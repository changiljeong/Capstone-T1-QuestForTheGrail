package com.questforholygrail.game.UI;

import com.questforholygrail.game.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

//this class was created with the aid of RyiSnow's YouTube tutorial for 2d Games

public class GamePanel extends JPanel implements Runnable {

  //Screen Settings
  private final int originalTileSize = 16;
  private final int scale = 3;

  private final int tileSize = originalTileSize * scale;
  private final int maxScreenCol = 16;
  private final int maxScreenRow = 12;
  private final int screenWidth = tileSize * maxScreenCol;
  private final int screenHeight = tileSize * maxScreenRow;


  //World setting
  private final int maxWorldCol = 34;
  private final int maxWorldRow = 52;


  private final int worldWidth = tileSize * maxWorldCol;
  private final int worldHeight = tileSize * maxWorldRow;

  private final double FPS = 60.0;

  private TileManager tileManager = new TileManager(this);
  KeyHandler keyHandler = new KeyHandler();
  Thread gameThread;

  public TileManager getTileManager() {
    return tileManager;
  }

  public CollisionChecker getcChecker() {
    return cChecker;
  }

  public void setcChecker(CollisionChecker cChecker) {
    this.cChecker = cChecker;
  }

  CollisionChecker cChecker = new CollisionChecker(this);

  private Player player;

  public GamePanel(){
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyHandler);
    this.setFocusable(true);
    player = new Player(this, keyHandler);

  }

  public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
  }


  public void update(){

    player.update();


  }


  @Override
  public void paintComponent(Graphics g){
     super.paintComponent(g);
     Graphics2D g2 = (Graphics2D) g;

     tileManager.draw(g2);
     player.draw(g2);
     g2.dispose();

  }

  @Override
  public void run() {

    double drawInterval = 1000000000 / FPS;
    double nextDrawTime = System.nanoTime() + drawInterval;


    while(gameThread != null) {

      update();
      repaint();

      try {
        double remainingTime = nextDrawTime - System.nanoTime();
        remainingTime = remainingTime /  1000000;

        if(remainingTime < 0) {
          remainingTime = 0;
        }

        //noinspection BusyWait
        Thread.sleep((long) remainingTime);
        nextDrawTime += drawInterval;

      } catch (InterruptedException e) {
        e.printStackTrace();
      }



    }
  }

  public int getOriginalTileSize() {
    return originalTileSize;
  }

  public int getScale() {
    return scale;
  }

  public int getTileSize() {
    return tileSize;
  }

  public int getMaxScreenCol() {
    return maxScreenCol;
  }

  public int getMaxScreenRow() {
    return maxScreenRow;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  public int getMaxWorldCol() {
    return maxWorldCol;
  }

  public int getMaxWorldRow() {
    return maxWorldRow;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
