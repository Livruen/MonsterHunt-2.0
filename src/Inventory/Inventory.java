package Inventory;

/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
import java.io.Serializable;
import java.util.LinkedList;

import Items.HealingPotion;
import Items.Item;
import Items.ManaPotion;

public class Inventory implements List, Serializable {

	private LinkedList<Item> list;
	private final String HPotion = "Healing potion";
	private final String MPotion = "Mana potion";
	private final int MAXInventorySize = 10;

	public Inventory() {
		list = new LinkedList<Item>();
	}

	@Override
	public int length() {
		return list.size();
	}

	public boolean isInList(String x) {
		return this.indexOf(x) > -1 ? true : false;
	}

	@Override
	public boolean isInList(Item x) {
		return isEmpty() ? false : list.contains(x);
	}

	@Override
	public Item firstItem() throws IllegalStateException {
		return isEmpty() ? null : list.getFirst();
	}

	@Override
	public Item getItem(int i) throws IndexOutOfBoundsException {
		return isEmpty() ? null : list.get(i);
	}

	@Override
	public List insert(Item x) {
		append(x);
		return this;
	}

	@Override
	public List append(Item x) {
		if (list.size() == MAXInventorySize) {
			System.out.println("Inventory is full please sell something.");
			return this;
		} else {
			if (x instanceof HealingPotion) {
				if (isInList(HPotion)) {

					Item potion = list.get(indexOf(HPotion));
					if (potion.potionNumber < 6) {
						potion.increasePotionNumber();
					} else {
						list.addLast(x);
						x.increasePotionNumber();
					}
				} else {
					list.addLast(x);
					x.increasePotionNumber();
				}
			} else if (x instanceof ManaPotion) {
				if (isInList(MPotion)) {
					Item potion = list.get(indexOf(MPotion));
					if (potion.potionNumber < 6) {
						potion.increasePotionNumber();
					} else {
						list.addLast(x);
						x.increasePotionNumber();
					}
				} else {
					list.addLast(x);
					x.increasePotionNumber();
				}
			} else {
				list.addLast(x);
			}
			return this;
		}
	}

	public void delete(String s) {
		delete(this.getItem(this.indexOf(s)));
	}

	@Override
	public List delete(Item x) {
		int potionNr = 0;
		if (isEmpty()) {
			return null;
		}
		if (x instanceof HealingPotion) {
			potionNr = x.getPotionNumber();
			if (potionNr == 1) {
				list.remove(x);
				return this;
			} else {
				x.decreasePotionNumber();
				return this;
			}
		} else if (x instanceof ManaPotion) {
			potionNr = x.getPotionNumber();
			if (potionNr == 1) {
				list.remove(x);
				return this;
			} else {
				x.decreasePotionNumber();
				return this;
			}
		} else {
			list.remove(x);
			return this;
		}
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void showInventory() {

		System.out.println("+..................INVENTORY........+");
		for (int i = 0; i < list.size(); i++) {
			Item item = getItem(i);
			if (item instanceof HealingPotion || item instanceof ManaPotion) {
				System.out.printf("| %3d | %3d  | %20s |\n", i,
						item.potionNumber, item.getName());
			} else {
				System.out.printf("| %3d |      | %20s |\n", i, item.getName());
			}

		}
		System.out.println("+...................................+");
	}

	public Item getPotion(String potionName) {
		if (isInList(potionName)) {
			return list.get(this.indexOf(potionName));
		} else {
			return null;
		}
	}

	public int indexOf(String s) {
		for (int i = 0; i < this.length(); i++) {
			if (list.get(i).getName().equals(s)) {
				return i;
			}
		}
		return -1;
	}
	public boolean emptyFields(){
		return list.size() == MAXInventorySize ? true : false;
	}
	public boolean haveManaPotion(){
		return isInList(MPotion);
	}
}
