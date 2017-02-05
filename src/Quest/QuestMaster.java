package Quest;

import gameIO.Reader;
import gameIO.readQuestFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.LinkedList;
import java.io.BufferedReader;

import Inventory.Inventory;
import Items.Armor;
import Items.HealingPotion;
import Items.Item;
import Items.Weapon;
import Characters.Character;

public class QuestMaster implements Serializable {
	
	private LinkedList<Quest> questList;
	private Quest commonQuest;
	private String commonQuestItem;

	public QuestMaster() {
		readQuestFile file = new readQuestFile();
		questList = file.createQuestList();
		setCommonQuest();
		setPrices();		
	}

	public String getCommonQuestItem() {
		return this.commonQuestItem;
	}

	public void setCommonQuestItem() {
		this.commonQuestItem = getCommonQuest().getNeededQuestItems();
	}

	private void setCommonQuest() {
		commonQuest = questList.getFirst();
		setCommonQuestItem();
	}

	public Quest getCommonQuest() {
		return commonQuest;
	}

	public void setNextQuest() {
		questList.removeFirst();
		setCommonQuest();
	}

	public String showQuest() {
		return getCommonQuest().toString();
	}

	private boolean emptyQuestList() {
		return questList.isEmpty();
	}
	private void setPrices(){
		questList.get(0).setPrice(new HealingPotion());
		questList.get(1).setPrice(new Armor("Shiny Magic Armor",3000,50));
		questList.get(2).setPrice(new Weapon("Shiny Magic Weapon",3000,50));
	}
	private Item getPrice(){
		return commonQuest.getPrice();
	}

	public void showQuestMaster(Character player) {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		Reader reader = new Reader();
		try {
			boolean decided = false;
			while (!decided) {
				System.out
						.println("\n\nGreetings ! I am the Quest Master and i have something interessting for you.\nIf you fullfill all my quests then i will help you win with the Kraken.\n ");
				reader.printPicture("questMaster.txt");
				String input = bReader.readLine();
				input.toLowerCase();
				switch (input) {
				case "g":
					takeQuestItem(player.getInventory());
					break;
				case "s":
					if (emptyQuestList()) {
						System.out
								.println("You have everything you need. KILL THE KRAKEN!");
					} else {
						showQuest();
					}
					break;
				case "e":
					decided = true;
				}
			}
		} catch (IOException e) {
			System.err.println("Cannot read questMaster.txt");
		}
	}

	public void takeQuestItem(Inventory inventory) {
		// Wenn das Quest Item im Inventory liegt
		if (inventory.isInList(getCommonQuestItem())) {
			System.out.printf("Superb! You found %s ! I give you for that %s", getCommonQuestItem(), getPrice().getName());
			inventory.delete(getCommonQuestItem());
			inventory.append(getPrice());
			setNextQuest();
		} else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("\nI'm sorry this is not the quest item.\n");
		}
	}
}
