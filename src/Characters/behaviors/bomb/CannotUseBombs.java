package Characters.behaviors.bomb;

import Maze.GameField;

public class CannotUseBombs implements BombBehavior {

	@Override
	public boolean decideToUseBomb() {
		return false;
	}

	@Override
	public void useBomb(GameField maze) {
		System.out.println("Cannot use Bombs");
	}
	

}
