package pws;

import java.util.Random;

public class Opponent {
  private int health;
  private int attack;
  private int defense;
  private int x;
  private int y;
  private int mrange;
  private int arange;
  /*
   * WEET NOG NIET OF DIT WERKT, HET ZOU HANDIGER ZIJN ALS WE ALLES GEWOON IN CHARACTERS KUNNNE STOPPEN DUS 
   * NU VOORLOPIG DOOR MET ALLEEN 1 KANT IPV 2
   * 
   * 
   */
  public Opponent() {
	  // Spawn coordinaat 
	  Random r = new Random();
	  setX(r.nextInt((21 - 18) + 1)+ 17);
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
  
  public int getMrange() {
	  return mrange;
  }
  
  public void setMrange(int mrange) {
	  this.mrange = mrange;
  }
  
  public int getArange() {
	  return mrange;
  }
  
  public void setArange(int arange) {
	  this.arange = arange;
  }
}
