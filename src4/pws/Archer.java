package pws;

public class Archer extends Characters {
	public Archer() {
		int coreAccuracy = 60;
		int coreDamage = 100;
		int coreDefence = 10;
		int coreHealth = 50;
		int coreRange = 5;
		int coreStamina = 3;
		setInfo(0,1);
		setInfo(1,coreAccuracy);
		setInfo(3,coreDamage);
		setInfo(4,coreDefence);
		setInfo(5,coreHealth);
		setInfo(6,coreRange);
		setInfo(7,coreStamina);
	}
}