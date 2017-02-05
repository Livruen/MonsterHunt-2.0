import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Inventory.Armor;
import Inventory.Inventory;
import Inventory.Weapon;

/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */

public class Player extends Character {

	private int healingPotion;
	private int MAXhealingPotion;
	private int mana;
	private int magicAtk;
	private int manaPotion;
	private int MAXmanaPotion;
	private boolean magicWeapon;
	private boolean magicArmor;
	private int MAXMP;
	private static int lvl;
	private boolean bomb;
	Reader reader = new Reader();
	Scanner sc = new Scanner(System.in);
	Inventory inventory;

	public Player( int hp,
				   int attack, 
				   double hitChance, 
				   String name,
				   int healing_potion,
				   Armor armor,
				   Weapon weapon,
				   int defence,
				   int mana,
				   int magicAtk,
				   int manaPotion) {
		
		super(hp, attack, hitChance, name);
		this.healingPotion = healing_potion;
		this.MAXhealingPotion = healing_potion;
		this.MAXMP = mana;
		this.mana = mana;
		this.magicAtk = magicAtk;
		this.manaPotion = manaPotion;
		this.MAXmanaPotion = manaPotion;
		this.lvl = 1;
		setBomb(true);
		inventory = new Inventory<>();	
	}
	




	/*
	 * ************************************************************************************************************
	 * 
	 *                                        get()        set()
	 * 
	 * ************************************************************************************************************
	 */

	public int getHealingPotion() {
		return healingPotion;
	}

