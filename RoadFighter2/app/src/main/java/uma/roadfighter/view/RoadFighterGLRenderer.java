package uma.roadfighter.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.os.SystemClock;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import uma.roadfighter.R;
import uma.roadfighter.model.RoadFighter;


public class RoadFighterGLRenderer implements GLSurfaceView.Renderer {
    // Game
    RoadFighter roadFighter = new RoadFighter();

    private RoadFighterActivity activity;

    // User Car
    int carLow, carHigh;
    // Obstacles and Opponents
    int nObstacles, nOpponents;
    // Random Generator
    Random generator;

    Boolean finalScoreShown = false;
    float totalTime;

    // Context
    private Context context;
    // Buffers
    private FloatBuffer vertexBuffer;
    private FloatBuffer texCoordBuffer;
    private long lastTime;
    // ID's
    private int[] texID;
    // Events
    public char event;

    private int currentScore;

    public void setEvent(char event) {
        if (event != this.event) {
            this.event = event;
        }
    }

    RoadFighterGLRenderer(Context c) {
        super();
        context = c;
        activity = (RoadFighterActivity) c;
        generator = new Random();
    }

    private float normalizeCoordinate(int c) {
        return ((float) c / (float) roadFighter.get_width().apply(RoadFighter.USER_LANE)) * 2.0f;
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        totalTime = 0;

        initializeLane();
        initializeUserCar();
        initializeScore();

        generateObstacles(10);
        generateOpponents(10);

        lastTime = SystemClock.uptimeMillis();

        initializeOpenGl(gl);
    }

