package Items;
/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
public class Bomb extends Item{
	
	public Bomb() {
		super("Bomb",100,5000);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the weigh
	 */
	public int getWeigh() {
		return weigh;
	}


}
