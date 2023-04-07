package com.questforholygrail.game.ui;

import com.google.gson.Gson;
import com.questforholygrail.game.util.GameText;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class MainGameWindow extends JFrame {

    private GameText gameText;
    private final JPanel gameScreenPanel = new JPanel();
    private final GamePanel game = new GamePanel();

    private final UtilityWidgetPanel uwp;

    // constructor
    public MainGameWindow(int width, int height) {
        loadGameTextJSON();
        setSize(width, height);
        setResizable(false);
        JPanel titlePanel = new GameTitlePanel();
        JPanel introPanel = new GameIntroPanel();
        setLayout(null);
        gameScreenPanel.setLayout(new BorderLayout());
        gameScreenPanel.setBackground(Color.black);
        uwp = new UtilityWidgetPanel(this);
        gameScreenPanel.add(uwp, BorderLayout.LINE_END);
        gameScreenPanel.add(game);
        setUpTransition(titlePanel, introPanel);
        setVisible(true);
    }

    public void loadGameTextJSON() {

        Gson gson = new Gson();
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResourceAsStream("gametext.json")))) {
            gameText = gson.fromJson(reader, GameText.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void setUpTransition(JPanel panelOne, JPanel panelTwo){
        panelOne.setBounds(0, -50,968,576);
        if (panelTwo == gameScreenPanel) {
            panelTwo.setBounds(0, 0, 968, 576);
        } else {
            panelTwo.setBounds(0, -50,968,576);
        }
        add(panelOne);
        getRootPane().getActionMap().put("swapPanels", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panelOne);
                add(panelTwo);
                validate();
                setUpTransition(panelTwo, gameScreenPanel);

            }
        });
        if(panelTwo == gameScreenPanel) {
            game.requestFocus();
            game.setupGame();
            game.startGameThread();
            uwp.generateMiniMap();

        } else {
            getRootPane().getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "swapPanels");
        }

    }

    public GameText getGameText() {
        return gameText;
    }

    public void setGameText(GameText gameText) {
        this.gameText = gameText;
    }

    public JPanel getGameScreenPanel() {
        return gameScreenPanel;
    }

    public GamePanel getGame() {
        return game;
    }

    public UtilityWidgetPanel getUwp() {
        return uwp;
    }
}
