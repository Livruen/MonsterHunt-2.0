
import gameIO.Reader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Characters.Character;
import Characters.Enemy;
import Characters.Magician;
import Characters.UsersInput;
import Characters.Warrior;
import Items.Item;
import Maze.Direction;
import Maze.GameField;
import Quest.QuestMaster;
import Shop.Shop;

/*
 * @author Livruen Nati
 * @version 2.0
 * 
 */

@SuppressWarnings("serial")
public class GameBoard implements Serializable, UsersInput {

	private Character player;
	private Character enemy;
	transient private Character[] PlayerList;
	private ArrayList<Character> enemyList;
	private int defetedEnemys;
	private QuestMaster questMaster;
	private GameField field;
	private static GameBoard singleton;

	/* GoF Singleton Pattern - there must be just one GameBoard intance */
	private GameBoard() {
	}

	public static GameBoard newInstance() {
		if (singleton == null) {
			synchronized (GameBoard.class) {
				if (singleton == null) {
					singleton = new GameBoard();
				}
			}

		}
		return singleton;
	}

	public void initializeGameParameters() {
		CreatePlayerList();
		CreateEnemyList();
		questMaster = new QuestMaster();
		field = new GameField();
		field = field.createGameField();
	}

	/* Creates the List of the enemys that are in the game */
	private void CreateEnemyList() {
		enemyList = new ArrayList<>();
		enemyList.add(new Enemy(160, 42, 0.4, "TheCreature", 400));
		enemyList.add(new Enemy(130, 30, 0.4, "Tessa", 300));
		enemyList.add(new Enemy(100, 21, 0.5, "Skeletor", 250));
		enemyList.add(new Enemy(80, 15, 0.6, "Cat", 100));
		enemyList.add(new Enemy(40, 7, 0.7, "Goblin", 50));

	}

	private void CreatePlayerList() {
		PlayerList = new Character[2];
		PlayerList[0] = new Warrior(60, 9, 0.5);
		PlayerList[1] = new Magician(40, 7, 0.5);
	}

	public Character getPlayer() {
		return player;
	}

	public void setPlayer(Character player) {
		this.player = player;
	}

	public Character getEnemy() {
		return enemy;
	}

	public void setEnemy(Character enemy) {
		this.enemy = enemy;
	}

	public ArrayList<Character> getEnemyList() {
		return enemyList;
	}

	public int getDefetedEnemys() {
		return defetedEnemys;
	}

	public void setDefetedEnemys(int defetedEnemys) {
		this.defetedEnemys = defetedEnemys;
	}

	public QuestMaster getQuestMaster() {
		return questMaster;
	}

	public void setQuestMaster(QuestMaster questMaster) {
		this.questMaster = questMaster;
	}

	public GameField getField() {
		return field;
	}

	public void setField(GameField field) {
		this.field = field;
	}

