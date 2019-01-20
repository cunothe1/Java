package pws;

import java.util.Random;

public class Characters {
  private int info[] = new int[14];
  private int x;
  private int y;
  private int mrange;
  /*Unit database
	 * [0] unittype
	 * [1] Accuracy
	 * [2] Activated
	 * [3] Damage
	 * [4] Defense
	 * [5] Health
	 * [6] Range
	 * [7] Stamina
	 * [8] Selected
	 * [9] x
	 * [10] y
	 * [11] team
	 * [12] protected
	 * [13] real X
	 * [14] real Y
	 */
  public int getX() {return x;}
  public void setX(int x) {this.x = x;}
  public int getY() {return y;}
  public void setY(int y) {this.y = y;}
  
  public void setInfo(int data, int value) {
	  info[data]=value;
  }
  public int getInfo(int data) {
	  return info[data];
  }
  
  public Characters() {
	  // Spawn coordinaat 
	  Random r = new Random();
	  setX(r.nextInt((3 - 1) + 1)* 50 + 160);
	  setY(r.nextInt((16 - 1) + 1)* 50 + 10);
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
  
  /*syntax: move(x,y)
	 * beweegt naar de locatie
	 * haalt punten van de stamina af
	 * *//*
	public void move(int x, int y) {
		if (world.unitAt[x][y]==0) {
			world.unitAt[x][y]=world.unitAt[info[9]][info[10]];
			world.unitAt[info[9]][info[10]]=0;
			setX(x*50+160);
			setY(y*50+10);
			if (world.tile[x][y]=="i") {info[7]-=2; }//FOREST
	    	if (world.tile[x][y]=="-") {info[7]-=2; }//DUNES
	    	if (world.tile[x][y]=="M") {info[7]-=4; }//MOUNTAIN
	    	if (world.tile[x][y]=="~") {info[7]-=99; }//WATER
	    	if (world.tile[x][y]=="X") {info[7]-=99; }//RAVINE
	    	if (world.tile[x][y]==" ") {info[7]-=1; }//Plains
	    	if (world.tile[x][y]=="O") {info[7]-=1; }//BASE
	    	if (world.tile[x][y]=="T") {info[7]-=1; }//TEMPLE
		}
  	
	}*/
}