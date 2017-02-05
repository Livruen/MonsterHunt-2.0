package Shop;

/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
import gameIO.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.LinkedList;

import Items.Bomb;
import Items.Fairy;
import Items.Flower;
import Items.HealingPotion;
import Items.Item;
import Items.ManaPotion;
import Items.Rubin;
import Characters.Character;
import Characters.UsersInput;


public class Shop implements UsersInput{

	private static final String PRODUKT_LIST_FILE = "produktList.txt";
	private LinkedList<Item> produktList;

	public Shop() {
		produktList = new LinkedList<Item>();
		produktList.add(new Bomb());
		produktList.add(new Fairy());
		produktList.add(new HealingPotion());
		produktList.add(new ManaPotion());
		produktList.add(new Flower());
		produktList.add(new Rubin());
	}

	public void startSelling(Character c) {
		BufferedReader bReader= new BufferedReader(new InputStreamReader(System.in));
		Reader reader = new Reader();
		boolean affected = false;
		String input;
		while (!affected) {
			try {
				reader.printPicture("Shop.txt");
				System.out.printf("-> MONEY:[%d]\n", c.getMoney());
				input = bReader.readLine();
				if (input.equals("i")) {
					c.getInventory().showInventory();
				} else if (input.equals("e")) {
					affected = true;
				} else if (input.equals("p")) {
					showProduktExplanation();
				} else if (input.equals("s")) {
					sell(c);
				} else if (input.equals("b")) {
					buy(c);
				} else {
					System.out.println("Wrong input.");
				}
			} catch (NumberFormatException e) {

			} catch (IOException e) {
				System.out.println("Cannot read Shop.txt");
				System.exit(0);
			}
		}
	}

	private void buy(Character player)
			throws IOException {
		Reader reader = new Reader();
		BufferedReader bReader= new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Insert the number of the Produkt you want to buy.");
		reader.printPicture(PRODUKT_LIST_FILE);
		int decision;
		decision = Integer.parseInt(bReader.readLine());
		switch (decision) {
		case BOMB:
			buy(player, produktList.get(BOMB));
			break;
		case FAIRY:
			buy(player, produktList.get(FAIRY));
			break;
		case HEALING_POTION_SHOP:
			buy(player, produktList.get(HEALING_POTION));
			break;
		case MANA_POTION_SHOP:
			buy(player, produktList.get(MANA_POTION));
			break;
		case FLOWER:
			buy(player,produktList.get(FLOWER));
			break;
		case RUBIN:
			buy(player, produktList.get(RUBIN));
			break;
		case HEAL:
			player.getHealable().healUp(player);
			pay(player, 100);
			break;
		default:
			System.out.println("Wrong input.");
			break;
		}
	}

	private void sell(Character player) throws NumberFormatException, IOException {
		BufferedReader bReader= new BufferedReader(new InputStreamReader(System.in));
		System.out
				.println("Insert the number of the product you want to sell.");
		player.getInventory().showInventory();
		int produktNr = Integer.parseInt(bReader.readLine());
		Item item = player.getInventory().getItem(produktNr);
		int offer = makeOffer(item);
		
		int buying = Integer.parseInt(bReader.readLine());
		if (buying == 1) {
			player.getInventory().delete(item);
			player.setMoney(player.getMoney()+ offer);
		}
	}

	private void showProduktExplanation() throws IOException {
		BufferedReader bReader= new BufferedReader(new InputStreamReader(System.in));
		boolean affected = false;
		while (!affected) {
			printExplanation("Produkt");
			String input = bReader.readLine();
			if (input.equals(EXIT)) {
				affected = true;
			} else {
				int temp = Integer.parseInt(input);
				switch (temp) {
				case 0:
					printExplanation("Bomb");
					break;
				case 1:
					printExplanation("Fairy3");

					break;
				case 2:
					printExplanation("HPotion");
					break;
				case 3:
					printExplanation("MPotion");
					break;
				}
			}
		}
	}

	private void buy(Character c, Item item) {
		BufferedReader bReader= new BufferedReader(new InputStreamReader(System.in));
		System.out.println("How many " + item.getName()
				+ "'s do you want to buy ? ");
		try {
			int amound = Integer.parseInt(bReader.readLine());
			int preis = sumProduktPreis(amound, item);
			if (c.getInventory().emptyFields()) {
				System.out.println("You don't have enough Inventory space.");
			} else if (c.getMoney() < preis) {
				System.out.println("You don't have enough money.");
			} else {
				System.out.println("This will cost " + preis
						+ " Do you want to buy it? 1 for YES, 2 for NO");
				int decision = Integer.parseInt(bReader.readLine());
				if (decision == 1) {
					putIntoPlayersInventory(c, amound, item);
					pay(c, preis);
				}
			}
		} catch (InputMismatchException | NumberFormatException | IOException e) {
			System.out.println("False input");
		}
	}

	private void pay(Character player, int preis) {
		player.setMoney(player.getMoney()-preis);
	}

	private void putIntoPlayersInventory(Character c, int decision, Item item) {
		sumProduktPreis(decision, item);
		for (int i = 0; i < decision; i++) {
			c.getInventory().append(item);
		}
	}

	private void printExplanation(String o) {
		Reader reader = new Reader();
		try {
			reader.printPicture(o + "Explanation.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int sumProduktPreis(int decision, Item item) {
		return item.getValue() * decision;
	}

	public int makeOffer(Item item) {
		int offer = (int) (item.getValue() * 0.2);
		System.out
		.printf("I can buy it for %dG . Do you agree with that?\n Insert 1 - YES    2 - NO \n",
				offer);
		return offer;
	}
}
