package Characters;

import gameIO.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import Characters.behaviors.attack.*;
import Characters.behaviors.bomb.UseBombs;
import Characters.behaviors.heal.*;
import Characters.behaviors.magic.*;
import Items.Armor;
import Items.Bomb;
import Items.Fairy;
import Items.HealingPotion;
import Items.Item;
import Items.ManaPotion;
import Items.Weapon;

public class Magician extends Character {

	private final String MPotion = "Mana potion";
	private final String HPotion = "Healing potion";
	private final String ATTACK_OR_POTION_ASCII = "AttackOrPotion.txt";
	private final String POTION_DECISION_MAG = "potionDecisionMagician.txt";


	public Magician(int hp, int attackPower, double hitChance) {
		super(hp, attackPower, hitChance, "Magician");
		combatBehavior = new AttackWithMagic();
		magicBehavior = new PerformsMagic();
		bombBehavior = new UseBombs();
		heal = new WithPotion();
		setInitialParameters();
		setHp(getMAXHP());
	}

	private void setInitialParameters() {

		// Magical parameters
		int startManaParams = 60;
		magicBehavior.setMana(startManaParams);
		magicBehavior.setMAXMANA(startManaParams);

		// Attributes
		setArmorAttribute(new Armor("Lether Tunika", 100, 3));
		setFightAttribute(new Weapon("Simple Stick", 50, 2));

		// Inventory Parameters
		inventory.insert(new HealingPotion());
		inventory.insert(new HealingPotion());
		inventory.insert(new ManaPotion());
		inventory.insert(new ManaPotion());
		inventory.insert(new Bomb());

		setMoney(100);
		lvl = 1;
	}

	public boolean attackUsePotionFlee(Character enemy) {
		Reader reader = new Reader();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		boolean affected = false;
		boolean flee = false;
		while (!affected) { // affected = angegriffen
			try {
				reader.printPicture(ATTACK_OR_POTION_ASCII);
				String input = bReader.readLine();
				int decision = Integer.parseInt(input);
				switch (decision) {
				case ATTACK: 
					this.attack(enemy);
					affected = true;
					flee=false;
					break;
				case POTION: 
					decideWhichPotion();
					break;
				case FLEE:
					affected = true;
					flee = true;
					break;
				default:
					System.out.println("Try again.");

				}
			} catch (NumberFormatException | IOException e) {
				System.out.println("Cannot read AttackOrPotion.txt");
				System.exit(0);
			}
		}
		return flee;
	}


	public void decideWhichPotion() {
		boolean decided = false;
		Reader reader = new Reader();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (!decided) {
				reader.printPicture(POTION_DECISION_MAG);
				int PlayersDecision = Integer.parseInt(bReader.readLine());
				switch (PlayersDecision) {
				case HEALING_POTION:
						getHealable().drinkPotion(this);
				case MANA_POTION:
					// Restore with Mana potion
					magicBehavior.restoreWithPotion(inventory);
					break;
				case SHOW_INVENTORY:
					inventory.showInventory();
					break;
				case BACK:
					decided = true;
					break;
				default:
					System.out.println("Wrong input.");
					break;
				}
			}
		} catch (InputMismatchException | NumberFormatException e) {
			System.out.println("False input.");
		} catch (IOException e1) {
			System.out.println("potionDecisionMagician.txt");
			System.exit(0);
		}
	}

	public String toString() {
		if (this.hp <= 0) {
			return this.name + " is dead.";
		} else {
			return "\n \t" + this.name + "HP: " + this.hp + " ATK : " + this.attackPower + " MANA:"
					+ magicBehavior.getMana();
		}
	}

}
