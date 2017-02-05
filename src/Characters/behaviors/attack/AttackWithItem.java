package Characters.behaviors.attack;

import Characters.Character;
import Items.Weapon;

public class AttackWithItem extends CombatBehavior {

	@Override
	public void attack(Character current, Character enemy) {
		if (enemy == null || current == null) {
			throw new NullPointerException();
		} else {		
			//die wahrscheinlichgkeit das ich den character treffe liegt bei 0
			if (coincidence() < enemy.getHitChance()) {
				System.out.printf("%s missed ... \n", current.getName());
			//sonst greife ich an 	
			} else {
				int damage = current.getAttackPower() + ((Weapon) current.getFightAttribute()).getPower();
				enemy.takeDamage(damage);
			}
		}
		
	}

	

}
