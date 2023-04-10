package com.questforholygrail.game;

import com.questforholygrail.game.ui.Display;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class Sound {

  private static AudioInputStream audioInputStream;
  private static AudioInputStream audioInputStreamFX;
  private static Clip clip;
  private static Clip clipFx;
  private static FloatControl musicGainControl;
  private static FloatControl effectsGainControl;
  private static InputStream bis;
  private static boolean effectsMuted;
  private static boolean musicMuted;

  public void soundLoad() {
    try {

      BufferedInputStream myStream = new BufferedInputStream(Objects.requireNonNull(
          this.getClass().getClassLoader().getResourceAsStream("DanseMacabre.wav")));
      AudioInputStream audio2 = AudioSystem.getAudioInputStream(myStream);
      clip = AudioSystem.getClip();
      clip.open(audio2);
      musicGainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      changeMusicVolume(-10f);
      clip.start();
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static void playSound() {
    clip.start();
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    Display.printScreenLn("Music is playing.");
  }

  public static void stopSound() {
    clip.stop();
    Display.printScreenLn("Music stopped.");
  }

  public static void changeMusicVolume(float desiredVolume) {
    if (desiredVolume <= -30) {
      musicMuted = true;
      stopSound();
    } else {
      musicGainControl.setValue(Math.min(desiredVolume, musicGainControl.getMaximum()));
      if (musicMuted) {
        playSound();
        musicMuted = false;
      }
    }
  }

  public static void changeEffectsVolume(int boxStatus) {
    if (boxStatus == 1) {
      effectsMuted = false;
    } else {
      effectsMuted = true;
    }
  }

  public static void reduceSound() {
    musicGainControl.setValue(-10.0f);
    // Reduce volume by 10 decibels.
    Display.printScreenLn("Decrease volume by -10 decibels.");
  }

  public static void increaseSound() {
    musicGainControl.setValue(6.0f); // Increase volume by 6 decibels - Maximum allowed.
    Display.printScreenLn(
        "Increased volume by 6 decibels (maximum allowed). If needed more, please increase it from your hardware setting.");
  }


  public void soundFXLoad(Location[] gameLocations) {

    try {
      for (Location location : gameLocations) {
        if (location.getNpc().size() == 0) {
          continue;
        } else if (location.getNpc().get(0).getName().equalsIgnoreCase("banshee")) {
          BufferedInputStream myStream = new BufferedInputStream(Objects.requireNonNull(
              getClass().getResourceAsStream("/fx/banshee.wav")));
          location.getNpc().get(0).setNoise(AudioSystem.getAudioInputStream(myStream));
        } else if (location.getNpc().get(0).getName().equalsIgnoreCase("Ancient Nasirax")) {
          BufferedInputStream myStream = new BufferedInputStream(Objects.requireNonNull(
              getClass().getResourceAsStream("/fx/dragon.wav")));
          location.getNpc().get(0).setNoise(AudioSystem.getAudioInputStream(myStream));
        } else if (location.getNpc().get(0).getName().equalsIgnoreCase("fairy")) {
          BufferedInputStream myStream = new BufferedInputStream(Objects.requireNonNull(
              getClass().getResourceAsStream("/fx/fairy.wav")));
          location.getNpc().get(0).setNoise(AudioSystem.getAudioInputStream(myStream));
        } else if (location.getNpc().get(0).getName().equalsIgnoreCase("goblin")) {
          BufferedInputStream myStream = new BufferedInputStream(Objects.requireNonNull(
              getClass().getResourceAsStream("/fx/goblin.wav")));
          location.getNpc().get(0).setNoise(AudioSystem.getAudioInputStream(myStream));
        } else if (location.getNpc().get(0).getName().equalsIgnoreCase("minotaur")) {
          BufferedInputStream myStream = new BufferedInputStream(Objects.requireNonNull(
              getClass().getResourceAsStream("/fx/minotaur.wav")));
          location.getNpc().get(0).setNoise(AudioSystem.getAudioInputStream(myStream));
        } else if (location.getNpc().get(0).getName().equalsIgnoreCase("wizard")) {
          BufferedInputStream myStream = new BufferedInputStream(Objects.requireNonNull(
              getClass().getResourceAsStream("/fx/wizard.wav")));
          location.getNpc().get(0).setNoise(AudioSystem.getAudioInputStream(myStream));
        } else if (location.getNpc().get(0).getName().equalsIgnoreCase("griffin")) {
          BufferedInputStream myStream = new BufferedInputStream(Objects.requireNonNull(
              getClass().getResourceAsStream("/fx/griffin.wav")));
          location.getNpc().get(0).setNoise(AudioSystem.getAudioInputStream(myStream));
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }


  public void playSoundFX(NPC npc) {
    // test this
    if (npc.getNoise() == null | npc.isEffectPlayed() ) {
      return;
    }
    audioInputStreamFX = npc.getNoise();
    try {
      clipFx = AudioSystem.getClip();
      clipFx.open(audioInputStreamFX);
    } catch (LineUnavailableException | IOException e) {
      e.printStackTrace();
    }
    effectsGainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    if (!isEffectsMuted()) {
      clipFx.setFramePosition(0);
      clipFx.start();
      npc.setEffectPlayed(true);
    }
    Display.printScreenLn("Sound resumed playing.");
  }

  public static void stopSoundFX() {
    clipFx.stop();
    Display.printScreenLn("Sound stopped playing.");
  }


  public static boolean isEffectsMuted() {
    return effectsMuted;
  }

  public static void setEffectsMuted(boolean effectsMuted) {
    Sound.effectsMuted = effectsMuted;
  }

}