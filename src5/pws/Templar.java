package pws;

public class Templar extends Characters {
	public Templar() {
		int coreAccuracy = 70;
		int coreDamage = 70;
		int coreDefence = 40;
		int coreHealth = 180;
		int coreRange = 1;
		int coreStamina = 8;
		int coreHealing = 0;
		int coreSPR = 0;
		setInfo(0,14);
		setInfo(1,coreAccuracy);
		setInfo(3,coreDamage);
		setInfo(4,coreDefence);
		setInfo(5,coreHealth);
		setInfo(6,coreRange);
		setInfo(7,coreStamina);
		setInfo(17,coreHealing);
		setInfo(18,coreSPR);
	}
}