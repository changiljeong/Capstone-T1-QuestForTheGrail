package com.questforholygrail.game.ui;

import com.questforholygrail.game.Sound;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SettingsPopUp extends JOptionPane {

    private final JSlider musicSlider = new JSlider(JSlider.HORIZONTAL, -30, 7, 0);
    private final JCheckBox soundEffects = new JCheckBox("Sound Effects");
    private final MainGameWindow frame;

    public SettingsPopUp(MainGameWindow frame) {
        this.frame = frame;
        soundEffects.setSelected(true);
        this.setMessage(new Object[]{"Adjust music volume:", musicSlider, soundEffects});
        soundEffects.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Sound.changeEffectsVolume(e.getStateChange());
            }
        });
        musicSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider sliderUnderFocus = (JSlider) e.getSource();
                if (!sliderUnderFocus.getValueIsAdjusting()) {
                    float musicVolume = sliderUnderFocus.getValue();
                    Sound.changeMusicVolume(musicVolume);
                }
            }
        });

        this.setMessageType(JOptionPane.PLAIN_MESSAGE);
    }

    public void displaySettingsOptions() {
        JDialog dialog = this.createDialog(frame, "Settings");
        dialog.setVisible(true);
    }


}