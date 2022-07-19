package PuzzleGame.Input;

import javafx.scene.input.KeyCode;

public enum Key {

    //numbers
    NUM0(KeyCode.DIGIT0, "0"),
    NUM1(KeyCode.DIGIT1, "1"),
    NUM2(KeyCode.DIGIT2, "2"),
    NUM3(KeyCode.DIGIT3, "3"),
    NUM4(KeyCode.DIGIT4, "4"),
    NUM5(KeyCode.DIGIT5, "5"),
    NUM6(KeyCode.DIGIT6, "6"),
    NUM7(KeyCode.DIGIT7, "7"),
    NUM8(KeyCode.DIGIT8, "8"),
    NUM9(KeyCode.DIGIT9, "9"),

    //letters
    A(KeyCode.A,"A"),

    //Functions
    F1(KeyCode.F1,"F1"),

    //white space
    SPACE(KeyCode.SPACE," "),
    ENTER(KeyCode.ENTER,"\n"),
    TAB(KeyCode.TAB,"\t"),

    //Marks
    WAVE(null,"~"),
    EXCLAMATION(KeyCode.EXCLAMATION_MARK,"!"),

    //ESCAPE
    ESCAPE(KeyCode.ESCAPE, "ESC"),
    ;

    private final KeyCode code;
    private final String text;
    //枚举类constructer只能是private
    private Key(KeyCode code, String text) {
        this.code = code;
        this.text = text;
    }

    public KeyCode getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
    public static Key find(KeyCode code){
        for (Key k : values()) {
            if(k.code != null && k.code == code){
                return k;
            }
        }
        return null;
    }
    public static Key find(String text){
        for (Key k : values()) {
            if(k.text != null && k.text.equalsIgnoreCase(text)){
                return k;
            }
        }
        return null;
    }
}
