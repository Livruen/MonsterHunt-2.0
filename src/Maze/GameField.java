package Maze;

import java.io.Serializable;
import java.util.ArrayList;
/*
 * @author Livruen Nati
 * @version 1.0
 * 
 * Diese Klasse simuliert das Spielfeld.
 */

public class GameField implements FieldIkon , Serializable{

	private int[][] mapData;
	private int columnLength;
	private int lineLength;
	private int pInColumn;
	private int pInLine;
	private static int countHeal;
	private static int countEnemys;
	private Direction choosenDirection;
	private final ArrayList<Integer> ikonList = new ArrayList<Integer>();
	
	/*
	 * ************************************************************************************************************
	 * 
	 *                                Constructor   get() set()
	 * 
	 * ************************************************************************************************************
	 */
	public GameField createGameField(){
		ikonList.add(wall);
		ikonList.add(way);
		ikonList.add(fountain);
		ikonList.add(enemy);
		ikonList.add(boss);
		randomField(11, 11);
		setPlayersPosition(1, 1);
		setShopkeeper(3,0);
		setQuestMaster(10,7);
		setBoss(6,6);
		return this;
	}


	public int[][] getMapData() {
		return mapData;
	}

	public void setMapData(int[][] mapData) {
		this.mapData = mapData;
	}

	public int getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(int columnLength) {
		this.columnLength = columnLength;
	}

	public int getLineLength() {
		return lineLength;
	}

	public void setLineLength(int lineLength) {
		this.lineLength = lineLength;
	}

	public int getpInColumn() {
		return pInColumn;
	}

	public void setpInColumn(int pInColumn) {
		this.pInColumn = pInColumn;
	}