	protected void chooseCharacter() {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		if (PlayerList == null) {
			System.out.println("Empty Player List");
			System.exit(0);
		} else {
			boolean selected = false;
			while (!selected) {
				ImageOutput("chooseCharacter.txt");
				String input;
				try {
					input = bReader.readLine();
					int select = Integer.parseInt(input);
					if (select == WARRIOR) {
						setPlayer(PlayerList[WARRIOR]);
						player.showCharacter(BIG_WARRIOR_TXT);
						selected = true;
					} else if (select == MAGICIAN) {
						setPlayer((Magician) PlayerList[MAGICIAN]);
						player.showCharacter(BIG_MAGICIAN_TXT);
						selected = true;
					} else if (select == DISPLAY_CHARACTERS) {
						// Show all characters
						ImageOutput(BIG_WARRIOR_TXT);
						ImageOutput(BIG_MAGICIAN_TXT);
					} else {
						System.out.println("\nFalse input. Try again.");
					}
				} catch (NumberFormatException e) {

					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * *************************************************************************
	 * ***********************************
	 * 
	 * playOnField() switchToDirection()
	 * 
	 * *************************************************************************
	 * * **********************************
	 */
	public void playOnField() {
		boolean endedGame = false;
		while (!endedGame) {
			field.printMap();
			ImageOutput("Legend.txt");
			try {
				BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
				String input;
				input = bReader.readLine();
				String next = "";
				next = switchToDirection(field, input, next);

				switch (next) {
				case "Enemy":
					attackEngine();
					player.setNextLvl();
					break;
				case "Wall":
					playerdestroingWall();
					break;
				case "Heal":
					player.getHealable().restoreParams(player);
					break;
				case ".":
					continue;
				case "Boss":
					enemy = new Enemy(220, 40, 0.4, "Kraken", 0);
					ImageOutput(enemy.getName() + ".txt");
					attackEngine();
					System.out.println("End of Game");
					endedGame = true;
					break;
				case "Shop":
					Shop dealer = new Shop();
					dealer.startSelling(player);
					break;
				case "Quest":
					questMaster.showQuestMaster(player);
					break;
				case "Save":
					saveGame();
					System.out.println("Saved the Game");
					break;
				case "EXIT":
					endedGame = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void playerdestroingWall() {
		if (player.getInventory().isInList("Bomb")) {
			System.out.println("Do you want to use the Bomb? Press 1 for YES or 2 for NO");
			boolean use = player.getBombBehavior().decideToUseBomb();
			if (use) {
				player.getBombBehavior().useBomb(field);
				player.getInventory().delete("Bomb");
			}
		}
		System.out.println("Choose another direction");
	}

	private String switchToDirection(GameField field, String input, String next) {

		switch (input) {
		case INVENTORY:
			player.showParamAndInventory();
			break;
		case UP:
			next = field.goTo(Direction.NORTH);
			break;
		case LEFT:
			next = field.goTo(Direction.WEST);
			break;
		case DOWN:
			next = field.goTo(Direction.SOUTH);
			break;
		case RIGHT:
			next = field.goTo(Direction.EAST);
			break;
		case SAVE_GAME:
			next = "Save";
			break;
		case EXIT:
			next = "EXIT";
		default:
			System.out.println("try again");
		}
		return next;
	}

	protected void nextEnemy() {
		if (enemyList == null) {
			System.out.println("Empty enemy list.");
			System.exit(0);
		} else {
			System.out.println("\n This is your enemy.\n");
			setEnemy(enemyList.get(enemyList.size()-1));
			ImageOutput((enemy.getName() + ".txt"));
		}
	}

	/*
	 * *************************************************************************
	 * ***********************************
	 * 
	 * attackEngine() fight()
	 * 
	 * *************************************************************************
	 * * **********************************
	 */
	private void attackEngine() {
		if (getEnemy() == null || getEnemy().getName() != "Kraken") {
			nextEnemy();
		}
		int whoWin;
		whoWin = PLAYER;
		whoWin = fight();
		switch (whoWin) {
		case PLAYER:
			defetedEnemys++;
			break;
		case ENEMY:
			System.out.println("Enemy win.");
			System.out.println("Player killed " + defetedEnemys + " enemys.");
			System.out.println("The End.");
			System.exit(0);
			break;
		case FLEE:
			System.out.printf(" %s run away.", this.player.getName());
			break;
		}
	}

	private int fight() {

		ImageOutput("Fight.txt");
		boolean flee = false;
		boolean fighting = true;
		int winner = 0;
	do{
			System.out.println(player.toString() + "        " + enemy.toString());
			if(player.isAlive()){
				flee = player.attackUsePotionFlee(getEnemy());
				if (flee) {
					winner = FLEE;
					fighting = false;
				}
				if (enemy.isAlive()) {
					enemy.attack(player);
				} else {
					System.out.println("\n\t You win with " + enemy.getName());
					System.out.println("Enemy drop some Items look at your Inventory.");
					for (int i = 0; i < enemy.getInventory().length(); i++) {
						Item key = enemy.getInventory().getItem(i);
						player.putIntoInventory(key);
						enemy.getInventory().delete(key);
						player.setMoney(player.getMoney() + enemy.getMoney());
						enemyList.remove(enemyList.size()-1);
					}
					fighting = false;
					winner = PLAYER;
				}
			} else {
				winner = ENEMY;
				fighting = false;
			}
		}while(fighting);
		return winner;
	}

	private void ImageOutput(String string) {
		Reader reader = new Reader();
		try {
			reader.printPicture(string);
		} catch (IOException e) {
			System.out.println("Cannot read " + string);
			e.printStackTrace();
		}
	}

	public void saveGame() {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("gameSave1.ser", false));
			outputStream.writeObject(this);
			System.out.println("Saved Game");
			outputStream.close();
		} catch (IOException e) {
			System.err.print("Cannot serialize File");
			e.printStackTrace();
		}
	}

	public static GameBoard loadGame() {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("gameSave1.ser"));
			GameBoard l = (GameBoard) inputStream.readObject();
			inputStream.close();
			return l;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.err.print("Cannot load game");
		}
		return null;
	}

}