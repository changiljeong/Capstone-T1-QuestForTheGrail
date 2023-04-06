package com.questforholygrail.game.UI;

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
  private boolean examine;


  @Override
  public void keyTyped(KeyEvent e) {

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
      attack = true;
    }
    if(code == KeyEvent.VK_H) {
      heal = true;
    }
    if(code == KeyEvent.VK_T) {
      talk = true;
    }
    if(code == KeyEvent.VK_E) {
      examine = true;
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
}
