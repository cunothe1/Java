package pws;

import java.util.Random;

public class Characters {
  private int info[] = new int[18];
  private int x;
  private int y;
  private int mrange;
  private World world;
  /*Unit database
   * Complete
   * Archer/Lancer
   * 
   * 
   * 
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
	 * [12] protector ID   1=unprotected
	 * [13] real X
	 * [14] real Y
	 * [15] ID tag (LOL)
	 * [16] healpower
	 * [17] 
	 */
  public int getX() {return x;}
  public void setX(int x) {this.x = x;}
  public int getY() {return y;}
  public void setY(int y) {this.y = y;}
  
  public void setInfo(int data, int value) {
	  info[data]=value;
  }
  public int getInfo(int data) {
	  int a=0;
	  a=info[data];
	  return a;
  }
  public void setWorld(World w) {
	  world=w;
  }
  
  public Characters() {
	  // Spawn coordinaat 
	  //Random r = new Random();
	  //setX(r.nextInt((3 - 1) + 1)* 50 + 160);
	  //setY(r.nextInt((16 - 1) + 1)* 50 + 10);
  }
  
  
  
  /*syntax: move(x,y)
	 * beweegt naar de locatie
	 * haalt punten van de stamina af
	 * */
	public void move(int a, int b) {
		if (world.getUnitAt(a,b)==world.unit[1]) {
        	int c; c=0;
			if (world.getTile(a,b)=="forest") {c=2; }//FOREST
	    	if (world.getTile(a,b)=="dunes") {c=2; }//DUNES
	    	if (world.getTile(a,b)=="mountain") {c=4; }//MOUNTAIN
	    	if (world.getTile(a,b)=="water") {c=99; }//WATER
	    	if (world.getTile(a,b)=="ravine") {c=99; }//RAVINE
	    	if (world.getTile(a,b)=="plains") {c=1; }//Plains
	    	if (world.getTile(a,b)=="base") {c=1; }//BASE
	    	if (world.getTile(a,b)=="temple") {c=1; }//TEMPLE

	    	if (c<=getInfo(7)) {
	    		world.unitPlace(getInfo(15), a, b);
	    		setInfo(7, getInfo(7)-c);
	    	}
		}
  	
	}
	/*syntax: attack(ID)
	 * beweegt naar de locatie
	 * haalt punten van de stamina af
	 * */
	public void attack(int ID) {
		//calculate distance
		if (ID>1) {
		int distance = Math.abs(world.unit[ID].getInfo(9) - getInfo(9)) + Math.abs(world.unit[ID].getInfo(10) - getInfo(10));
		if (distance<=getInfo(6)) { 
			Characters other = world.unit[ID];
			if (other.getInfo(12)!=1) other=world.unit[other.getInfo(12)];
			int r = world.randomRange(0, 100);
			if (r<=getInfo(1)) { // in range
				//calculate attack
				int damage = 10;
				if (getInfo(3)>other.getInfo(4)+9) {
					damage=getInfo(3)-other.getInfo(4);
				}
				other.setInfo(5, other.getInfo(5)-damage);
				setInfo(2,1);
				setInfo(7,0);
				//look if died
				if (other.getInfo(5)<=0) world.unitKill(ID);
			}
		}}
	}
}