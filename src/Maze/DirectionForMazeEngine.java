package Maze;

/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
public enum DirectionForMazeEngine {
	NONE(0,0),
	WEST(0,- 2), 
	NORTH(-2,0),
	EAST(0,2),
	SOUTH(2,0);
	
	final int line;
	final int column;
	private DirectionForMazeEngine(int line,int column) {
		this.line = line;
		this.column=column;
	}
	public int getLine() {
		return this.line;
	}
	public int getColumn() {
		return this.column;
	}
}
