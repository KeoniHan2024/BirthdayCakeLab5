package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint coordinateColor = new Paint();
    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 40.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;
    public Boolean drawBalloon = false;
    public Paint balloonPaint;
    public float balloonX;
    public float balloonY;
    private CakeModel newCake;

    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFF74F284);  //light Line Green
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        coordinateColor.setColor(0xFFFF0000);
        coordinateColor.setTextSize(50.0f);

        setBackgroundColor(Color.WHITE);  //better than black default

        //Initializes to new cakeModel object
        newCake = new CakeModel();
    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth + 50, bottom, candlePaint);

            if (newCake.candleLitStatus == true) {
                //draw the outer flame
                float flameCenterX = 25 + left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }

            //draw the wick
            float wickLeft = 25 + left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas) {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now a candle in the center
        int candleCount = newCake.candleCount;
        if (newCake.candlePresence != true) {
            return;
        }
        if (candleCount == 1) {
            drawCandle(canvas, cakeLeft + cakeWidth / 2, cakeTop);
        } else if (candleCount == 2) {
            drawCandle(canvas, cakeLeft + cakeWidth / 3 + cakeWidth / 3, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth / 3 + candleWidth / 4, cakeTop);
        } else if (candleCount == 3) {
            drawCandle(canvas, cakeLeft + cakeWidth / 2, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth / 2 + cakeWidth/5, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth / 2 - cakeWidth/5, cakeTop);
        } else if (candleCount == 4) {
            drawCandle(canvas, cakeLeft + cakeWidth / 3 + cakeWidth / 3 - cakeWidth/50, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth / 3 + candleWidth / 4 + cakeWidth/50, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth / 3 + cakeWidth / 3 + cakeWidth/4, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth / 3 + candleWidth / 4 - cakeWidth/4, cakeTop);

        } else if (candleCount == 5) {
            drawCandle(canvas, cakeLeft + cakeWidth / 2, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth / 2 + cakeWidth/5, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth / 2 - cakeWidth/5, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth / 2 + cakeWidth/5 * 2, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth / 2 - cakeWidth/5 * 2, cakeTop);

        }//onDraw

        if(drawBalloon == true){
            canvas.drawRect(balloonX-5, balloonY, balloonX+5, balloonY + 100, wickPaint);
            canvas.drawOval(balloonX + 25, balloonY-50, balloonX-25, balloonY+50, balloonPaint);
        }
        canvas.drawText("Tap Coordinates:", getWidth()-500.0f, getHeight()-60.0f, coordinateColor);
        canvas.drawText(newCake.coordinateX + ", " + newCake.coordinateY, getWidth()-500.0f, getHeight()-15.0f, coordinateColor);

    }

        public CakeModel getCakeModelReference () {
            return this.newCake;
        }
}//class CakeView

