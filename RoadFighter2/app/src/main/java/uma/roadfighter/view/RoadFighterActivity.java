package uma.roadfighter.view;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uma.roadfighter.R;

import static uma.roadfighter.view.Direction.DOWN;
import static uma.roadfighter.view.Direction.IDLE;
import static uma.roadfighter.view.Direction.LEFT;
import static uma.roadfighter.view.Direction.RIGHT;
import static uma.roadfighter.view.Direction.UP;

public class RoadFighterActivity extends Activity implements SensorEventListener {

    public RoadFighterGLView GLView;
    private boolean isTiltChosen;
    private boolean isPhoneTiltUpward = false;
    private TextView score;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isTiltChosen = getIntent().getExtras().getBoolean("TILT", false);

        // OpenGL surface
        GLView = new RoadFighterGLView(this);
        setContentView(GLView);

        addScore();

        if (isTiltChosen) {
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

            accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

            initListeners();
        } else {
            setupButtons();
        }

    }

    private void addScore() {

        RelativeLayout.LayoutParams scoreParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        scoreParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        scoreParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        score = new TextView(this);
        score.setTextColor(Color.WHITE);
        score.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);

        RelativeLayout scoreWrapper = new RelativeLayout(this);

        RelativeLayout.LayoutParams scoreWrapperParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        scoreWrapperParams.setMargins(25, 25, 25, 25);

        scoreWrapper.addView(score, scoreParams);

        this.addContentView(scoreWrapper, scoreWrapperParams);
    }

    public void setScore(final int scorePoints) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                score.setText("Score: " + scorePoints);
            }
        });
    }

    private void initListeners() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSensorManager.registerListener(RoadFighterActivity.this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
                mSensorManager.registerListener(RoadFighterActivity.this, magnetometer, SensorManager.SENSOR_DELAY_FASTEST);
            }
        }, 100);

    }

    private void setupButtons() {
        RelativeLayout buttonControls = new RelativeLayout(this);

        RelativeLayout.LayoutParams buttonsParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        buttonsParams.setMargins(25, 25, 25, 25);

        RelativeLayout.LayoutParams leftButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        leftButtonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        leftButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        RelativeLayout.LayoutParams upButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        upButtonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        upButtonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        RelativeLayout.LayoutParams rightButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rightButtonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rightButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        RadioGroup gearGroup = new RadioGroup(this);

        RadioGroup.LayoutParams params
                = new RadioGroup.LayoutParams(this, null);
        params.setMargins(10, 10, 10, 10);

        RadioButton brake = new RadioButton(this);
        RadioButton slowSpeed = new RadioButton(this);
        RadioButton fastSpeed = new RadioButton(this);

        brake.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        slowSpeed.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        fastSpeed.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        brake.setLayoutParams(params);
        slowSpeed.setLayoutParams(params);
        fastSpeed.setLayoutParams(params);

        brake.setBackgroundResource(R.drawable.button_states);
        slowSpeed.setBackgroundResource(R.drawable.button_states);
        fastSpeed.setBackgroundResource(R.drawable.button_states);

        brake.setButtonDrawable(android.R.color.transparent);
        slowSpeed.setButtonDrawable(android.R.color.transparent);
        fastSpeed.setButtonDrawable(android.R.color.transparent);

        brake.setText("STOP");
        slowSpeed.setText("SLOW");
        fastSpeed.setText("FAST");

        brake.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        slowSpeed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        fastSpeed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        gearGroup.addView(fastSpeed);
        gearGroup.addView(slowSpeed);
        gearGroup.addView(brake);

        brake.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    GLView.onBreakPress();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    GLView.onRelease();
                    return true;
                }
                return false;
            }
        });

        slowSpeed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    GLView.onSlowPress();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    GLView.onRelease();
                    return true;
                }
                return false;
            }
        });


        fastSpeed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    GLView.onFastPress();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    GLView.onRelease();
                    return true;
                }
                return false;
            }
        });

        Button left = new Button(this);
        left.setText("←");
        left.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        left.setBackgroundResource(R.drawable.button_states);
        left.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

        Button right = new Button(this);
        right.setText("→");
        right.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        right.setBackgroundResource(R.drawable.button_states);
        right.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);


        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    GLView.onLeftPress();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    GLView.onRelease();
                    return true;
                }
                return false;
            }
        });


        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    GLView.onRightPress();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    GLView.onRelease();
                    return true;
                }
                return false;
            }
        });


        left.setLayoutParams(params);
        right.setLayoutParams(params);

        buttonControls.addView(left, leftButtonParams);
        buttonControls.addView(gearGroup, upButtonParams);
        buttonControls.addView(right, rightButtonParams);

        buttonControls.setAlpha(0.3f);

        this.addContentView(buttonControls, buttonsParams);
    }

    float[] mGravity;
    float[] mGeomagnetic;
    float pitch;
    float roll;

    @Override
    public void onSensorChanged(SensorEvent event) {
        //If type is accelerometer only assign values to global property mGravity
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mGravity = event.values;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mGeomagnetic = event.values;

            Direction[] currentPosition = getRotateDirection();

            if (currentPosition[0] != null && currentPosition[1] != null)  {
                switch (currentPosition[0]) {
                    case UP:
                        if (!isPhoneTiltUpward) {
                            GLView.onFastPress();
                            isPhoneTiltUpward = true;
                        }
                        break;
                    case DOWN:
                        isPhoneTiltUpward = false;
                        break;
                    case IDLE:
                        isPhoneTiltUpward = false;
                        break;
                }

                if (!isPhoneTiltUpward) {
                    switch (currentPosition[1]) {
                        case RIGHT:
                            GLView.onRightPress();
                            break;
                        case LEFT:
                            GLView.onLeftPress();
                            break;
                        case IDLE:
                            GLView.onRelease();
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public Direction[] getRotateDirection() {

        Direction[] result = new Direction[2];

        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];

            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);

            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                pitch = orientation[1];
                roll = orientation[2];

                if (pitch > 0.35 && pitch < 1) {
                    result[0] = UP;
                } else if (pitch < -0.35 && pitch > -1) {
                    result[0] = DOWN;
                } else if (pitch > -0.35 && pitch < 0.35) {
                    result[0] = IDLE;
                }

                if (roll > 0.25 && roll < 1) {
                    result[1] = RIGHT;
                } else if (roll < -0.25 && roll > -1) {
                    result[1] = LEFT;
                } else if (roll > -0.25 && roll < 0.25) {
                    result[1] = IDLE;
                }
            }
        }
        return result;
    }
}