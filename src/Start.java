import gameIO.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Maze.GameField;
import Quest.QuestMaster;
import java.io.IOException;

import java.util.ArrayList;

import Characters.UsersInput;

/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */

public class Start implements UsersInput {

	private static ArrayList savedGames ;
	private static GameBoard currentGame;

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws InterruptedException,
			IOException {
//		Gui startGame = new Gui();
	


		Reader reader = new Reader();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));
		
		
		boolean endGame = false;
		while (!endGame) {
			reader.printPicture("Title.txt");
			String input = bReader.readLine();
			input = input.toLowerCase();
			switch (input) {
			case NEW_GAME:	
		    currentGame =  GameBoard.newInstance();
		    currentGame.initializeGameParameters();
		    currentGame.chooseCharacter();
		    currentGame.playOnField();
			saveGame();	
			break;
			case LOAD_GAME:
				GameBoard gameLoad = GameBoard.loadGame();
				gameLoad.playOnField();
				gameLoad = null;
				break;
			case EXIT:
				endGame = true;
			}
		}
		bReader.close();
		System.exit(0);
	}



	private static void saveGame() {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.println("Do you want to save the Game?");
		String input;
		try {
			input = bReader.readLine();
			input = input.toLowerCase();
			switch(input) {
			case YES:
				currentGame.saveGame();
				addCurrentGameToList();
				break;
			case NEW_GAME:
				currentGame = null;
				break;							
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private static void addCurrentGameToList() {
		// TODO Auto-generated method stub
		
	}	
}
