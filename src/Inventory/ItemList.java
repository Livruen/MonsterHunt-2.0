package Inventory;
/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
import java.io.Serializable;
import java.util.ArrayList;

import Items.Armor;
import Items.Fairy;
import Items.Flower;
import Items.HealingPotion;
import Items.Item;
import Items.ManaPotion;
import Items.Weapon;

public class ItemList implements Serializable{
	 ArrayList<Item> list;
	
	public ItemList() {
		list = new ArrayList<Item>();
		list.add(new HealingPotion());
		list.add(new ManaPotion());
		list.add(new Fairy());
		list.add(new Armor("Rabit Shirt",10,1));
		list.add(new Flower());
		list.add(new Armor("Ivory Tunic",200,5));
		list.add(new Armor("Draconic Robe",300,7));
		list.add(new Armor("Goblin Tunic",150,5));
		list.add(new Armor("Barbaric Vest",250,6));
		list.add(new Armor("Destroyer Vest",500,10));
		list.add(new Weapon("Spoon",10,2));
		list.add(new Fairy());
		list.add(new Weapon("Swort of Eternity",300,12));
		list.add(new Weapon("Enchanted Spear",400,13));
		list.add(new Weapon("Fist of Blod",350,8));
	}
	
	public  Item randomItem() {
		int i = (int) (Math.random() * list.size());
		Item r = list.get(i);
		return r;
	}
	
}
