package Characters.behaviors.bomb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Maze.GameField;

public class UseBombs implements BombBehavior{
	private final int USE_BOMB = 1;
	private final int DONT_USE_BOMB = 2;
	@Override
	public boolean decideToUseBomb() {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int PlayersDecision;
		try {
			PlayersDecision = Integer.parseInt(bReader.readLine());
			if (PlayersDecision == USE_BOMB) {
				return true;
			} else if (PlayersDecision == DONT_USE_BOMB) {
				return false;
			} else {
				System.out.println("False input");
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void useBomb(GameField maze) {
		maze.destroyWallWithBomb();
	}

}
