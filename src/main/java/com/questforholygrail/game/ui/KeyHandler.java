package com.questforholygrail.game.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  private boolean upPressed;
  private boolean downPressed;
  private boolean leftPressed;
  private boolean rightPressed;
  private boolean attack;
  private boolean heal;
  private boolean talk;
  private boolean startFX;
  private boolean examine;
  private boolean jPressed;
  private boolean kPressed;
  private boolean lPressed;
  private boolean iPressed;
  private boolean equipWeapon;



  GamePanel gp;

  public KeyHandler(GamePanel gp){
    this.gp = gp;
  }


  @Override
  public void keyTyped(KeyEvent e) {
    int code = e.getKeyCode();


  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    if(code == KeyEvent.VK_UP) {
      upPressed = true;
    }
    if(code == KeyEvent.VK_DOWN) {
      downPressed = true;
    }
    if(code == KeyEvent.VK_LEFT) {
      leftPressed = true;
    }
    if(code == KeyEvent.VK_RIGHT) {
      rightPressed = true;
    }
    if(code == KeyEvent.VK_A) {
      startFX = true;
      attack = true;
    }
    if(code == KeyEvent.VK_T) {
      startFX = true;
      talk = true;
    }
    if(code == KeyEvent.VK_E) {
      examine = true;
    }
    if(code == KeyEvent.VK_P) {
      if(gp.getGameState() == gp.getPlayState()){
        gp.setGameState(gp.getPauseState());
      }else if(gp.getGameState() == gp.getPauseState()){
        gp.setGameState(gp.getPlayState());
      }
    }
    if(code == KeyEvent.VK_I){
      if(gp.getUi().getSlotRow() !=0 ){
        gp.getUi().setSlotRow(gp.getUi().getSlotRow()-1);
      }
    }
    if(code == KeyEvent.VK_J) {
      if(gp.getUi().getSlotCol() !=0 ){
        gp.getUi().setSlotCol(gp.getUi().getSlotCol()-1);
      }
    }
    if(code == KeyEvent.VK_K) {
      if(gp.getUi().getSlotRow() !=4 ){
        gp.getUi().setSlotRow(gp.getUi().getSlotRow()+1);
      }
    }
    if(code == KeyEvent.VK_L) {
      if(gp.getUi().getSlotCol() !=4 ){
        gp.getUi().setSlotCol(gp.getUi().getSlotCol()+1);
      }
    }
    if(code == KeyEvent.VK_U){
      if(gp.getUi().getItemIndexOnSlot() < gp.getPlayer().getInventory().size()){
        if(gp.getPlayer().getInventory().get(
                gp.getUi().getItemIndexOnSlot())
            .getName().equalsIgnoreCase(("sword"))
            && equipWeapon == true){
          equipWeapon = false;
        } else if(gp.getPlayer().getInventory().get(
                gp.getUi().getItemIndexOnSlot())
            .getName().equalsIgnoreCase(("sword"))){
          equipWeapon = true;
        }
      }
    }


    if(code == KeyEvent.VK_H) {
      heal = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();

    if(code == KeyEvent.VK_UP) {
      upPressed = false;
    }
    if(code == KeyEvent.VK_DOWN) {
      downPressed = false;
    }
    if(code == KeyEvent.VK_LEFT) {
      leftPressed = false;
    }
    if(code == KeyEvent.VK_RIGHT) {
      rightPressed = false;
    }
    if(code == KeyEvent.VK_T) {
      talk = false;
    }
    if(code == KeyEvent.VK_E) {
      examine = false;
    }
    if(code == KeyEvent.VK_I){
      iPressed = false;
    }
    if(code == KeyEvent.VK_J) {
      jPressed = false;
    }
    if(code == KeyEvent.VK_K) {
      kPressed = false;
    }
    if(code == KeyEvent.VK_L) {
      lPressed = false;
    }

  }

  public boolean isUpPressed() {
    return upPressed;
  }

  public boolean isDownPressed() {
    return downPressed;
  }

  public boolean isLeftPressed() {
    return leftPressed;
  }

  public boolean isRightPressed() {
    return rightPressed;
  }

  public boolean isAttack() {
    return attack;
  }

  public void setAttack(boolean attack) {
    this.attack = attack;
  }

  public boolean isHeal() {
    return heal;
  }

  public void setHeal(boolean heal) {
    this.heal = heal;
  }

  public boolean isTalk() {
    return talk;
  }

  public void setTalk(boolean talk) {
    this.talk = talk;
  }

  public boolean isExamine() {
    return examine;
  }

  public void setExamine(boolean examine) {
    this.examine = examine;
  }

  public boolean isStartFX() {
    return startFX;
  }

  public void setStartFX(boolean startFX) {
    this.startFX = startFX;
  }

  public boolean jPressed() {
    return jPressed;
  }

  public boolean kPressed() {
    return kPressed;
  }

  public boolean lPressed() {
    return lPressed;
  }

  public boolean iPressed() {
    return iPressed;
  }

  public boolean isEquipWeapon() {
    return equipWeapon;
  }

  public void setEquipWeapon(boolean equipWeapon) {
    this.equipWeapon = equipWeapon;
  }
}
