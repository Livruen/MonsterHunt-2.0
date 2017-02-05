package Items;

import java.io.Serializable;


/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
public abstract class Item implements Serializable {
	protected final String name;
	protected final int value;
	protected final int weigh;
	public int potionNumber;

	protected Item(String name, int value, int weigh) {
		this.name = name;
		this.value = value;
		this.weigh = weigh;
		this.potionNumber = 0;
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
	public int getPotionNumber() {
		return potionNumber;
	}
	public void increasePotionNumber() {
		this.potionNumber++;
	}
	public boolean decreasePotionNumber() {		
			this.potionNumber--;
			return true;
	}
}
