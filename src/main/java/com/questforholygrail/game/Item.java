package com.questforholygrail.game;
import java.util.List;
import java.util.Map;

public class Item {

  private String name;
  private Map<String, String> action;

  public Map<String, String> getAction() {
    return action;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Item{" +
        "name='" + name + '\'' +
        ", action=" + action +
        '}';
  }
}
