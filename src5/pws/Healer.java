package pws;

public class Healer extends Characters {
	
	public Healer() {
		int coreAccuracy = 0;
		int coreDamage = 0;
		int coreDefence = 0;
		int coreHealth = 50;
		int coreRange = 0;
		int coreStamina = 5;
		int coreHealing = 20;
		int coreSPR = 4;
		setInfo(0,5);
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