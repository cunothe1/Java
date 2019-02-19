package pws;

public class Mage extends Characters {
	public Mage() {
		int coreAccuracy = 60;
		int coreDamage = 100;
		int coreDefence = 10;
		int coreHealth = 50;
		int coreRange = 500;
		int coreStamina = 3;
		int coreHealing = 0;
		setInfo(0,1);
		setInfo(1,coreAccuracy);
		setInfo(3,coreDamage);
		setInfo(4,coreDefence);
		setInfo(5,coreHealth);
		setInfo(6,coreRange);
		setInfo(7,coreStamina);
		setInfo(17,coreHealing);
	}
}