package pws;

import java.util.Random;

public class Characters {
  private int health;
  private int attack;
  private int defense;
  private int x;
  private int y;
  
  public Characters() {
	  // Spawn coordinaat 
	  Random r = new Random();
	  setX(r.nextInt((3 - 1) + 1));
	  setY(r.nextInt((16 - 1) + 1));
  }
  
  public int getHealth() {
	  return health;
  }
  
  public void setHealth(int health) {
	  this.health = health;
  }
  
  public int getAttack() {
	  return attack;
  }
  
  public void setAttack(int attack) {
	  this.attack = attack;
  }
  
  public int getDefense() {
	  return defense;
  }
  
  public void setDefense(int defense) {
	  this.defense = defense;
  }
  
  public int getX() {
	  return x;
  }
  
  public void setX(int x) {
	  this.x = x;
  }
  
  public int getY() {
	  return y;
  }
  
  public void setY(int y) {
	  this.y = y;
  }
}
