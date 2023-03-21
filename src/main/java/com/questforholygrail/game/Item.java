package com.questforholygrail.game;
import java.util.List;
import java.util.Map;

public class Item {

  private String name;
  private Map<String, String> action;
  private List<Item> items;

  //Empty constructor. Add parameters as needed.
  public Item() {}

  public Map<String, String> getAction() {
    return action;
  }

  public void setAction(Map<String, String> action) {
    this.action = action;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

}
