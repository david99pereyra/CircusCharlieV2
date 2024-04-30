package jgame.gradle.Pong;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sonido {
    public static Clip clip;

    public static void iniciar(String nombre){
        try {
            File file = new File("app/src/main/resources/musica/Pong/" + nombre + ".wav");
            clip = AudioSystem.getClip();
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
            clip.open(audioInput);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void parar(){
        clip.stop();
    }

    public static void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
