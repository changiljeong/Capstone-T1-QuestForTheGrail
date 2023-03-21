package com.questforholygrail.game;

public class Enemy {
  private String name;
  private int health;
  private int damage;
  private Location location;

  public Enemy(String name, int health, int damage, Location location) {
    this.name = name;
    this.health = health;
    this.damage = damage;
    this.location = location;
  }
  public String getName() {
    return name;
  }
  public int getHealth() {
    return health;
  }
  public void setHealth(int health) {
    this.health = health;
  }
  public int getDamage() {
    return damage;
  }
  public void attack(Player player) {
/*    player.takeDamage(damage);*/
  }
  public void setLocation(Location location) {
    this.location = location;
  }
  public Location getLocation() {
    return location;
  }
//  Location firstDungeon = locations[0];
//  Enemy chimera = new Enemy("Chimera", 50, 10, firstDungeon);
//  Location secondDungeon = locations[1];
//  Enemy minotaur = new Enemy("Minotaur", 75, 15, secondDungeon);
//  Location thirdDungeon = locations[2];
//  Enemy banshee = new Enemy("Banshee", 60, 12, thirdDungeon);
//  Location finalDungeon = locations[3];
//  Enemy ancientNasirax = new Enemy("Ancient Nasirax", 100, 20, finalDungeon);
}