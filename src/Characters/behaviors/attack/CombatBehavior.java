package Characters.behaviors.attack;

import Characters.Character;

public abstract class CombatBehavior {
	abstract public void attack(Character current, Character enemy);
	public void takeDamage(Character character, int damage) {
		if (character.getHp() <= 0) {
			System.out.printf("%s is dead \n", character.getName());			
		} else {
			character.setHp(character.getHp() - damage);
		}
	}

	public void flee(Character current) {
		System.out.printf("%s flee.", current.getName());
	}

	public int coincidence() {
			return (int) (Math.random() * 3);	
	}
}
