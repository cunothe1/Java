package pws;
import java.util.Random;

public class World {
	//=================================================================================================================
	//part 1 World genereation
	//=================================================================================================================
	public static String tile[][] = new String[25][17];
	
	public String[][] getTile() {
		return tile;
	}
	
	/* 
	 * syntax: randomRange(min,max);
	 * geeft een random waarde tussen min en max
	 */
	public static int randomRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	/*
	 * syntax: growPreset(type,i,j,k,l);
	 * Types: Ravine / River
	 */
	public static void growPreset(String type, int i,int j,int k,int l) {
		if (i+k>=1&&i+k<=24&&j+l>=1&&j+l<=16) { 
			if (tile[i+k][j+l]!="O"||tile[i+k][j+l]!="T") {
				if (type=="Ravine")tile[i+k][j+l] = "x";
				if (type=="River")tile[i+k][j+l] = "I";
	} 	}	}
	/*
	 * syntax: growSea(i,j,k,l);
	 * Wordt gebruikt door de wereld generator, en die notities
	 * zijn nodig om dit te begrijpen, dit is een herhaaldelijk
	 * deel en is daarom in een eigen functie.
	 * Werking:
	 * Als het pad niet wordt geblokkeert wordt er een I neergezet
	 * Als het pad wel wordt geblokkeerd dan wordt er in de vakken 
	 * achter de blokkade een m neergezet
	 */
	public static void growSea(int i, int j, int k, int l) {
		if (k>=1&&k<=24&&l>=1&&l<=16) {
			if ( tile[k][l]=="`" ) tile[k][l] = "I";
			if ( tile[k][l]!="`" && tile[k][l]!="I" && tile[k][l]!="Y") {
				int m=k;
				if (m>=i) { while (m<=i+3) { if (m>=1&&m<=24) if ( tile[m][l]=="`" ) tile[m][l] = "y"; m++; }}
				m=k;
				if (m<=i) { while (m>=i-3) { if (m>=1&&m<=24) if ( tile[m][l]=="`" ) tile[m][l] = "y"; m--; }}
				m=l;
				if (m>=j) { while (m<=j+3) { if (m>=1&&m<=16) if ( tile[k][m]=="`" ) tile[k][m] = "y"; m++; }}
				m=l;
				if (m<=j) { while (m>=j-3) { if (m>=1&&m<=16) if ( tile[k][m]=="`" ) tile[k][m] = "y"; m--; }}	
		}	}}
	
