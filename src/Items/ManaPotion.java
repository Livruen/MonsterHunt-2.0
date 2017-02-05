package Items;
/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
public class ManaPotion extends Item {
	private final int POWER = 20;
	public ManaPotion( ) {
		super("Mana potion",20,1000);
		increasePotionNumber();
	}
	public int getPOWER() {
		return POWER;
	}
	

}
