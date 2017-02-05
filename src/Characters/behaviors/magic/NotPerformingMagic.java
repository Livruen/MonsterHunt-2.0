package Characters.behaviors.magic;

import Inventory.Inventory;

/**
 * This Class describes a behavior of Character that can't use Magic
 * 
 * **/

public class NotPerformingMagic implements MagicBehavior{

	@Override
	public void setMana(int mana) {
		System.out.println("Character is not performing magic");
		
	}

	@Override
	public int getMana() {
		System.out.println("Character is not performing magic");
		return 0;
	}

	@Override
	public void setMAXMANA(int MAXMANA) {
		System.out.println("Character is not performing magic");
		
	}


	@Override
	public void reduceMana() {
		
	}

	@Override
	public void restoreWithPotion(Inventory inventory) {
		// TODO Auto-generated method stub
		
	}

}
