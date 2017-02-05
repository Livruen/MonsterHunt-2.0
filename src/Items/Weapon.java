package Items;

/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
public class Weapon extends Item {
	private int power;

	public Weapon(String name, int value, int power) {
		super(name, value, 5000);
		this.power = power;
	}

	public int getPower() {
		return this.power;
	}

}
