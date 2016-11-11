package uma.roadfighter.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import uma.roadfighter.R;

/**
 * Created by neewy on 10.11.16.
 */

public class RoadFighterMenuActivity extends Activity {

    private Switch controlSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_menu);

        Button startGame = (Button) findViewById(R.id.start_game);
        controlSwitch = (Switch) findViewById(R.id.control_switch);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    public void startGame() {
        Intent startGame = new Intent(this, RoadFighterActivity.class);
        startGame.putExtra("TILT", controlSwitch.isChecked());
        startActivity(startGame);
    }
}
