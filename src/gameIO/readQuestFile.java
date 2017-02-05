package gameIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import Quest.Quest;

public class readQuestFile {
	private BufferedReader bReader;

	public readQuestFile() {
		try {
			bReader = new BufferedReader(new FileReader("questFile.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public LinkedList<Quest> createQuestList() {
		LinkedList<Quest> questList = new LinkedList<>();
		String line;
		try {
			while ((line = bReader.readLine()) != null) {
				questList.add(new Quest(line));
			}
			bReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questList;
	}
}
