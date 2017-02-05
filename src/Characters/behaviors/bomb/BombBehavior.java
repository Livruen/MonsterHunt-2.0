package Characters.behaviors.bomb;

import Maze.GameField;

public interface BombBehavior {
	public boolean decideToUseBomb();
	public void useBomb(GameField maze);

}
