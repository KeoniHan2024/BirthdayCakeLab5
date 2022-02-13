package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    //instance variable pointing to CakeView object in GUI
    public CakeView reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        //hi
        // Insert findviewByID() Here

        final CakeView CakeViewreference = findViewById(R.id.cakeviewID);

        final CakeController CakeControlObject = new CakeController(CakeViewreference);

        Button blowOutButton = findViewById(R.id.BlowOutButton);
        blowOutButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("tag", "Blow Out Button Clicked");
                        CakeControlObject.getCakeModel().candleLitStatus = false;
                        CakeViewreference.invalidate();
                    }
                });

        CompoundButton candleToggleSwitch = findViewById(R.id.CandleToggle);
        candleToggleSwitch.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b == true) {
                            CakeControlObject.getCakeModel().candlePresence = true;
                        } else if (b != true) {
                            CakeControlObject.getCakeModel().candlePresence = false;
                        }
                        CakeViewreference.invalidate();
                    }
                }
        );

        SeekBar candleCounterSeekBar = findViewById(R.id.candleCounter);
        candleCounterSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        CakeControlObject.getCakeModel().candleCount = i;
                        //System.out.println(CakeControlObject.getCakeModel().candleCount);
                        CakeViewreference.invalidate();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );


    }

    public void goodBye(View button) {
        Log.i("button", "Goodbye");
    }

}
