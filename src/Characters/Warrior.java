package Characters;

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

import Characters.behaviors.attack.AttackWithItem;
import Characters.behaviors.bomb.UseBombs;
import Characters.behaviors.heal.WithPotion;
import Characters.behaviors.magic.NotPerformingMagic;
import Items.Armor;
import Items.Bomb;
import Items.HealingPotion;
import Items.Weapon;

public class Warrior extends Character {

	private static final long serialVersionUID = 1L;


	/**************************************************************************************************************
	 * 
	 * Constructor get set
	 * 
	 * 
	 *************************************************************************************************************/

	public Warrior(int hp, int attack, double hitChance) {
		super(hp, attack, hitChance, "Warrior");
		combatBehavior = new AttackWithItem();
		heal = new WithPotion();
		magicBehavior = new NotPerformingMagic();
		bombBehavior = new UseBombs();
		setArmorAttribute(new Armor("Chain shirt", 100, 3));
		setFightAttribute(new Weapon("Knife", 50, 2));
		setInitialParameters();

	}

	private void setInitialParameters() {
		inventory.insert(new HealingPotion());
		inventory.insert(new HealingPotion());
		inventory.insert(new Bomb());
		this.money = 300;
		lvl = 1;
		setHp(getMAXHP());
	}

	public boolean attackUsePotionFlee(Character enemy) {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));
		Reader reader = new Reader();
		// affected = angegriffen
		boolean affected = false;
		while (!affected) {
			try {
				try {
					reader.printPicture("AttackOrPotion.txt");
				} catch (IOException e) {
					System.out.println("Cannot read AttackOrPotion.txt");
					System.exit(0);
				}
				String input = bReader.readLine();
				int decision = Integer.parseInt(input);
				// Attack
				switch (decision) {
				case ATTACK:
					super.attack(enemy);
					affected = true;
					break;
				case POTION:
					decideWhichPotion();
					break;
				case FLEE:
					affected = true;
					return true;

				default:
					System.out.println("Try again.");
				}
			} catch (NumberFormatException | IOException e) {
				System.out.println("Cannot read AttackOrPotion.txt");
				System.exit(0);
			}
		}
		return false;
	}

	/**************************************************************************************************************
	 * 
	 * decideWhichPotion
	 * 
	 * 
	 *************************************************************************************************************/

	public void decideWhichPotion() {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));
		Reader reader = new Reader();
		boolean decided = false;
		while (!decided) {
			try {
				reader.printPicture("potionDecisionWarrior.txt");
			} catch (IOException e1) {
				System.out.println("Cannot read potionDecisionWarrior.txt");
				System.exit(0);
			}
			try {
				int PlayersDecision = Integer.parseInt(bReader.readLine());
				switch (PlayersDecision) {
				case USE_HEALING_POTION:
					getHealable().drinkPotion(this);;
				case SHOW_INVENTORY:
					inventory.showInventory();
					break;
				case BACK_TO_FIGHT:
					decided = true;
					break;
				default:
					System.out.println("Wrong input.");
					break;
				}
			} catch (InputMismatchException | NumberFormatException
					| IOException e) {
				System.out.println("False input.");
			}
		}
	}

	/**************************************************************************************************************
	 * 
	 * show Character toString
	 * 
	 * 
	 *************************************************************************************************************/

	public void showCharacter() {
		Reader reader = new Reader();
		try {
			reader.printPicture("BigWarrior.txt");
		} catch (IOException e) {
			System.out.println("Cannot read BigWarrior.txt");
			System.exit(0);
		}

		System.out.println(this.toString());
	}

	public String toString() {
		if (this.hp <= 0) {
			return this.name + " is dead.";
		} else {
			return "\n \t" + this.name + " --> HP: " + this.hp + " ATK : "
					+ this.attackPower;
		}
	}
	
}
