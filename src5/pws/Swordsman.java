package pws;

public class Swordsman extends Characters {
	
	public Swordsman() {
		int coreAccuracy = 85;
		int coreDamage = 70;
		int coreDefence = 40;
		int coreHealth = 100;
		int coreRange = 1;
		int coreStamina = 3;
		int coreHealing = 0;
		int coreSPR = 0;
		setInfo(0,13);
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