    public void onDrawFrame(GL10 gl) {

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // Time
        long currentTime = SystemClock.uptimeMillis();
        long elapsed = currentTime - lastTime;
        lastTime = currentTime;

        // User finished
        if (roadFighter.get_finished().apply(RoadFighter.USER_CAR))  {
            event = 'n';
            if (!finalScoreShown) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    public void run() {
                        Intent finishGame = new Intent(context, RoadFighterFinishActivity.class);
                        finishGame.putExtra("SCORE", currentScore);
                        context.startActivity(finishGame);
                        activity.finish();
                    }
                });
                finalScoreShown = true;
            }
        }

        // SCORE
        Integer PObj = RoadFighter.USER_CAR;
        Integer PS = -(int) elapsed;
        roadFighter.evt_UPDATE_SCORE.run_UPDATE_SCORE(PObj, PS);

        // Input - Logic
        // Lean and Acceleration
        changeMode(PObj);

        Integer PElapsed = (int) elapsed + 10;

        // User Car
        roadFighter.evt_UPDATE_VEL.run_UPDATE_VEL(PObj, PElapsed);
        roadFighter.evt_SET_MAXVEL.run_SET_MAXVEL(PObj, roadFighter.get_maxvel().apply(PObj));
        roadFighter.evt_APPLY_FRICTION.run_APPLY_FRICTION(PElapsed, RoadFighter.USER_LANE, PObj);
        roadFighter.evt_UPDATE_POS.run_UPDATE_POS(PElapsed, PObj);

        // Opponents
        for (int i = 2 + nObstacles; i < 2 + nObstacles + nOpponents; i++) {
            if ((roadFighter.get_active().has(i, true) || roadFighter.get_active().has(i, false)) && roadFighter.get_active().apply(i)) {
                PObj = i;
                roadFighter.evt_UPDATE_VEL.run_UPDATE_VEL(PObj, PElapsed);
                roadFighter.evt_SET_MAXVEL.run_SET_MAXVEL(PObj, roadFighter.get_maxvel().apply(PObj));
                roadFighter.evt_APPLY_FRICTION.run_APPLY_FRICTION(PElapsed, RoadFighter.USER_LANE, PObj);
                roadFighter.evt_UPDATE_POS.run_UPDATE_POS(PElapsed, PObj);
            }

        }

		/* Collisions */
        Integer PObj1 = RoadFighter.USER_CAR;
        // Obstacles + Opponents
        for (int i = 2; i < 2 + nObstacles + nOpponents; i++) {
            if ((roadFighter.get_active().has(i, true) || roadFighter.get_active().has(i, false)) && roadFighter.get_active().apply(i)) {
                Integer PObj2 = i;
                roadFighter.evt_OBJECT_COLLISION.run_OBJECT_COLLISION(PObj1, PObj2);
            }

            // Reset Car
            if (roadFighter.get_collision().apply(RoadFighter.USER_CAR)) {
                PObj = RoadFighter.USER_CAR;
                Integer PTrack = RoadFighter.USER_LANE;
                roadFighter.evt_CAR_RESET.run_CAR_RESET(PObj, PTrack);
                PS = -5000;
                roadFighter.evt_UPDATE_SCORE.run_UPDATE_SCORE(PObj, PS);
            }
        }

        // Track
        PObj = RoadFighter.USER_CAR;
        Integer PTrack = RoadFighter.USER_LANE;
        roadFighter.evt_WALL_COLLISION.run_WALL_COLLISION(PObj, PTrack);

        // Reset Car
        if (roadFighter.get_collision().apply(RoadFighter.USER_CAR)) {
            roadFighter.evt_CAR_RESET.run_CAR_RESET(PObj, PTrack);
            PS = -1000;
            roadFighter.evt_UPDATE_SCORE.run_UPDATE_SCORE(PObj, PS);
        }

        // Finish track?
        roadFighter.evt_FINISHED_LANE.run_FINISHED_LANE(PObj, RoadFighter.USER_LANE);

        // Clear
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // Buffers
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoordBuffer);

        // Camera
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl, 1.0f, normalizeCoordinate(roadFighter.get_posY().apply(RoadFighter.USER_CAR)) + 0.5f, 1.0f,
                1.0f, normalizeCoordinate(roadFighter.get_posY().apply(RoadFighter.USER_CAR)) + 0.5f, 0.0f,
                0.0f, 1.0f, 0.0f);

        // Track
        gl.glPushMatrix();
        gl.glScalef(1.0f, (float) roadFighter.get_height().apply(RoadFighter.USER_LANE) / (float) roadFighter.get_width().apply(RoadFighter.USER_LANE), 1.0f);
        gl.glTranslatef(1.0f, 1.0f, 0.0f);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[0]);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
        gl.glPopMatrix();

        // User-car
        gl.glPushMatrix();
        gl.glTranslatef(normalizeCoordinate(roadFighter.get_posX().apply(RoadFighter.USER_CAR)), normalizeCoordinate(roadFighter.get_posY().apply(RoadFighter.USER_CAR)), 0.0f);
        gl.glScalef((float) roadFighter.get_width().apply(RoadFighter.USER_CAR) / (float) roadFighter.get_width().apply(RoadFighter.USER_LANE), (float) roadFighter.get_height().apply(RoadFighter.USER_CAR) / (float) roadFighter.get_width().apply(RoadFighter.USER_LANE), 1.0f);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[1]);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
        gl.glDisable(GL10.GL_BLEND);
        gl.glPopMatrix();

        // Obstacles
        for (int i = 2; i < 2 + nObstacles; i++) {
            if ((roadFighter.get_active().has(i, true) || roadFighter.get_active().has(i, false)) && roadFighter.get_active().apply(i)) {
                gl.glPushMatrix();
                gl.glTranslatef(normalizeCoordinate(roadFighter.get_posX().apply(i)), normalizeCoordinate(roadFighter.get_posY().apply(i)), 0.0f);
                gl.glScalef((float) roadFighter.get_width().apply(i) / (float) roadFighter.get_width().apply(RoadFighter.USER_LANE), (float) roadFighter.get_height().apply(i) / (float) roadFighter.get_width().apply(RoadFighter.USER_LANE), 1.0f);
                gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[3]);
                gl.glEnable(GL10.GL_BLEND);
                gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
                gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
                gl.glDisable(GL10.GL_BLEND);
                gl.glPopMatrix();
            }
        }

        // Opponents
        for (int i = 2 + nObstacles; i < 2 + nObstacles + nOpponents; i++) {
            if ((roadFighter.get_active().has(i, true) || roadFighter.get_active().has(i, false)) && roadFighter.get_active().apply(i)) {
                gl.glPushMatrix();
                gl.glTranslatef(normalizeCoordinate(roadFighter.get_posX().apply(i)), normalizeCoordinate(roadFighter.get_posY().apply(i)), 0.0f);
                gl.glScalef((float) roadFighter.get_width().apply(i) / (float) roadFighter.get_width().apply(RoadFighter.USER_LANE), (float) roadFighter.get_height().apply(i) / (float) roadFighter.get_width().apply(RoadFighter.USER_LANE), 1.0f);
                gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[2]);
                gl.glEnable(GL10.GL_BLEND);
                gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
                gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
                gl.glDisable(GL10.GL_BLEND);
                gl.glPopMatrix();
            }
        }

        currentScore = roadFighter.get_score().apply(RoadFighter.USER_CAR);
        activity.setScore(currentScore);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }


    private void  initializeOpenGl(GL10 gl) {
        // OpenGL
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // Vertex Buffer
        float vertex[] = {
                -1.0f, -1.0f, 0.0f,
                -1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                -1.0f, -1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, -1.0f, 0.0f
        };
        // Buffer with space
        ByteBuffer vb = ByteBuffer.allocateDirect(vertex.length * 4); // Space
        vb.order(ByteOrder.nativeOrder());    // Hardware order
        vertexBuffer = vb.asFloatBuffer();    // Memory to VertexBuffer (in float)
        vertexBuffer.put(vertex);                // Put vertex info
        vertexBuffer.position(0);

        // TexCoord Buffer
        float texCoord[] = {
                0.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f
        };
        // Buffer with space
        ByteBuffer tb = ByteBuffer.allocateDirect(texCoord.length * 4); // Space
        tb.order(ByteOrder.nativeOrder());    // Hardware order
        texCoordBuffer = tb.asFloatBuffer();    // Memory to VertexBuffer (in float)
        texCoordBuffer.put(texCoord);            // Put vertex info
        texCoordBuffer.position(0);

        // Textures
        texID = new int[4];
        gl.glGenTextures(4, texID, 0);


        // Track texture
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[0]);
        InputStream is = context.getResources().openRawResource(+R.drawable.map);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();

        // User-car texture
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[1]);
        is = context.getResources().openRawResource(+R.drawable.car);
        bitmap = BitmapFactory.decodeStream(is);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();

        // Opponent-car texture
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[2]);
        is = context.getResources().openRawResource(+R.drawable.opponent);
        bitmap = BitmapFactory.decodeStream(is);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();

        // Obstacle texture
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texID[3]);
        is = context.getResources().openRawResource(+R.drawable.obstacle);
        bitmap = BitmapFactory.decodeStream(is);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    private void generateOpponents(int opponentsNum) {
        nOpponents = opponentsNum;
        Integer PTex = R.drawable.opponent;
        Integer PW = 15;
        Integer PH = 15;
        for (int i = 2 + nObstacles; i < 2 + nObstacles + nOpponents; i++) {
            Integer PObj = i;
            Integer PX = roadFighter.get_left_border().apply(RoadFighter.USER_LANE) + PW / 2 + generator.nextInt(roadFighter.get_right_border().apply(RoadFighter.USER_LANE) - roadFighter.get_left_border().apply(RoadFighter.USER_LANE) - PW);

            Integer PY = PH / 2 + generator.nextInt(roadFighter.get_finish_line().apply(RoadFighter.USER_LANE) - PH);

            Integer PV = 38;
            Integer PF = 3;
            roadFighter.evt_ADD_CAR.run_ADD_CAR(PObj, PTex, PH, PW, PX, PY, PF, PV);
            Integer PA = carHigh;
            roadFighter.evt_SET_ACC.run_SET_ACC(PA, PObj);
        }
    }

    private void generateObstacles(int obstaclesNum) {
        // Obstacles
        nObstacles = obstaclesNum;
        Integer PTex = R.drawable.obstacle;
        Integer PW = 10;
        Integer PH = 10;
        for (int i = 2; i < 2 + nObstacles; i++) {
            Integer PObj = i;
            Integer PX = roadFighter.get_left_border().apply(RoadFighter.USER_LANE) + PW / 2 + generator.nextInt(roadFighter.get_right_border().apply(RoadFighter.USER_LANE) - roadFighter.get_left_border().apply(RoadFighter.USER_LANE) - PW);

            Integer PY = PH / 2 + generator.nextInt(roadFighter.get_finish_line().apply(RoadFighter.USER_LANE) - PH);
            roadFighter.evt_ADD_OBSTACLE.run_ADD_OBSTACLE(PTex, PH, PObj, PW, PX, PY);
        }
    }

    private void initializeScore() {
        Integer PS = 100000;
        roadFighter.evt_UPDATE_SCORE.run_UPDATE_SCORE(RoadFighter.USER_CAR, PS);
    }

    private void initializeUserCar() {
        Integer PObj = RoadFighter.USER_CAR;
        Integer PTex = R.drawable.car;
        Integer PW = 15;
        Integer PH = 15;
        Integer PX = roadFighter.get_width().apply(RoadFighter.USER_LANE) / 2;
        Integer PY = 50;

        Integer PV = 50;
        carLow = 40;
        carHigh = 90;
        Integer PF = 3;
        roadFighter.evt_ADD_CAR.run_ADD_CAR(PObj, PTex, PH, PW, PX, PY, PF, PV);
    }

    private void initializeLane() {
        Integer PObj = RoadFighter.USER_LANE;
        Integer PTex = R.drawable.map;
        Integer PW = 128;
        Integer PH = 1536;
        Integer PX = 0;
        Integer PY = 0;
        roadFighter.evt_ADD_OBJECT.run_ADD_OBJECT(PTex, PH, PObj, PW, PX, PY);

        Integer PF = 3;
        Integer PBL = 25;
        Integer PBR = 100;
        Integer PFL = 1396;
        roadFighter.evt_ADD_LANE.run_ADD_LANE(PFL, PObj, PBL, PBR, PF);
    }

    private void changeMode(Integer PObj) {
        Integer PL;
        Integer PA;
        switch (event) {
            case 'l':
                PL = -1;
                roadFighter.evt_SET_DRIFT.run_SET_DRIFT(PObj, PL);
                break;
            case 'r':
                PL = 1;
                roadFighter.evt_SET_DRIFT.run_SET_DRIFT(PObj, PL);
                break;
            case 'c':
                PL = 0;
                roadFighter.evt_SET_DRIFT.run_SET_DRIFT(PObj, PL);
                break;
            case 'a':
                roadFighter.evt_SET_ACC.run_SET_ACC(20, PObj);
                roadFighter.evt_SET_MAXVEL.run_SET_MAXVEL(PObj,carLow);
                break;
            case 'h':
                roadFighter.evt_SET_ACC.run_SET_ACC(40, PObj);
                roadFighter.evt_SET_VEL.run_SET_VEL(PObj,10);
                roadFighter.evt_SET_MAXVEL.run_SET_MAXVEL(PObj,carHigh);
                break;
            case 'n':
                PA = 0;
                roadFighter.evt_SET_ACC.run_SET_ACC(PA, PObj);
                roadFighter.evt_SET_VEL.run_SET_VEL(PObj,0);
                roadFighter.evt_SET_ZERO_VEL.run_SET_ZERO_VEL(PObj);
                break;
        }
    }

    public boolean isNPOTSupported(GL10 gl) {
        String extensions = gl.glGetString(GL10.GL_EXTENSIONS);
        return extensions.indexOf("GL_OES_texture_npot") != -1;
    }

}
