package com.questforholygrail.game.UI;


import com.questforholygrail.game.Item;
import com.questforholygrail.game.Commands;
import com.questforholygrail.game.Location;
import com.questforholygrail.game.Main;
import com.questforholygrail.game.NPC;
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
  private DialogScreen dialog;


  //World setting
  private final int maxWorldCol = 200;
  private final int maxWorldRow = 200;


  private final int worldWidth = tileSize * maxWorldCol;
  private final int worldHeight = tileSize * maxWorldRow;

  private final double FPS = 60.0;

  private final TileManager tileManager = new TileManager(this);
  private final KeyHandler keyHandler = new KeyHandler();
  Thread gameThread;


  CollisionChecker cChecker = new CollisionChecker(this);
  private AssetSetter aSetter = new AssetSetter(this);

  private Player player;

  private transient Item[] obj = new Item[10];

  public GamePanel(){
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyHandler);
    this.setFocusable(true);
    player = new Player(this, keyHandler);
    player.setHealth(100);
    player.setAttack(5);

  }

  public void setupGame(){
    AssetSetter.setNPC(this);
    aSetter.setObject();
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
     dialog = new DialogScreen(this, g2);

     tileManager.draw(g2);



    //Object draw
    for(int i=0; i<getObj().length; i++){
      if(getObj()[i] != null){
        getObj()[i].draw(g2, this);
      }
    }

     player.draw(g2);

    //handles NPCs


    if(Commands.getCurrentLocation() != null && Commands.getCurrentLocation().getNpc().size() > 0){
      if (Commands.getCurrentLocation().getNpc().get(0).isDefeated()){
        Commands.getCurrentLocation().getNpc().remove(0);
      } else {
        Commands.getCurrentLocation().getNpc().get(0).draw(g2, this);
      }

    }
    Commands.look();
    Commands.talk();
    Commands.playRiddle();
    g2.dispose();


  }

  @Override
  public void run() {

    double drawInterval = 1000000000 / FPS;
    double nextDrawTime = System.nanoTime() + drawInterval;


    while(gameThread != null) {

      updateLocation();
      update();
      repaint();
      Main.playGame();

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

  private void updateLocation(){
    if( player != null) {
      int playerX = player.getWorldX();
      int playerY = player.getWorldY();

      //handles location change
      for (Location loc : Main.getLocations()) {
        if (playerX <= loc.getMaxX() * tileSize && playerX >= loc.getMinX() * tileSize
            && playerY <= loc.getMaxY() * tileSize && playerY >= loc.getMinY() * tileSize) {
          player.setLocation(loc);
          Commands.setCurrentLocation(loc);
          break;
        }
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

  public AssetSetter getaSetter() {
    return aSetter;
  }

  public Item[] getObj() {
    return obj;
  }
  public KeyHandler getKeyHandler() {
    return keyHandler;
  }
  public TileManager getTileManager() {
    return tileManager;
  }

  public CollisionChecker getcChecker() {
    return cChecker;
  }

  public void setcChecker(CollisionChecker cChecker) {
    this.cChecker = cChecker;
  }

  public DialogScreen getDialog() {
    return dialog;
  }

  public void setDialog(DialogScreen dialog) {
    this.dialog = dialog;
  }
}