	/*
	 * Syntax: worldGeneration(forest,dunes,mountains,river,ravine,ocean)
	 */
	public static void worldGeneration(int pforest, int pdunes, int pmountains, int priver, int pravine, int pocean) {
		//tile[][]="<terrain><moveable><attackable><impassable>"
		//Als eerst worden wat waardes op false gezet
		//bijv of er een basis in het tweede kwadrant is, b2
		int i; int j;
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		boolean b4 = false;
		boolean t1 = false;
		boolean t2 = false;
		boolean base = false;
		boolean temple = false;
		//als eerst wordt overal een standaard waarde neergezet 
		//om nullpointerexceptions te stoppen
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) { tile[i][j]="0";} }
		//dit is waar het echt begint
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) {
			int r;
			//verdeling van spawnkans van landschappen
			//in promille
			r = randomRange(0,1000);
			int p=1000;
			if (r>p-pforest&&r<=p)tile[i][j]="i";  //forest
			p-=pforest;
			if (r>p-pdunes&&r<=p)tile[i][j]="-"; //dunes
			p-=pdunes;
			if (r>p-pmountains*10&&r<=p)tile[i][j]="M"; //mountains
			p-=pmountains*10;
			if (r>p-priver&&r<=p)tile[i][j]="~"; //river
			p-=priver;
			if (r>p-pravine&&r<=p)tile[i][j]="X"; //ravine
			p-=pravine;
			if (r>p-pocean&&r<=p) if (j==1||j==16) tile[i][j]="Y"; //oceaan
			p-=pocean;
			//temples en bases zijn wat lastiger, omdat het veld als eerst in
			//kwadranten verdeeld moet worden if i<a&&i<=b
			//vervolgens wordt gekeken of er geen temple is, if t1==false
			//dan wordt er gezegd dat een temple gemaakt mag worden en dat
			//er een tempel is het kwadrant is
			//vervolgens wordt de temple gemaakt.
			if (r>p-200&&r<=p-100) { //temple
				if (i>2 && i<=7 && t1==false) {temple = true; t1 = true;}
				if (i>17 && i<=22 && t2==false) {temple = true; t2 = true;}
				if (temple==true) {
					tile[i][j]="T";
					temple = false;
			}	}
			// voor bases geld hetzelfde als voor tempels
			if (r>p-100&&r<=p) { //base
				if (i>2 && i<=7 && b1==false) {base = true; b1 = true;}
				if (i>7 && i<=12 && b2==false) {base = true; b2 = true;}
				if (i>12 && i<=17 && b3==false) {base = true; b3 = true;}
				if (i>17 && i<=22 && b4==false) {base = true; b4 = true;}
				if (base==true) {
					tile[i][j]="O";
					base = false;
			} 	}
			//plains
			if (r>0&&r<=p-200) tile[i][j]="`";
			//repairing non spawned temples and bases
			//omdat soms op een plaats een tempel wil spawnen,
			//maar dat niet kan omdat er al een in het kwadrant
			//is, blijft de waarde ongewijzigd en dat moet een 
			//plain worden
			if ( tile[i][j]=="0" ) tile[i][j] = "`";
			}
		}
		
		//growing sea
		//als eerst kijkt die of er een oceanspawn blok is (Y)
		//vervolgens wordt er in de omringende blokken, 7x5
		//een tijdelijk waterblok gezet, zie: growSea()
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) {
			if ( tile[i][j]=="Y" ) {
				int k; int l;
				for (k=i-3;k<=i+3;k++) {  for (l=j-3;l<=j+3;l++) { growSea(i,j,k,l); } }
				for (k=i+3;k>=i-3;k--) {  for (l=j-3;l<=j+3;l++) { growSea(i,j,k,l); } }
				for (k=i-3;k<=i+3;k++) {  for (l=j+3;l>=j-3;l--) { growSea(i,j,k,l); } }
				for (k=i+3;k>=i-3;k--) {  for (l=j+3;l>=j-3;l--) { growSea(i,j,k,l); } }
		}} 	}
		//hier wordt de tijdelijke waterbblokken en de
		//water source blokken verandert in een waterblok
		//ook wordt de afgekeurde waterblokken teruggezet
		//naar plains
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) { if ( tile[i][j]=="y" ) tile[i][j] = "`"; } }
		

		//growing forest and dunes
		for (i=1;i<=24;i++){for(j=1;j<=16;j++) {
			if (tile[i][j]=="i"){ //is er een boom?
				//plaats dan in een + tijdelijke bomen rondom de bomen
				if (i>1) {if (tile[i-1][j]=="`") tile[i-1][j] = "j";}
				if (i<24) {if (tile[i+1][j]=="`") tile[i+1][j] = "j";}
				if (j>1) {if (tile[i][j-1]=="`") tile[i][j-1] = "j";}
				if (j<16) {if (tile[i][j+1]=="`") tile[i][j+1] = "j";}
			}
			if (tile[i][j]=="-"){ //is er een dune?
				//same als bij boom
				if (i>1) {if (tile[i-1][j]=="`") tile[i-1][j] = "d";}
				if (i<24) {if (tile[i+1][j]=="`") tile[i+1][j] = "d";}
				if (j>1) {if (tile[i][j-1]=="`") tile[i][j-1] = "d";}
				if (j<16) {if (tile[i][j+1]=="`") tile[i][j+1] = "d";}
			}
		}}
		// hier worden de tijdelijke bomen en dunes
		// omgezet in daadwerkelijke bomen.
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) { 
			if ( tile[i][j]=="j" ) tile[i][j] = "i";
			if ( tile[i][j]=="d" ) tile[i][j] = "-";
		}}
		
		//growing ravines and rivers
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) { 
			int r = randomRange(0,100);
			if (tile[i][j]=="X"&&r>0&&r<=25) {growPreset("Ravine",i,j,-2,0);growPreset("Ravine",i,j,+2,0);growPreset("Ravine",i,j,+1,0);growPreset("Ravine",i,j,-1,0);growPreset("Ravine",i,j,+2,+1);growPreset("Ravine",i,j,+3,+1);}
			if (tile[i][j]=="X"&&r>25&&r<=50) {growPreset("Ravine",i,j,-3,+1);growPreset("Ravine",i,j,-1,0);growPreset("Ravine",i,j,-2,0);growPreset("Ravine",i,j,-3,-1);growPreset("Ravine",i,j,+1,0);growPreset("Ravine",i,j,+2,-1);growPreset("Ravine",i,j,-2,+1);growPreset("Ravine",i,j,+2,0);growPreset("Ravine",i,j,-2,-1);}
			if (tile[i][j]=="X"&&r>50&&r<=75) {growPreset("Ravine",i,j,-2,-1);growPreset("Ravine",i,j,+1,0);growPreset("Ravine",i,j,0,-1);growPreset("Ravine",i,j,-1,-1);growPreset("Ravine",i,j,+1,+1);growPreset("Ravine",i,j,+2,+1);}
			if (tile[i][j]=="X"&&r>75&&r<=100) { growPreset("Ravine",i,j,-2,+1);growPreset("Ravine",i,j,+2,0);growPreset("Ravine",i,j,+1,0);growPreset("Ravine",i,j,-1,+1);growPreset("Ravine",i,j,+3,-1);}
			if (tile[i][j]=="~"&&r>0&&r<=100) {
			if (j==2) {growPreset("River",i,j,0,-1);growPreset("River",i,j,-1,-1);}
				if (j==3) {growPreset("River",i,j,+1,-1);growPreset("River",i,j,+1,-2);growPreset("River",i,j,0,-2);growPreset("River",i,j,0,-3);} 
				if (j==4) {growPreset("River",i,j,+1,-1);growPreset("River",i,j,+1,-2);growPreset("River",i,j,0,-2);growPreset("River",i,j,0,-3);growPreset("River",i,j,+1,-4);} 
				if (j==5) {growPreset("River",i,j,0,-1);growPreset("River",i,j,+1,-2);growPreset("River",i,j,+1,-3);growPreset("River",i,j,+1,-4);growPreset("River",i,j,0,-4);growPreset("River",i,j,0,-5);}
				if (j==6) {growPreset("River",i,j,0,-1);growPreset("River",i,j,+1,-2);growPreset("River",i,j,+1,-3);growPreset("River",i,j,0,-3);growPreset("River",i,j,0,-4);growPreset("River",i,j,-1,-4);growPreset("River",i,j,-1,-5);growPreset("River",i,j,0,-6);} 
				if (j==7) {growPreset("River",i,j,0,-1);growPreset("River",i,j,+1,-2);growPreset("River",i,j,+1,-3);growPreset("River",i,j,+2,-3);growPreset("River",i,j,+2,-4);growPreset("River",i,j,+1,-5);growPreset("River",i,j,0,-5);growPreset("River",i,j,0,-6);growPreset("River",i,j,0,-7);}
		}}	}
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) { if ( tile[i][j]=="x" ) tile[i][j] = "X"; } }
		//removing grass, volle estethica, er kon niet gewerkt worden
		//met spaties voor plains, dus gebruikte ik `, die worden nu 
		//teruggezet naar spaties.
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) { 
			if ( tile[i][j]=="`" ) tile[i][j] = " ";
			if ( tile[i][j]=="y" ) tile[i][j] = "I";
			if ( tile[i][j]=="Y" ) tile[i][j] = "I";
			if ( tile[i][j]=="I" ) tile[i][j] = "~";
		}}
		
		
	}

	//=================================================================================================================
	//part 2 Units
	//=================================================================================================================
	/*Unit database
	 * 0 unittype
	 * 1  Accuracy
	 * 2  Activated
	 * 3  Damage
	 * 4  Defense
	 * 5  Health
	 * 6  Range
	 * 7  Stamina
	 * 8  Selected
	 * 9  x
	 * 10 y
	 * 11 team
	 * 12 protected
	 * 13 RealX
	 * 14 RealY
	 */
	public int unitNew = 0;  //The amount of existing objects, used in for loops
	Characters unit[] = new Characters[255]; //the unitlist with a current cap of 255
	public int unitAt[][] = new int [24][16]; //the current unit on a certain tile
	
	/*syntax unitAdd("name", teamnumber);
	 * Adds a unit and gives it an ID
	 * */
	public void unitAdd(String type, int team) {
		unitNew+=1;
		if (type=="archer") unit[unitNew] = new Archer();
		//else if (type=="swordsman") unit[unitNew] = new Swordsman();
		else if (type=="lancer") unit[unitNew] = new Lancer();
		//else if (type=="commander") unit[unitNew] = new Commander();
		//else if (type=="healer") unit[unitNew] = new Healer();
		//else if (type=="builder") unit[unitNew] = new Builder();
		//else if (type=="paladin") unit[unitNew] = new Paladin();
		//else if (type=="knight") unit[unitNew] = new Knight();
		//else if (type=="mage") unit[unitNew] = new Mage();
		//else if (type=="trebuchet") unit[unitNew] = new Trebuchet();
		//else if (type=="assassin") unit[unitNew] = new Assassin();
		//else if (type=="summoner") unit[unitNew] = new Summoner();
		//else if (type=="summoned") unit[unitNew] = new Summoned();
		//else if (type=="houndmaster") unit[unitNew] = new Houndmaster();
		//else if (type=="templar") unit[unitNew] = new Templar();
		else return;
		unit[unitNew].setInfo(11, team);
		unit[unitNew].setInfo(2, 0);
	}
	
	
	/* syntax unitKill(unit ID);
	 * Kills an ID by setting its coords to 0
	 * an activates and changes the team of the target
	 * */
	public void unitKill(int ID) {
		unit[ID].setInfo(9, 0); // x naar 0
		unit[ID].setInfo(10, 0); // y naar 0
		unit[ID].setInfo(2, 1); //altijd al geactiveerd
		unit[ID].setInfo(11, 0); //naar team 0
	}
	
	/*syntax unitPlace(x,y);
	 * place a unit on a specific spot in tile cords
	 * */
	public void unitPlace(int ID, int x, int y) {
		unit[ID].setX(x*50+160);
		unit[ID].setY(y*50+10);
		unitAt[x][y]=ID;
	}
}