	public int getManaPotion() {
		return manaPotion;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void setHealingPotion(int quantity) {
		this.healingPotion = quantity;
	}
	
	
	public int getMAXhealingPotion() {
		return MAXhealingPotion;
	}

	public void setMAXhealingPotion(int mAXhealingPotion) {
		MAXhealingPotion = mAXhealingPotion;
	}

	public int getMAXmanaPotion() {
		return MAXmanaPotion;
	}

	public void setMAXmanaPotion(int mAXmanaPotion) {
		MAXmanaPotion = mAXmanaPotion;
	}

	public void setManaPotion(int quantity) {
		this.manaPotion = quantity;
	}
	public int getLvl() {
		return this.lvl;
	}
	public void setLvl() {
		this.magicAtk += 6;
		this.attack += 4;
		this.MAXhealingPotion++;
		this.MAXmanaPotion++;
		resetParameters();
		this.lvl++;
	}

	public void setMagicWeapon() {
		this.magicWeapon = true;
		this.magicAtk += (magicAtk/30);
		this.attack += (attack/30);	
	}

	public void setMagicArmor() {
		this.magicArmor = true;
		this.MAXHP += (MAXHP/40);
		this.hp = MAXHP;
	}

	public boolean haveBomb() {
		return bomb;
	}

	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	/*
	 * ************************************************************************************************************
	 * 
	 *                      resetParameters()   showPotionNumber()  useManaPotion()  heal()
	 * 
	 * ************************************************************************************************************
	 */
	public void resetParameters() {

		setHp(MAXHP);
		setHealingPotion(MAXhealingPotion);
		if (this.name == "Magician") {
			setMana(MAXMP);
			setManaPotion(MAXmanaPotion);
		}
	}

	public void showPotionNumber() {
		System.out.printf("You have %d healing potions and %d mana potions",
				this.getHealingPotion(), this.getManaPotion());

	}

	public boolean heal() {
		if (healingPotion == 0) {
			System.out.println("No healing potion.");
			return false;
		} else {
			this.healingPotion -= 1;
			super.heal();
			return true;
		}
	}

	public void useManaPotion() {
		if (manaPotion == 0) {
			System.out.println("No mana Potion.");
			if (this.mana <= 0) {
				System.out
						.println("The magician has no mana and no mana potion.");
				System.out.println("He run away.");
				System.exit(0);
			}

		} else if (this.mana == this.MAXMP) {
			System.out.println("You don't need to use Mana Potion.");
		} else {
			this.manaPotion -= 1;
			this.mana = this.mana + 30;
			System.out.println(this.name + " used Mana Potion: + 30 MANA.");
		}
	}
	
	/*
	 * ************************************************************************************************************
	 * 
	 *                                           magicAttack() 
	 * 
	 * ************************************************************************************************************
	 */

	public int magicAttack(Character character) {

		if (character == null) {
			System.out.println("Noone to fight with.");
		} else if (this.mana <= 0) {
			System.out.println("No Mana");

		} else {

			int random_number = random();
			// die wahrscheinlichgkeit das ich den character treffe liegt bei 0
			if (random_number < character.getHit()) {
				System.out.printf("%s missed ... \n", this.getName());
				// sonst greife ich an
			} else {

				character.takeDamage(this.magicAtk);
				this.mana -= 15;
				printAttackStats(character, this.magicAtk);
			}
		}
		return this.magicAtk;

	}
	/*
	 * ************************************************************************************************************
	 * 
	 *                     decideToAttackOrUsePotion()    decideWhichPotion()  decideToUseBomb()
	 * 
	 * ************************************************************************************************************
	 */
	public void decideToAttackOrUsePotion(Character enemy)
			throws IOException, InterruptedException {

		// unaffected = nicht angegriffen
		boolean affected = false;
		while (!affected) {
			try {
				reader.printPicture("AttackOrPotion.txt");
				int decision = sc.nextInt();
				if (decision == 1) {

					if (this.getName() == "Magician") {
						if (this.getMana() <= 0) {
							System.out.println("No Mana. Use your Potion.\n");

						} else {

							this.magicAttack(enemy);
							reader.printPicture("magicianAttack.txt");
							affected = true;
						}

					} else {
						this.attack(enemy);
						affected = true;
					}

				} else if (decision == 2) {
					decideWhichPotion();
				}
			} catch (InputMismatchException e) {
				System.out.println("False input.");
				System.exit(0);
			}
		}
	}
	public boolean decideToUseBomb() {
		boolean decided = false;
		while (!decided) {
			try {
				int PlayersDecision = sc.nextInt();
				if (PlayersDecision == 1) {
					return true;
				} else if (PlayersDecision == 2) {
					return false;

				} else {
					System.out.println("Wrong input.");
				}
			} catch (InputMismatchException e) {
				System.out.println("False input.");		
			}
		}
		return false;
	}

	void decideWhichPotion() throws IOException, InterruptedException {

		showPotionNumber();
		reader.printPicture("potion_decision.txt");
		boolean decided = false;
		while (!decided) {
			try {
				int PlayersDecision = sc.nextInt();
				if (PlayersDecision == 1) {
					heal();
					System.out.println(this.toString());
					decided = true;

				} else if (PlayersDecision == 2) {
					useManaPotion();
					System.out.println(this.toString());
					decided = true;

				} else if (PlayersDecision == 3) {
					decided = true;
				} else {
					System.out.println("Wrong input.");
				}
			} catch (InputMismatchException e) {
				System.out.println("False input.");
				
			}
		}
	}
	/*
	 * ************************************************************************************************************
	 * 
	 *                                showPlayer()   toString()
	 * 
	 * ************************************************************************************************************
	 */
	public void show_player() throws IOException {

		Reader reader = new Reader();
		if (this.getName() == "Magician") {
			reader.printPicture("BigMagician.txt");

		} else {

			reader.printPicture("BigWarrior.txt");
		}
		System.out.println(this.toString());
	}

	public String toString() {
		if (this == null) {
			return "null";
		} else if (this.hp <= 0) {
			return this.name + " is dead.";

		} else {
			if (this.name == "Magician") {
				return "\n \t" + this.name + " --> HP: " + this.hp
						+ " M_ATK : " + this.magicAtk + " MANA " + this.mana + " lvl " + this.lvl;
			} else {
				return "\n \t" + this.name + " --> HP: " + this.hp + " ATK : "
						+ this.attack + " lvl " + this.lvl;
			}

		}

	}
	/*
	 * ************************************************************************************************************
	 * 
	 *                                         INVENTAR
	 * 
	 * ************************************************************************************************************
	 */



}
