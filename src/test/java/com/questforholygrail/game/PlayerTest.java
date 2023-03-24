package com.questforholygrail.game;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

Player player;

  @BeforeEach
  void setUp() {
    player = new Player("testName", 70, 20, null);
  }

  @Test
  void getAttack_sword() {
    Item item = new Item();
    item.setName("sword");
    player.getInventory().add(item);
    assertEquals(30, player.getAttack());
  }


  @Test
  void getAttack_noSword() {
    assertEquals(20, player.getAttack());
  }
}