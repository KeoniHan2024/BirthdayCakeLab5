package cs301.birthdaycake;

import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    private CakeView aCakeView;
    private CakeModel aCakeModel;


    public CakeController(CakeView cvReference) {
        aCakeView = cvReference;
        aCakeModel = aCakeView.getCakeModelReference();
    }

    @Override
    public void onClick(View view) {
        Log.d("tag", "Clicked");
    }

    public CakeModel getCakeModel() {
        return aCakeModel;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b == true) {
            aCakeModel.candlePresence = true;
        }
        else {
            aCakeModel.candlePresence = false;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        aCakeModel.candleCount = i;
        aCakeView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}
