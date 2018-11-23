package pws;

import java.util.Random;

public class World {
	public static String tile[][] = new String[25][17];
	public String[][] getTile() {
		return tile;
	}
	
	/*
	 * syntax: random_range(min,max);
	 * geeft een random waarde tussen min en max
	 */
	public static int randomRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
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
			if ( tile[k][l].substring(0,1).contains("`") ) tile[k][l] = "I"+tile[k][l].substring(1);
			if (!tile[k][l].substring(0,1).contains("`") && !tile[k][l].substring(0,1).contains("I") && !tile[k][l].substring(0,1).contains("Y")) {
				int m=k;
				if (m>=i) { while (m<=i+3) {
					if (m>=1&&m<=24) {
						if ( tile[m][l].substring(0,1).contains("`") ) tile[m][l] = "y"+tile[m][l].substring(1);
					}
					m++;
				}}
				m=k;
				if (m<=i) { while (m>=i-3) {
					if (m>=1&&m<=24) {
						if ( tile[m][l].substring(0,1).contains("`") ) tile[m][l] = "y"+tile[m][l].substring(1);
					}
					m--;
				}}
				m=l;
				if (m>=j) { while (m<=j+3) {
					if (m>=1&&m<=16) {
						if ( tile[k][m].substring(0,1).contains("`") ) tile[k][m] = "y"+tile[k][m].substring(1);
					}
					m++;
				}}
				m=l;
				if (m<=j) { while (m>=j-3) {
					if (m>=1&&m<=16) {
						if ( tile[k][m].substring(0,1).contains("`") ) tile[k][m] = "y"+tile[k][m].substring(1);
					}
					m--;
				}}
				}
		}
	}
	
	/*
	 * De wereld generator
	 */
	public static void worldGeneration() {
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
		for (i=1;i<=24;i++) {
			for (j=1;j<=16;j++) {
				tile[i][j]="0";
			}}
		//dit is waar het echt begint
		for (i=1;i<=24;i++) {
			for (j=1;j<=16;j++) {
				int r;
				//verdeling van spawnkans van landschappen
				//in promille
				r = randomRange(0,1000);


				if (r>=0&&r<=610) tile[i][j]="`"+tile[i][j].substring(1); //plains
				if (r>=610&&r<=640)tile[i][j]="i"+tile[i][j].substring(1); //forest
				if (r>=640&&r<=670)tile[i][j]="-"+tile[i][j].substring(1); //dunes
				if (r>=670&&r<=720)tile[i][j]="M"+tile[i][j].substring(1); //mountains
				if (r>=720&&r<=735)tile[i][j]="~"+tile[i][j].substring(1); //water
				if (r>=735&&r<=740)tile[i][j]="X"+tile[i][j].substring(1); //ravine
				if (r>=740&&r<=800) { 
					//kijken of het wel aan de zijkant is
					if (j==1||j==16) tile[i][j]="Y"+tile[i][j].substring(1); //oceaan
				}
				//temples en bases zijn wat lastiger, omdat het veld als eerst in
				//kwadranten verdeeld moet worden if i<a&&i<=b
				//vervolgens wordt gekeken of er geen temple is if t1==false
				//dan wordt er gezegd dat een temple gemaakt mag worden en dat
				//er een tempel is het kwadrant is
				//vervolgens wordt de temple gemaakt.
				if (r>=800&&r<=900) { //temple
					if (i>2 && i<=7 && t1==false) {temple = true; t1 = true;}
					if (i>17 && i<=22 && t2==false) {temple = true; t2 = true;}
					if (temple==true) {
						tile[i][j]="T"+tile[i][j].substring(1);
						temple = false;
				} }
				// voor bases geld hetzelfde als voor tempels
				if (r>=900&&r<=1000) { //base
					if (i>2 && i<=7 && b1==false) { base = true; b1 = true;}
					if (i>7 && i<=12 && b2==false) { base = true; b2 = true;}
					if (i>12 && i<=17 && b3==false) {base = true; b3 = true;}
					if (i>17 && i<=22 && b4==false) {base = true; b4 = true;}
					if (base==true) {
						tile[i][j]="O"+tile[i][j].substring(1);
						base = false;
				} }
				
				//repairing non spawned temples and bases
				//omdat soms op een plaats een tempel wil spawnen,
				//maar dat niet kan omdat er al een in het kwadrant
				//is, blijft de waarde ongewijzigd en dat moet een 
				//plain worden
				if ( tile[i][j].substring(0,1).contains("0") ) tile[i][j] = "`"+tile[i][j].substring(1);
			}
		}
		
		//growing sea
		//als eerst kijkt die of er een oceanspawn blok is (Y)
		//vervolgens wordt er in de omringende blokken, 7x5
		//een tijdelijk waterblok gezet, zie: growSea()
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) {
				if ( tile[i][j].substring(0,1).contains("Y") ) {
					int k; int l;
					for (k=i-3;k<=i+3;k++) {  for (l=j-3;l<=j+3;l++) { growSea(i,j,k,l); } }
					for (k=i+3;k>=i-3;k--) {  for (l=j-3;l<=j+3;l++) { growSea(i,j,k,l); } }
					for (k=i-3;k<=i+3;k++) {  for (l=j+3;l>=j-3;l--) { growSea(i,j,k,l); } }
					for (k=i+3;k>=i-3;k--) {  for (l=j+3;l>=j-3;l--) { growSea(i,j,k,l); } }
		} } }
		//hier wordt de tijdelijke waterbblokken en de
		//water source blokken verandert in een waterblok
		//ook wordt de afgekeurde waterblokken teruggezet
		//naar plains
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) { if ( tile[i][j].substring(0,1).contains("y") ) tile[i][j] = "`"+tile[i][j].substring(1); } }
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) { if ( tile[i][j].substring(0,1).contains("Y") ) tile[i][j] = "I"+tile[i][j].substring(1); } }
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) { if ( tile[i][j].substring(0,1).contains("I") ) tile[i][j] = "~"+tile[i][j].substring(1); } }
		

		//growing forest and dunes
		for (i=1;i<=24;i++){for(j=1;j<=16;j++) {
			if (tile[i][j].substring(0,1).contains("i")){ //is er een boom?
				//plaats dan in een + tijdelijke bomen rondom de bomen
				if (i>1) {if (tile[i-1][j].substring(0,1).contains("`")) tile[i-1][j] = "j"+tile[i-1][j].substring(1);}
				if (i<24) {if (tile[i+1][j].substring(0,1).contains("`")) tile[i+1][j] = "j"+tile[i+1][j].substring(1);}
				if (j>1) {if (tile[i][j-1].substring(0,1).contains("`")) tile[i][j-1] = "j"+tile[i][j-1].substring(1);}
				if (j<16) {if (tile[i][j+1].substring(0,1).contains("`")) tile[i][j+1] = "j"+tile[i][j+1].substring(1);}
			}
			if (tile[i][j].substring(0,1).contains("-")){ //is er een dune?
				//same als bij boom
				if (i>1) {if (tile[i-1][j].substring(0,1).contains("`")) tile[i-1][j] = "d"+tile[i-1][j].substring(1);}
				if (i<24) {if (tile[i+1][j].substring(0,1).contains("`")) tile[i+1][j] = "d"+tile[i+1][j].substring(1);}
				if (j>1) {if (tile[i][j-1].substring(0,1).contains("`")) tile[i][j-1] = "d"+tile[i][j-1].substring(1);}
				if (j<16) {if (tile[i][j+1].substring(0,1).contains("`")) tile[i][j+1] = "d"+tile[i][j+1].substring(1);}
			}
		}}
		// hier worden de tijdelijke bomen en dunes
		// omgezet in daadwerkelijke bomen.
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) { 
			if ( tile[i][j].substring(0,1).contains("j") ) tile[i][j] = "i"+tile[i][j].substring(1);
			if ( tile[i][j].substring(0,1).contains("d") ) tile[i][j] = "-"+tile[i][j].substring(1);
			} }
		
		//removing grass, volle estethica, er kon niet gewerkt worden
		//met spaties voor plains, dus gebruikte ik `, die worden nu 
		//teruggezet naar spaties.
		for (i=1;i<=24;i++) { for (j=1;j<=16;j++) { if ( tile[i][j].substring(0,1).contains("`") ) tile[i][j] = " "+tile[i][j].substring(1); } }
		
		//Drawing
		//compleet gejat en verwisseld y met x
		//HEEL IRRITANT
		/* for ( i = 1; i < tile.length; i++) {
		    for ( j = 1; j < tile[i].length; j++) {
		    	System.out.print(tile[i][j] + " ");
		    }
		    System.out.println();
		}
		*/
		
	}
}