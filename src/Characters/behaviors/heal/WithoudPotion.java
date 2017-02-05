package Characters.behaviors.heal;
import Characters.Character;
/** Class describes the behavior of healing a Character that can use healing potion.
 * 	it is using the Strategy Pattern
 * **/
import Items.HealingPotion;

public class WithoudPotion implements Healable {
	private final int HEALING_POWER = 20;
	@Override
	public void restoreParams(Character current) {
		current.setHp(current.getMAXHP());
		
	}

	

	@Override
	public void healUp(Character current) {
		current.setHp(current.getHp() + HEALING_POWER);
		
	}



	@Override
	public void drinkPotion(Character character) {
		
		
	}

}
