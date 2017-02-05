package Characters;

import Characters.behaviors.attack.AttackWithHands;
import Characters.behaviors.bomb.CannotUseBombs;
import Characters.behaviors.bomb.UseBombs;
import Characters.behaviors.heal.WithoudPotion;
import Characters.behaviors.magic.NotPerformingMagic;
import Inventory.*;


/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
public class Enemy extends Character {
	
	private final int COINSIDENCE = 5;
	private final int HEAL_COINCIDENCE = 4;
	private final int MAX_INVENTORY_SIZE = 3;
	private final double COMBO_POWER = 2.5;
	private boolean healedInCombat;
	
	/* Creates an Enemy */
	public Enemy(int hp, int attackPower, double hitChance, String name,int money) {
		super(hp, attackPower, hitChance, name);
		combatBehavior = new AttackWithHands(); 
		heal = new WithoudPotion();
		magicBehavior = new NotPerformingMagic();
		bombBehavior = new CannotUseBombs();
		fillInventory();	
		setMoney(money);
		healedInCombat = false;
	}
	
	/* Fills the enemy inventory with random Items*/
	private void fillInventory() {
		ItemList itemList = new ItemList();
		for (int i = 0; i < MAX_INVENTORY_SIZE; i++) {
			this.inventory.insert(itemList.randomItem());
		}
	}
	/* Enemy can heal himself in a combat */
	@Override
	public void attack(Character player) {

		if (healedInCombat) { // After he healed himself the enemy attacks with a combo hit
			player.takeDamage(comboHit());
			healedInCombat = false;
			System.out.println("Combo hit !");
			printAttackStats(player, comboHit());
		} else {
			int coincidence = this.random();
			if (coincidence == HEAL_COINCIDENCE) {
				healedInCombat = true;
				selfHealing();
			} else {
				super.attack(player);
			}
		}
	}

	private void selfHealing() {
		heal.healUp(this);
	}

	private int comboHit() {
		return (int) Math.round(this.attackPower * COMBO_POWER);
	}
	
	public int random() {
		return (int) (Math.random() * COINSIDENCE);	
	}

	@Override
	public boolean attackUsePotionFlee(Character other) {
		// TODO Auto-generated method stub
		return false;
	}
	
}