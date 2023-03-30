package com.questforholygrail.game;
import com.questforholygrail.game.UI.Display;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Objects;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

  private static AudioInputStream audioInputStream;
  private static AudioInputStream audioInputStreamFX;
  private static Clip clip;
  private static Clip clipFx;
  private static FloatControl gainControl;
  private static InputStream bis;
  private static boolean muted;

  public void soundLoad() {
    try {

      BufferedInputStream myStream = new BufferedInputStream(Objects.requireNonNull(
          this.getClass().getClassLoader().getResourceAsStream("DanseMacabre.wav")));
      AudioInputStream audio2 = AudioSystem.getAudioInputStream(myStream);

      clip = AudioSystem.getClip();
      clip.open(audio2);
      clip.start();
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void playSound() {
    clip.setFramePosition(0);
    clip.start();
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    Display.printScreenLn("Sound resumed playing.");
  }

  public static void stopSound() {
    clip.stop();
    Display.printScreenLn("Sound stopped playing.");
  }

  public static void reduceSound() {
    gainControl =(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

    gainControl.setValue(-10.0f);
    // Reduce volume by 10 decibels.
    Display.printScreenLn("Decrease volume by -10 decibels.");
  }

  public static void increaseSound() {
    gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(6.0f); // Increase volume by 6 decibels - Maximum allowed.
    Display.printScreenLn("Increased volume by 6 decibels (maximum allowed). If needed more, please increase it from your hardware setting.");
  }

  // --------------------------------------------------------------- //

  public void soundFXLoad(Player player) {
    String playerLocation = player.getLocation().getName();

    try {
        if(playerLocation.equals("The Gate of Trials")) {

          BufferedInputStream myStream = new BufferedInputStream(Objects.requireNonNull(
              this.getClass().getClassLoader().getResourceAsStream("Wolf.wav")));
          AudioInputStream audioInputStreamFX = AudioSystem.getAudioInputStream(myStream);

          clipFx = AudioSystem.getClip();
          clipFx.open(audioInputStreamFX);
          if (!isMuted()) {
            clipFx.start();
          }
        } else if (playerLocation.equals("Temple of Trials")) {
          BufferedInputStream myStream = new BufferedInputStream(Objects.requireNonNull(
              this.getClass().getClassLoader().getResourceAsStream("Witch.wav")));
          AudioInputStream audioInputStreamFX = AudioSystem.getAudioInputStream(myStream);

          clipFx = AudioSystem.getClip();
          clipFx.open(audioInputStreamFX);
          if (!isMuted()) {
            clipFx.start();
          }
        }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void playSoundFX() {
    clipFx.start();
    Display.printScreenLn("Sound resumed playing.");
  }

  public static void stopSoundFX() {
    clipFx.stop();
    Display.printScreenLn("Sound stopped playing.");
  }

  public static boolean isMuted() {
    return muted;
  }

  public static void setMuted(boolean muted) {
    Sound.muted = muted;
  }

}
