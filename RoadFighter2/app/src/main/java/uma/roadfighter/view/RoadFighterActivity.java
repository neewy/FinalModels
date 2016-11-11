package uma.roadfighter.view;

import android.app.Activity;
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
import android.widget.RelativeLayout;

import uma.roadfighter.R;

import static uma.roadfighter.view.RotateDirection.IDLE;
import static uma.roadfighter.view.RotateDirection.LEFT;
import static uma.roadfighter.view.RotateDirection.RIGHT;

public class RoadFighterActivity extends Activity implements SensorEventListener {
    /**
     * Called when the activity is first created.
     */
    public RoadFighterGLView GLView;
    private boolean isTiltChosen;
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

        if (isTiltChosen) {
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

            accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

            GLView.onUpPress();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initListeners();
                }
            }, 100);
        } else {
            setupButtons();
        }

    }

    private void initListeners() {
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        GLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GLView.onResume();
    }

    private void setupButtons() {
        RelativeLayout buttonControls = new RelativeLayout(this);

        RelativeLayout.LayoutParams buttonsParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        buttonsParams.setMargins(25, 0, 25, 25);

        RelativeLayout.LayoutParams leftButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        leftButtonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        leftButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        RelativeLayout.LayoutParams upButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        upButtonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        upButtonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        RelativeLayout.LayoutParams rightButtonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rightButtonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rightButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        Button left = new Button(this);
        left.setText("←");
        left.setBackgroundResource(R.drawable.button_states);
        left.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

        Button right = new Button(this);
        right.setText("→");
        right.setBackgroundResource(R.drawable.button_states);
        right.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

        Button up = new Button(this);
        up.setText("↑");
        up.setBackgroundResource(R.drawable.button_states);
        up.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

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

        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    GLView.onUpPress();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    GLView.onRelease();
                    return true;
                }
                return false;
            }
        });

        buttonControls.addView(left, leftButtonParams);
        buttonControls.addView(up, upButtonParams);
        buttonControls.addView(right, rightButtonParams);

        this.addContentView(buttonControls, buttonsParams);
    }


    float[] inclineGravity = new float[3];
    float[] mGravity;
    float[] mGeomagnetic;
    float orientation[] = new float[3];
    float pitch;
    float roll;

    @Override
    public void onSensorChanged(SensorEvent event) {
        //If type is accelerometer only assign values to global property mGravity
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mGravity = event.values;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mGeomagnetic = event.values;

            switch (getRotateDirection()) {
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

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public RotateDirection getRotateDirection() {
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];

            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);

            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                pitch = orientation[1];
                roll = orientation[2];

                if (roll > 0.25 && roll < 1) {
                    return RIGHT;
                } else if (roll < -0.25 && roll > -1) {
                    return LEFT;
                } else if (roll > -0.25 && roll < 0.25) {
                    return IDLE;
                }
            }
        }
        return IDLE;
    }
}