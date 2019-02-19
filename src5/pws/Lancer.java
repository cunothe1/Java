package pws;

public class Lancer extends Characters {
	
	public Lancer() {
		int coreAccuracy = 75;
		int coreDamage = 80;
		int coreDefence = 30;
		int coreHealth = 100;
		int coreRange = 2;
		int coreStamina = 2;
		int coreHealing = 0;
		int coreSPR = 0;
		setInfo(0,8);
		setInfo(1,coreAccuracy);
		setInfo(3,coreDamage);
		setInfo(4,coreDefence);
		setInfo(5,coreHealth);
		setInfo(6,coreRange);
		setInfo(7,coreStamina);
		setInfo(16,coreHealing);
		setInfo(17,coreSPR);
	}
}