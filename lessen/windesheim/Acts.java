package lessen.windesheim;

public class Acts {
	private String naam;
	private Artiest artiest;
	
	public Acts(String naam, Artiest artiest) {
		this.naam = naam;
		this.artiest = artiest;
	}
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String toString() {
		return naam + " door artiest " + artiest;
	}
	
}
