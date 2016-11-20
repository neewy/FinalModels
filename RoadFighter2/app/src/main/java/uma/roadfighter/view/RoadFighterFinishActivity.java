package uma.roadfighter.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import uma.roadfighter.R;

/**
 * Created by neewy on 14.11.16.
 */

public class RoadFighterFinishActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int scoreInt = getIntent().getExtras().getInt("SCORE", 0);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.finish);

        TextView score = (TextView) findViewById(R.id.score);
        score.setText("Your score is " + scoreInt);

        Button startGame = (Button) findViewById(R.id.start_game_1);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
