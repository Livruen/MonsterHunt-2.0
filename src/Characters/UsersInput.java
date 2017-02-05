package Characters;

public interface UsersInput {
	final int ATTACK = 1;
	final int FLEE = 3;
	final int POTION = 2;
	
	final int USE_HEALING_POTION = 1;
	final int USE_MANA_POTION = 0;
	final int SHOW_INVENTORY = 2;
	final int BACK_TO_FIGHT = 3;

	final String EXIT = "e";
	final String SAVE_GAME = "o";
	final String RIGHT = "d";
	final String DOWN = "s";
	final String LEFT = "a";
	final String UP = "w";
	final String INVENTORY = "i";
	final String YES = "y";

	final int ENEMY = 666;
	final int PLAYER = 1;

	final String BIG_MAGICIAN_TXT = "BigMagician.txt";
	final String BIG_WARRIOR_TXT = "BigWarrior.txt";

	final int MAGICIAN = 1;
	final int WARRIOR = 0;
	final int DISPLAY_CHARACTERS = 2;

	// private static final String EXIT_GAME = "e";
	final String LOAD_GAME = "l";
	final String NEW_GAME = "n";
	
	final int RUBIN = 5;
	final int FLOWER = 4;
	final int MANA_POTION_SHOP = 3;
	final int HEALING_POTION_SHOP = 2;
	final int FAIRY = 1;
	final int BOMB = 0;
	int HEAL = 6;
}
