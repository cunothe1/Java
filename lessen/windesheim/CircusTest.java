package lessen.windesheim;

public class CircusTest {

	public static void main(String[] args) {
		System.out.println("---------------");
		Circus c1 = new Circus("Clown");
		CircusAct ca1 = new CircusAct("Trapeze");
		CircusAct ca2 = new CircusAct("Trampoline");
		CircusAct ca3 = new CircusAct("Juggling");
		
		ca1.voegArtiestToe(new Artiest("James"));
		ca2.voegArtiestToe(new Artiest("Jason"));
		ca2.voegArtiestToe(new Artiest("Cameron"));
		ca3.voegArtiestToe(new Artiest("Cameron"));
		
		c1.voegCircusActToe(new CircusAct("Trapeze"));
		c1.voegCircusActToe(new CircusAct("Trampoline"));
		c1.voegCircusActToe(new CircusAct("Juggling"));
		c1.printActOverzicht();
	}

}
