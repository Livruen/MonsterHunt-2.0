package Characters.behaviors.heal;

import Characters.Character;
import Items.HealingPotion;

public interface Healable {
	public void healUp(Character current);
	public void restoreParams(Character current);
	void drinkPotion(Character character);
}
