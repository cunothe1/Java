package pws;

import java.util.Random;

public class Characters {
  private int health;
  private int attack;
  private int defense;
  private int x;
  private int y;
  private int mrange;
  private int arange;
  
  public Characters() {
	  // Spawn coordinaat 
	  Random r = new Random();
	  setX(r.nextInt((3 - 1) + 1)* 50 + 160);
	  setY(r.nextInt((16 - 1) + 1)* 50 + 10);
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
  
  public void Move(int x, int y, int mrange) {

	  x = this.x;
	  y = this.y;
	  mrange = this.mrange;
	  if (x >= 160) { // MOET HIER NOG EEN NAAR RECHTS COMMAND TOEVOEGEN, WEET ALLEEN NIET HOE			  
		  x = x+(mrange*50);
	  }
	  
	  if (x >= 160) {	// MOET HIER NOG EEN NAAR LINKS COMMAND TOEVOEGEN	  
		  x = x-(mrange*50);
	  }
	  
	  if (y >= 10) {	// MOET HIER NOG EEN NAAR BOVEN COMMAND TOEVOEGEN	  
		  y = y+(mrange*50);
	  }
	  
	  if (y >= 10) {	// MOET HIER NOG EEN NAAR BENENDEN COMMAND TOEVOEGEN	  
		  y = y-(mrange*50);
	  }//bij deze code hoeft deze parameters zoals x>= 160 eigenlijk er niet in, maar gewoon if clicked, maar keyboard check en
	  //mouse check codes zijn moeilijk te begrijpen en vinden dus moet ff overdoen 
	  
  }
}
