package com.questforholygrail.game;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import static com.questforholygrail.game.Commands.currentLocation;

public class Sound {

  public static AudioInputStream audioInputStream;
  public static AudioInputStream audioInputStreamFX;
  public static Clip clip;
  public static Clip clipFx;
  public static FloatControl gainControl;
  Location locations;

  public void soundLoad() {
    try {

      audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("DanseMacabre.wav"));
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.loop(Clip.LOOP_CONTINUOUSLY);
      clip.start();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void playSound() {
    clip.start();
    System.out.println("Sound is resumed playing.");
  }

  public static void stopSound() {
    clip.stop();
    System.out.println("Sound is stopped playing.");
  }

  public static void reduceSound() {
    gainControl =(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

    gainControl.setValue(-10.0f);
    // Reduce volume by 10 decibels.
    System.out.println("Decrease volume by -10 decibels.");
  }

  public static void increaseSound() {
    gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(6.0f); // Increase volume by 6 decibels - Maximum allowed.
    System.out.println("Increased volume by 6 decibels (maximum allowed). If needed more, please increase it from your hardware setting.");
  }

  // --------------------------------------------------------------- //

  public void soundFXLoad(Player player) {
    String playerLocation = player.getLocation().getName();

    try {
        if(playerLocation.equals("The Gate of Trials")) {
          audioInputStreamFX = AudioSystem.getAudioInputStream(
              this.getClass().getClassLoader().getResourceAsStream("Wolf.wav"));
          clipFx = AudioSystem.getClip();
          clipFx.open(audioInputStreamFX);
          clipFx.start();
        } else if (playerLocation.equals("Chimera's Grotto Entrance")) {
          audioInputStreamFX = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("Witch.wav"));
          clipFx = AudioSystem.getClip();
          clipFx.open(audioInputStreamFX);
          clipFx.start();
        } else if (playerLocation.equals("Armory")) {
          audioInputStreamFX = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("Scream.wav"));
          clipFx = AudioSystem.getClip();
          clipFx.open(audioInputStreamFX);
          clipFx.start();
        }
        else if (playerLocation.equals("Key Room")) {
          audioInputStreamFX = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("Wind.wav"));
          clipFx = AudioSystem.getClip();
          clipFx.open(audioInputStreamFX);
          clipFx.start();
        }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void playSoundFX() {
    clipFx.start();
    System.out.println("Sound is resumed playing.");
  }

  public static void stopSoundFX() {
    clipFx.stop();
    System.out.println("Sound is stopped playing.");
  }











}
