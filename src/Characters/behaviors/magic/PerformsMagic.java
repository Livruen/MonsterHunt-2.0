package Characters.behaviors.magic;

import Inventory.Inventory;
import Items.ManaPotion;

public class PerformsMagic implements MagicBehavior {
	private final int MAGICPOWER = 10;
	private String MPotion = "Mana potion";
	private int MAXMANA;
	private int mana;

	@Override
	public void setMana(int mana) {
		this.mana = mana;

	}

	@Override
	public int getMana() {
		return mana;
	}

	@Override
	public void setMAXMANA(int MAXMANA) {
		this.MAXMANA = MAXMANA;
	}

	@Override
	public void restoreWithPotion(Inventory inventory) {

		if (MAXMANA()) {
			System.out.println("You don't need to use Mana Potion.");
		} else {
			if (inventory.haveManaPotion()) {

				ManaPotion manaPotion = (ManaPotion) inventory.getPotion(MPotion);
				setMana(getMana() + manaPotion.getPOWER());
				alignParameters();
				inventory.delete(MPotion);
				System.out.println(" + 30 MANA ");

			} else {
				System.out.println("No mana Potion.");
				if (emptyMana()) {
					System.out.println("The magician has no mana and no mana potion.");
				}
			}
		}
	}

	@Override
	public void reduceMana() {
		setMana(getMana() - MAGICPOWER);

	}

	private boolean MAXMANA() {
		return this.mana == this.MAXMANA;
	}

	private void alignParameters() {
		if (mana > MAXMANA) {
			setMana(MAXMANA);
		}
	}

	private boolean emptyMana() {
		return mana <= 0;
	}

}
