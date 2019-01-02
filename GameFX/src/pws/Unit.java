package pws;
import java.util.Random;



public class Unit {
	public static int unitAt[][] = new int[25][17];
	public static int unit[][] = new int[640][1];
	public static int core[][] = new int[16][1];
	public static int unitNew;
	public static int selected;
	public static int infoMax = 12;
	/*Unit database
	 * [ID][0] unittype
	 * [ID][1] Accuracy
	 * [ID][2] ==Activated
	 * [ID][3] Damage
	 * [ID][4] Defense
	 * [ID][5] Health
	 * [ID][6] Range
	 * [ID][7] Stamina
	 * [ID][8] ==Selected
	 * [ID][9] ==x
	 * [ID][10]==y
	 * [ID][11]==team
	 * [ID][12]==protected
	 * Core Database
	 * [ID][0] unittype
	 * [ID][1] Accuracy
	 * [ID][2] ==1
	 * [ID][3] Damage
	 * [ID][4] Defense
	 * [ID][5] Health
	 * [ID][6] Range
	 * [ID][7] Walk Distance
	 * [ID][8] ==0
	 * [ID][9] ==MovementType
	 * [ID][10]==
	 * [ID][11]==0
	 * */
	
	public void setup() {
		unitNew=0;
	}
	
	
	/*syntax Loc(x,y)
	 * geeft de unit id op deze locatie
	 * */
	public int Loc(int x,int y) {
		return unitAt[x][y];
	}
	public int getInfo(int id, int info) {
		return unit[id][info];
	}
	public void setLoc(int x, int y, int id) {
		unitAt[x][y]=id;
	}
	/*syntax unitAdd(unitname,x,y)
	 * Voeg een unit toe op de locatie
	 * */
	public void unitAdd(int x, int y, int unitType, int team) {
		if (unitAt[x][y]==0) {
			unitNew+=1;
			int i;
			for(i=0;i<=infoMax;i+=1) {
				unit[unitNew][i]=core[unitType][i];
			}
			unit[unitNew][9]=x;
			unit[unitNew][10]=y;
			unit[unitNew][11]=team;
		}
	}
	
	/* syntax select();
	 * selecteer een unit
	 * */
	public void select() {
		int tx; 
		int ty;
		int i;
		tx=1; //INSERT mouse cords to tile cords
		ty=1; //INSERT mouse cords to tile cords
		selected=Loc(tx,ty);
		for (i=1;i<=unitNew;i+=1) unit[i][8]=0;
		unit[selected][8]=1;
	}
	
	public void attackSelect(int id) {}//laat alle stats zien
	
	/*syntax attackConfirmed(id)
	 * % kans om aan te vallen
	 * attacked health -= damage-defense
	 * dood de aangevalde als zijn hp minder dan 0 is
	 * */
	public void attackConfirmed(int id) {
		if (World.randomRange(0,100)<unit[selected][1]) {
			if (unit[selected][3]-unit[id][4]>0) { 
				unit[id][5]-=unit[selected][3]-unit[id][4];
				} else unit[id][5]-=1;
			if (unit[selected][5]<=0) unitKill(id);
		}
	}
	
	public void visibleAttack() {} //laat tiles zien die aanvalbaar zijn
	public void visbleMove() {} //laat tiles zien die beschikbaar zijn
	
	/*syntax: move(x,y)
	 * beweegt naar de locatie
	 * haalt punten van de stamina af
	 * */
	public void move(int x, int y) {
		if (Loc(x,y)==0) {
			setLoc(unit[selected][9],unit[selected][10],0);
			setLoc(x,y,selected);
			unit[selected][9]=x;
			unit[selected][10]=y;
			if(World.tile[x][y]=="i") {unit[selected][7]-=2; }//FOREST
	    	if(World.tile[x][y]=="-") {unit[selected][7]-=2; }//DUNES
	    	if(World.tile[x][y]=="M") {unit[selected][7]-=4; }//MOUNTAIN
	    	if(World.tile[x][y]=="~") {unit[selected][7]-=99; }//WATER
	    	if(World.tile[x][y]=="X") {unit[selected][7]-=99; }//RAVINE
	    	if(World.tile[x][y]==" ") {unit[selected][7]-=1; }//Plains
	    	if(World.tile[x][y]=="O") {unit[selected][7]-=1; }//BASE
	    	if(World.tile[x][y]=="T") {unit[selected][7]-=1; }//TEMPLE
		}
    	
	}
	
	/*syntax: unitKill(id)
	 * verwijdert de unit met het id van het bord
	 * */
	public void unitKill(int id) {
		setLoc(unit[id][9],unit[id][10],0); //uit het spel halen
		unit[id][9]=0; // x naar 0
		unit[id][10]=0; // y naar 0
		unit[id][2]=1; //altijd al geactiveerd
		unit[id][11]=0; //naar team 0
	}
	
}