	public int getpInLine() {
		return pInLine;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayersPosition(int line, int column) {
		this.pInLine = line;
		this.pInColumn = column;
		mapData[pInLine][pInColumn] = 8;
	}
	private void setShopkeeper(int i, int j) {
		mapData[i][j] = shopKeeper;
		
	}

	private void setQuestMaster(int i, int j) {
		mapData[i][j] = questMaster;	
	}
	private void setBoss(int i, int j) {
		mapData[i][j] = boss;	
	}
	/*
	 * ************************************************************************************************************
	 * 
	 *                   randomField()     create EmptyField()   gen()
	 * 
	 * ************************************************************************************************************
	 */
	/*
	 * Erstellt ein randomisiertes Feld 
	 */
	public void randomField(int line, int column) {
		if (column < 3 || column % 2 == 0 || line < 3 || line % 2 == 0) {
			System.out.println("fehler");
			System.exit(0);
		} else {
			setMapData(new int[line][column]);
			setColumnLength(column);
			setLineLength(line);
			createEmptyField(column, line, mapData);
			gen(1, 1);
		}
	}
	private void createEmptyField(int line, int column, int[][] field) {
		for (int xAchse = 0; xAchse < column; xAchse++) {
			for (int i = 0; i < line; i++) {
				field[xAchse][i] = wall;
			}
		}
	}

	private void gen(int x, int y) {
		DirectionForMazeEngine direction;
		do {
			direction = randomDirection(x, y); // hole eine richtung
			if (direction == DirectionForMazeEngine.NONE) { // wenn keine moeglich ist, dann
												// mache nichts mehr
				int ikon = randomIkon();			
				this.mapData[x][y] = ikon;
				return;
			}
			destroyWalls(direction, x, y); // mache die wand weg
			gen(x + direction.getLine(), y + direction.getColumn()); // rufe
																	// rekursiv
																		// auf
		} while (true); // wenn der aufruf nicht weiter machen konnte, macht der
						// aufrufer hier weiter und testen ob eine weitere
						// richtung moeglich ist
	}

	/*
	 * Entfernt die Wand zwischen alter und neuer Player position
	 */
	private void destroyWalls(DirectionForMazeEngine d, int x, int y) {
		switch (d) {
		case EAST:
			mapData[x][y + 1] = way;
			mapData[x][y + 2] = way;
			break;
		case NORTH:
			mapData[x - 2][y] = way;
			mapData[x - 1][y] = way;
			break;
		case SOUTH:
			mapData[x + 2][y] = way;
			mapData[x + 1][y] = way;
			break;
		case WEST:
			mapData[x][y - 2] = way;
			mapData[x][y - 1] = way;
			break;
		default:
			break;
		}
	}
	/*
	 * ************************************************************************************************************
	 * 
	 *                                          randomIkon()
	 * 
	 * ************************************************************************************************************
	 */
	private int randomIkon() {
		boolean check = true;	
		while (check) {
			int ikon = (int) (Math.random() * ikonList.size() );
			switch (ikon) {
			case wall:
				return wall;		
			case way:
				return way;	
			case fountain:
				if (countHeal != 2) {
					countHeal++;
					return fountain;
				} 
				break;			
			case enemy:
				if (countEnemys != 5) {
					countEnemys++;
					return enemy;	
				}
						return wall;
			}
		}
		return columnLength;
	}
	/*
	 * ************************************************************************************************************
	 * 
	 *               randomDirection()  isWall()   saveDirection()    behindMaze()
	 * 
	 * ************************************************************************************************************
	 */
	
	/*
	 * Gibt eine randomisierte Richtung an
	 */
	private DirectionForMazeEngine randomDirection(int x, int y) {
		DirectionForMazeEngine[] directions = new DirectionForMazeEngine[4]; // Array da wir nur 4
													// verschiedene gueltige
													// Richtungen haben
		int i = 0;
		if (saveDirection(DirectionForMazeEngine.NORTH, x, y)) { // wenn er nach oben kann
													// dann packe das ins Array
													// und erhoehe den
													// laengenindex um eins
			directions[i++] = DirectionForMazeEngine.NORTH;
		}
		if (saveDirection(DirectionForMazeEngine.EAST, x, y)) {
			directions[i++] = DirectionForMazeEngine.EAST;
		}
		if (saveDirection(DirectionForMazeEngine.SOUTH, x, y)) {
			directions[i++] = DirectionForMazeEngine.SOUTH;
		}
		if (saveDirection(DirectionForMazeEngine.WEST, x, y)) {
			directions[i++] = DirectionForMazeEngine.WEST;
		}

		if (i == 0) { // wenn keine richtung moeglich war, dann gibt NONE fuer
						// keine richtung zurueck
			return DirectionForMazeEngine.NONE;
		} else {
			return directions[(int) (Math.random() * i)]; // zufaellige richtung
															// ausgeben
		}
	}
	/*
	 * Diese Methode überprüft ob die gewählte Richtung nicht am Rand oder am
	 * ende des Labirynts oder Wand ist
	 */
	private boolean saveDirection(DirectionForMazeEngine direction, int x, int y) {
		if (direction == null) {
			System.err.print("Direction = null");
		} else {
			if (!(behindMaze(x + direction.getLine(),
					(y + direction.getColumn())))
					&& (isWall(x + direction.getLine(),
							(y + direction.getColumn())))) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Überprüft ob gewählte richtung ausherbald des Labirynths
	 */
	private boolean behindMaze(int line, int column) {
		if ((line <= 0) || (column <= 0) || column >= getColumnLength()
				|| (line >= getLineLength())) {
			return true;
		}
		return false;
	}

	/*
	 * Überprüft ob der "Nachbar" eine Wand ist
	 */
	private boolean isWall(int i, int j) {
		if (behindMaze(i, j)) {
			return false;
		} else if (mapData[i][j] == wall) {
			return true;
		}
		return false;
	}

	/*
	 * ************************************************************************************************************
	 * 
	 *                             goTO()        genutz von GameBoard
	 * 
	 * ************************************************************************************************************
	 */
	/*
	 * Methode um die richtung zu gehen die der player angibt aus Klasse
	 * GameBoard
	 */
	public String goTo(Direction choosenDirection) {
		this.choosenDirection = choosenDirection;
		int tempLine = pInLine + choosenDirection.getLine();
		int tempColumn = pInColumn + choosenDirection.getColumn();
		switch (mapData[tempLine][tempColumn]) {
		case wall:
			return "Wall";
		case way:
			changeCurrentMap(tempLine, tempColumn);
			return ".";
		case fountain:
			changeCurrentMap(tempLine, tempColumn);
			return "Heal";
		case shopKeeper:
			return "Shop";
		case enemy:
			changeCurrentMap(tempLine, tempColumn);
			return "Enemy";
		case boss:
			changeCurrentMap(tempLine, tempColumn);
			return "Boss";
		case questMaster:
			return "Quest";
		}
		return null;
	}
	/*
	 * ************************************************************************************************************
	 * 
	 *                             clearSection()     changeCurrentMap()  useBomb()
	 * 
	 * ************************************************************************************************************
	 */

	public void clearSection() {
		mapData[pInLine][pInColumn] = 1;
	}

	public void changeCurrentMap(int line, int column) {
		clearSection();
		mapData[line][column] = player;
		setPlayersPosition(line, column);
	}
	public boolean destroyWallWithBomb() {
		int tempLine = pInLine + this.choosenDirection.getLine();
		int tempColumn = pInColumn + this.choosenDirection.getColumn();
		if(behindMaze(tempLine, tempColumn)) {
			return false;		
		}else {
			mapData[tempLine][tempColumn] = way;
			return true;
		}	
	}
	/*
	 * ************************************************************************************************************
	 * 
	 *                                           printMap()
	 * 
	 * ************************************************************************************************************
	 */

	public void printMap() {
		System.out.println("\n-------------------------------------------------");
		for (int line = 0; line < mapData.length; line++) {
			for (int column = 0; column < mapData[line].length; column++) {
				if (mapData[line][column] == wall) {
					System.out.print("|xxx|");
				} else if (mapData[line][column] == way) {
					System.out.print("     ");
				} else if (mapData[line][column] == fountain) {
					System.out.print("--@--");
				} else if (mapData[line][column] == shopKeeper) {
					System.out.print("{$.$}");
				} else if (mapData[line][column] == enemy) {
					System.out.print("^-_-^");
				} else if (mapData[line][column] == boss) {
					System.out.print("^o,o^");
				} else if (mapData[line][column] == player) {
					System.out.print("{-.-}");
				} else if (mapData[line][column] == questMaster) {
					System.out.print("{q.q}");	
				}
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------");
	}
}