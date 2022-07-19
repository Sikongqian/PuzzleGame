package PuzzleGame.Util;

import javafx.scene.media.AudioClip;

public class SoundEffect {
    public static void play(String url) {
        AudioClip audioClip = new AudioClip(SoundEffect.class.getResource(url).toString());
        audioClip.play();
    }
}

