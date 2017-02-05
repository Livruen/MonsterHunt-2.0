package Maze;

/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
public enum Direction {

	WEST( 0, -1 ), 
	NORTH(-1, 0 ),
	EAST( 0, 1 ),
	SOUTH( 1, 0 );
	
	final int line;
	final int column;
	private Direction(int line,int column) {
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
