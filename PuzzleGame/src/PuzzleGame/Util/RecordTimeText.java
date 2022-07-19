package PuzzleGame.Util;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Timer
 *@function To measure the playing time
 */

public class RecordTimeText {
    private Text text_time;

    private Calendar dateTime;
    private boolean clockState;

    private Calendar originTime;

    private int clickFlag = 0;
    private Calendar duringPreTime;
    private Calendar duringPostTime;

    SimpleDateFormat timeDF = new SimpleDateFormat("mm:ss");

    /**
     *The constructor of the timer
     */
    public RecordTimeText() {
        setClockState(false);
        //setClockState(true);
        text_time = new Text("00:00");
        text_time.setLayoutX(88);
        text_time.setLayoutY(270);
        text_time.setFont(new Font("Arial", 20));
    }


    /**
     * Press the start button, timer start to work and show the pause button
     */
    public void startClock() {
        if (clickFlag == 0) {
            this.originTime = Calendar.getInstance();
            clickFlag=1;
        }else{
            this.duringPostTime=Calendar.getInstance();
            this.originTime.setTimeInMillis(originTime.getTimeInMillis()
                    +(duringPostTime.getTimeInMillis()-duringPreTime.getTimeInMillis()));
        }

        setClockState(true);
        clock();

    }

    /**
     * Press the pause button, timer pause to work
     */
    public void closeClock() {
        this.duringPreTime=Calendar.getInstance();
        setClockState(false);
        clock();
    }


    public void setClockState(boolean clockState) {
        this.clockState = clockState;
    }

    private void setTime() {
        this.dateTime = Calendar.getInstance();
    }

    /**
     * Update the time of the Text box
     */
    private void setText_Time() {
        double result = dateTime.getTimeInMillis() - originTime.getTimeInMillis();
        text_time.setText(timeDF.format(result));
    }

    public Text getText_time() {
        return text_time;
    }

    /**
     * To update the clock text. ClockState true starts a new thread, false does not start Clock state true starts a new thread, false does not start (or end) a thread a thread
     */
    public void clock() {
        if (clockState)
            new Thread(() -> {
                try {
                    while (clockState) {
                        setTime();
                        setText_Time();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
    }

}
