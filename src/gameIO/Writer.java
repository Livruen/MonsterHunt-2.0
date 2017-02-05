package gameIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	public void writeIntoFile(){
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter("savedGameList"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
