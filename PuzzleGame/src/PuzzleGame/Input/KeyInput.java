package PuzzleGame.Input;

import javafx.stage.Stage;

import javafx.scene.input.KeyEvent;

/**
 *
 */
public class KeyInput {

    private final class Unit {

        private boolean pressed;
        private boolean released;
        private boolean held;

        private int typeCount;
        private int typeStore;
        private long typeStamp;

        private static final int TYPE_DURATION = 200;

        public Unit() {
        }

        public void press() {
            if (!held) {
                pressed = true;
                held = true;
            }
        }

        public void release() {
            if (held) {
                released = true;
                held = false;
                typeCount = ++typeStore;
                typeStamp = System.currentTimeMillis();
            }
        }

        public void refresh() {
            pressed = false;
            released = false;

            typeCount = 0;

            if (typeStamp > 0) {
                long now = System.currentTimeMillis();
                long stamp = typeStamp;

                if (now - stamp > TYPE_DURATION) {
                    typeCount = 0;
                    typeStore = 0;
                }
            }
        }

        public void reset() {
            pressed = false;
            released = false;
            held = false;

            typeStore = 0;
            typeCount = 0;
            typeStamp = 0;
        }
    }

    private final Unit[] units;
    public KeyInput() {
        units = new Unit[Key.values().length];//init
        for(int i = 0; i< units.length; i++) {//new
            units[i] = new Unit();
        }
    }
    public boolean isPressed(Key k){
        return k != null ? units[k.ordinal()].pressed : false;
    }
    public boolean isReleased(Key k){
        return k != null ? units[k.ordinal()].released : false;
    }
    public boolean isHeld(Key k){
        return k != null ? units[k.ordinal()].held : false;
    }
    public int getTypeCount(Key k) {
        return k != null ? units[k.ordinal()].typeCount : 0;
    }

    public void install(Stage stage) {
        stage.addEventHandler(KeyEvent.KEY_PRESSED,this::handleKeyPressed);
        stage.addEventHandler(KeyEvent.KEY_RELEASED,this::handleKeyReleased);

        reset();
    }

    public void uninstall(Stage stage) {
        stage.removeEventHandler(KeyEvent.KEY_PRESSED,this::handleKeyPressed);
        stage.removeEventHandler(KeyEvent.KEY_RELEASED,this::handleKeyReleased);

        reset();
    }

    private final void handleKeyPressed(KeyEvent e) {
        press(Key.find(e.getCode()));
        press(Key.find(e.getText()));
    }
    private final void handleKeyReleased(KeyEvent e) {
        release(Key .find(e.getCode()));
        release(Key.find(e.getText()));
    }

    //
    public boolean isTyped(Key k) {
        return getTypeCount(k) > 0;
    }

    public void press(Key k) {
        if(k != null) {
            units[k.ordinal()].press();
        }
    }

    public void release(Key k) {
        if(k != null) {
            units[k.ordinal()].release();
        }
    }

    public void refresh() {
        for(int i = 0; i < units.length; i++) {
            units[i].refresh();
        }
    }

    public void reset() {
        for(int i = 0; i < units.length; i++) {
            units[i].reset();
        }
    }
}
