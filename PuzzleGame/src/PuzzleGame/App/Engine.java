package PuzzleGame.App;

import javafx.animation.AnimationTimer;

/**
 * Engine for the game.
 * @function Engine is to control the game's main circle.
 * @author Group 10 - Heather Xiong - 20722114
 */
public class Engine {

    private final Timer timer;

    //these time are to make circle takes place with interval
    public double nowNanos;
    private double lastNanos;
    private double deltaNanos;

    private double fps;//frame per second

    private double npf;//second per frame

    OnStart onStart;
    OnUpdate onUpdate;
    OnStop onStop;

    public Engine() {
        timer = new Timer();
        setFps(60);
    }

    public Engine(double fps) {
        timer = new Timer();
        setFps(fps);
    }

    public double getFps() {
        return fps;
    }

    public void setFps(double fps) {
        this.fps = fps;
        this.npf = 1E9 / fps;//10^9
    }

    //these are for further development.
    public double getNowSec() {
        return nowNanos * 1E-9;
    }
    public double getNowNanos() {
        return nowNanos;
    }
    public double getNowMills() {
        return nowNanos * 1E-6;
    }
    public double getLastNanos() {
        return lastNanos;
    }
    public double getDeltaSec() {
        return deltaNanos * 1E-9;
    }
    public double getDeltaMills() {
        return deltaNanos * 1E-6;
    }
    public double getDeltaNanos() {
        return deltaNanos;
    }
    public double getNpf() {
        return npf;
    }

    //start engine and stop engine.
    void start() {
        timer.start();
    }
    void stop() {
        timer.stop();
    }

    /* these are to control the game ongoing timer */
    //Since the four methods in the parent class are public, there is no way to lower the public level.
    // You can create an inner class to protect these methods to control access authority.
    private final class Timer extends AnimationTimer {
        @Override
        public void start() {
            this.reset();
            super.start();
            if(onStart != null){
                onStart.handle();
            }
        }
        @Override
        public void handle(long now) {
            nowNanos = now;
            //the first frame doesn't have lastNanos
            if(lastNanos > 0) {
                deltaNanos += nowNanos - lastNanos;
            }
            lastNanos = nowNanos;
            //The initialization time of this frame is sufficient to satisfy the update condition.
            if(deltaNanos >= npf) {
                if(onUpdate != null) {
                    onUpdate.handle(deltaNanos);
                }
                deltaNanos -= npf;
            }
        }
        @Override
        public void stop() {
            super.stop();
            this.reset();
            if(onStop != null) {
                onStop.handle();
            }
        }

        public void reset() {
            nowNanos = 0;
            lastNanos = 0;
            deltaNanos = 0;
        }
    }

    //In order to protect the above methods
    static interface OnStart {
        void handle();
    }

    static interface OnStop {
        void handle();
    }

    static  interface OnUpdate {
        void handle(double time);
    }

}