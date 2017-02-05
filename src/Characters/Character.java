package Characters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import Characters.behaviors.heal.Healable;
import Characters.behaviors.magic.*;
import Characters.behaviors.attack.*;
import Characters.behaviors.bomb.BombBehavior;
import Inventory.Inventory;
import Items.Armor;
import Items.Bomb;
import Items.Fairy;
import Items.HealingPotion;
import Items.Item;
import Items.ManaPotion;
import Items.Weapon;
import gameIO.Reader;

/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */

public abstract class Character implements Serializable , UsersInput{
	
	protected final int COINSIDENCE = 5;
	protected CombatBehavior combatBehavior;
	protected Healable heal;
	protected MagicBehavior magicBehavior;
	protected BombBehavior bombBehavior;
	protected int maxHP;
	protected int hp;
	protected int attackPower;
	protected final String name;
	protected int lvl = 0;
	protected Inventory inventory;
	protected int money;	
	protected double hitChance;
	protected Item fightAttribute; // Anything to fight with
	protected Item armorAttribute; // Armor for Character
	

	public abstract boolean attackUsePotionFlee(Character other);


	public Character( int hp, int attackPower, double hitChance,
			String name) {
		setMAXHP(hp);
		setHp(hp);
		setAttackPower(attackPower);
		this.hitChance = hitChance;
		this.name = name;
		inventory = new Inventory();
	}	

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public String getName() {
		return this.name;
	}

	public int getLvl() {
		return lvl;
	}

	public void setNextLvl() {
		this.lvl++;
	}

	public int getHitChance() {
		return (int) Math.round(this.hitChance);
	}

	public void setHit(double hit) {
		this.hitChance = hit;
	}

	public int getMAXHP() {
		return this.maxHP;
	}
	public void setMAXHP(int hp){
		this.maxHP = hp;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public BombBehavior getBombBehavior() {
		return bombBehavior;
	}

	public void setBombBehavior(BombBehavior bombBehavior) {
		this.bombBehavior = bombBehavior;
	}

	public Weapon getFightAttribute() {
		return ((Weapon)fightAttribute);
	}
	public void setFightAttribute(Item fightAttribute) {
		this.fightAttribute = fightAttribute;
	}
	
	public Armor getArmorAttribute() {
		return ((Armor) armorAttribute);
	}
	
	public void changeArmor(Item newArmor) {
		
		setMAXHP(getMAXHP() - getArmorAttribute().getResistence());
		putIntoInventory(getArmorAttribute());
		
		Armor armor = (Armor) newArmor;
		setArmorAttribute(armor);
		inventory.delete(armor);
	}

	public void changeWeapon(Item newWeapon) {
		
		setAttackPower(getAttackPower() - getFightAttribute().getPower());
		putIntoInventory(getFightAttribute());
		
		Weapon weaponT = (Weapon) newWeapon;
		setFightAttribute(weaponT );
		inventory.delete(weaponT );
	}
	protected void setArmorAttribute(Armor armorAttribute) {
		this.armorAttribute = armorAttribute;
		setMAXHP(getMAXHP() + getArmorAttribute().getResistence());
	}

	public Item getFromInventory(int key) {
		return inventory.getItem(key);
	}

	public void putIntoInventory(Item item) {
		inventory.insert(item);
	}
	public CombatBehavior getCombatBehavior() {
		return combatBehavior;
	}
	
	public Healable getHealable() {
		return heal;
	}
	
	public MagicBehavior getMagicBehavior() {
		return magicBehavior;
	}
	public boolean isMaxHP(){
		return hp == maxHP;
	}
	
	/*
	 * ************************************************************************************************************
	 * 
	 *                   random()     heal()    
	 * 
	 * ************************************************************************************************************
	 */
	
	/* curennt character attacks his enemy */
	public void attack(Character enemy) {
		if (exist(enemy)) {
			int hitProbability = (int) (Math.random() * COINSIDENCE); // Calculates a random probability chance
			if ( hitProbability < enemy.getHitChance()) { // wenn die wahrscheinlichgkeit das ich den character treffe bei 0 liegt
				System.out.printf("%s missed ... \n", this.getName());
			} else { //sonst greife ich an 	
					enemy.takeDamage(this.attackPower);
					printAttackStats(enemy,attackPower);
			}
			
		} else {		
			System.out.println("Noone to fight with.");
		}
	}

	private boolean exist(Character character) {
		return character != null;
	}

	public void takeDamage(int damage) {
		if (this.hp <= 0) {
			System.out.printf("%s is dead \n", this.name);			
		} else {
			this.hp -= damage;	
		}
	}
	/*
	 * ************************************************************************************************************
	 * 
	 *                   isDefeated()      toString()   printAttackStats() 
	 * 
	 * ************************************************************************************************************
	 */
	public boolean isAlive() {	
		return this.hp <= 0 ? false: true;
	}
	
	protected void printAttackStats(Character character, int damage) {
		System.out.println( this.name + " hits " + character.name + " with " + damage + " damage. " );
		
	}
	
	public String toString(){
		 if(this.hp <= 0 ){
			return  this.name + " is dead." ;
			
		}else {
			return " " + this.name + " --> HP: " + this.hp + " ATK : " + this.attackPower + "\t";
		}
	}

	public void showCharacter(String characterPic) {
		Reader reader = new Reader();
		try {
			reader.printPicture(characterPic);
		} catch (IOException e) {
			System.out.println("Cannot read " + characterPic);
			System.exit(0);
		}
		System.out.println(this.toString());
	}
	public void showParamAndInventory() {
		Reader reader = new Reader();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			boolean decided = false;
			while (!decided) {
				printCharacterParameters();
				inventory.showInventory();
				reader.printPicture("Inventory.txt");
				
				String decision = bReader.readLine();
				switch (decision) {
				case "u":
					System.out.println("Which item do you want to use? Insert the position number.");
					int position = Integer.parseInt(bReader.readLine());
					Item choosenItem = inventory.getItem(position);
					if (choosenItem instanceof HealingPotion) {
						if(isMaxHP()){
							System.out.println("HP is maximum.");
						} else {
							heal.drinkPotion(this);
						}
					} else if (choosenItem instanceof ManaPotion) {
						magicBehavior.restoreWithPotion(getInventory());
					} else if (choosenItem instanceof Bomb) {
						System.out.println("Cannot use Bomb here.");
					} else if (choosenItem instanceof Fairy) {
						Fairy f = (Fairy) choosenItem;
						Item i = f.sparkle();
						if (i == null) {
							setMoney(-111);
						} else {
							putIntoInventory(i);
						}
						inventory.delete("Fairy");
					} else if (choosenItem instanceof Weapon) {
						changeWeapon(choosenItem);
					} else if (choosenItem instanceof Armor) {
						changeArmor(choosenItem);
					}
					break;
				case "e":
					decided = true;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printCharacterParameters() {
		System.out.printf("\n~~~~~~~~~~~~~~~~PARAMETERS~~~~~~~~~~~~~\n");
		System.out.printf("# %s\n", this.name);
		System.out.printf("# MAXHP:[%d]\n", this.getMAXHP());
		System.out.printf("# HP:[%d] ATTACK:[%d] MANA:[%d]\n", this.getHp(), getAttackPower(), magicBehavior.getMana());
		System.out.printf("# ARMOR:[%s] WEAPON:[%s]\n", armorAttribute.getName(), fightAttribute.getName());
		System.out.printf("# MONEY:[%dG]\n", this.money);
	}
	
}