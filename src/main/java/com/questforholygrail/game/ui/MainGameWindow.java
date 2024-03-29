package com.questforholygrail.game.ui;

import com.google.gson.Gson;
import com.questforholygrail.game.util.GameText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel titlePanel = new GameTitlePanel();
        JPanel introPanel = new GameIntroPanel();
        gameScreenPanel.setLayout(new BorderLayout());
        gameScreenPanel.setBackground(Color.black);
        uwp = new UtilityWidgetPanel(this);
        gameScreenPanel.add(uwp, BorderLayout.LINE_END);
        gameScreenPanel.add(game);
        game.startGameThread();
        setUpTransition(titlePanel, introPanel);
        setVisible(true);
        setTitle("Quest for the Holy Grail");
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


    private void setUpTransition(JPanel panelOne, JPanel panelTwo) {
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
        if (panelTwo == gameScreenPanel) {
            game.requestFocus();
            game.setupGame();
            uwp.generateMiniMap();
            uwp.generateInventory();

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
