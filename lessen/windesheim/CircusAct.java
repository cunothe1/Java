package lessen.windesheim;
import java.util.ArrayList;

public class CircusAct {
	private String naam;
	private ArrayList<Artiest> artiesten;
	
	public CircusAct(String naam) {
		this.naam = naam;
		artiesten = new ArrayList<>();
	}
	public void voegArtiestToe(Artiest artiest) {
		artiesten.add(artiest);
	}
	public String getNaam() {
		return naam;
	}
	public void printAct() {
		for(Artiest a : artiesten) {
			System.out.println(artiesten.get(a));
		}
	}

}
