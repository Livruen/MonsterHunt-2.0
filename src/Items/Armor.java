package Items;
/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
public class Armor extends Item {
	private int resistence;
	
	public Armor(String name, int value, int resistence) {
		super(name,value,5000);
		this.resistence = resistence;
	}
	public int getResistence() {
		return this.resistence;
	}
}
