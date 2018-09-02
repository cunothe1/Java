package lessen.windesheim;

import java.util.ArrayList;

public class Circus {
	private String naam;
	private Artiest[] artiest;
	private ArrayList<CircusAct> Act;
	
	public Circus(String naam) {
		this.naam = naam;
		this.artiest = new Artiest[3];
		artiest[0] = new Artiest("James");
		artiest[1] = new Artiest("Jason");
		artiest[2] = new Artiest("Cameron");
		Act = new ArrayList<>();
	}
	
	public void printActOverzicht() {
		for(CircusAct b : Act) {
			System.out.print(b.getNaam() + " ");
			b.printAct();
		}
	}
	public void voegCircusActToe(CircusAct act) {
		Act.add(act);
	}
	public void printArtiesten() {
		for(int i = 0; i<artiest.length; i++) {
			System.out.println(i+1 + " " + artiest[i]);
		}
	}
	public String getAantalArtiesten() {
		return "Het aantal artiesten zijn " + artiest.length ;
	}
	public String toString() {
		return getAantalArtiesten();
	}
}
