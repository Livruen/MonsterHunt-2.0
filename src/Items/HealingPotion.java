package Items;
/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */

public class HealingPotion extends Item {
	private final static int POWER = 10;
	public HealingPotion() {
		super("Healing potion",30,1000);
		increasePotionNumber();
	}
	public static int getPOWER() {
		return POWER;
	}
	
}