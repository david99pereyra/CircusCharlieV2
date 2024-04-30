/*
 Ejemplo original

 https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html

 */
package jgame.gradle.CircusCharlie;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;


public enum FXPlayer {
  FIN_JUEGO("rick_morty.wav"),
  TEMA1("spanish_flea.wav"),
  TEMA2("muyun_brothers_the_kiffness.wav"),
  FX00("fx00.wav"),
  FX01("fx01.wav"),
  FX02("fx02.wav"),
  EVENTO1("CircusCharlieAudio1.wav"),
  EVENTO2("CircusCharlieAudio2.wav"),
  EVENTO3("CircusCharlieAudio3.wav"),
  DRAGONBALL("DragonballZ_OST_SonGokuIsTheStrongestAfterAll.wav"),
  DBRAP("DBRap.wav"),
  DBSUPER("DragonBallSuper.wav"),
  UI("UltraInstinto.wav"),
  BARDOCK("DragonBallZ_Bardock.wav");


   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }

   public static Volume volume = Volume.LOW;

   private Clip clip;

   FXPlayer(String wav) {
      try {

         URL url = this.getClass().getClassLoader().getResource("musica/"+wav);
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         clip = AudioSystem.getClip();
         clip.open(audioInputStream);

      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }

   public void play() {
      if (volume != Volume.MUTE) {
         if (!clip.isRunning()){
            clip.setFramePosition(0);
            clip.start();
         }
      }
   }

   public void stop(){
      if (volume != Volume.MUTE) {
         if (clip.isRunning()){
            clip.setFramePosition(0);
            clip.stop();
         }
      }
   }

   public void loop(){
       if (volume != Volume.MUTE) {
         if (!clip.isRunning()){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
         }
      }
   }
 
   static void init() {
      values();
   }
}