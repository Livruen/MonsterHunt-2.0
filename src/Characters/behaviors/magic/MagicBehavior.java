package Characters.behaviors.magic;

import Inventory.Inventory;
import Items.ManaPotion;

public interface MagicBehavior {
	public void setMana(int mana);
	public int getMana();
	public void setMAXMANA(int MAXMANA);
	public void restoreWithPotion(Inventory inventory);
	public void reduceMana();

}
