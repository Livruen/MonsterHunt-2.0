package Characters.behaviors.heal;
/** Class describes the behavior of healing a Character that can use healing potion.
 * 	it is using the Strategy Pattern
 * **/

import Characters.Character;
import Items.HealingPotion;

public class WithPotion implements Healable {
	private final int HEALING_POWER = 20;
	private final String HPotion = "Healing potion";

	@Override
	public void restoreParams(Character current) {
		current.setHp(current.getMAXHP());

	}

	@Override
	public void drinkPotion(Character character) {

		if (havePotion(character)) {
			if (!(character.isMaxHP())) {
				character.getInventory().delete(HPotion);
				healUp(character);
			} else {
				System.out.println("Cannot heal HP is MAX");
			}
		} else {
			System.out.printf("%s doesn't have Healing potion", character.getName());
		}
		

	}

	private boolean havePotion(Character current) {
		return current.getInventory().isInList(HPotion);
	}

	public int random() {
		return (int) (Math.random() * 3);
	}

	@Override
	public void healUp(Character current) {
		current.setHp(current.getHp() + HEALING_POWER);
		System.out.println(current.getName() + " heals himself: + 20 HP.");

	}
}
