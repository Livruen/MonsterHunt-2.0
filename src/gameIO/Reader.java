package gameIO;

import java.io.*;

/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
public class Reader {
	
	public void printPicture(String pictureName) throws IOException {
		FileReader file = new FileReader(pictureName);
		BufferedReader reader = new BufferedReader(file);
		String tempLine = null;
		while ((tempLine = reader.readLine()) != null) {
			System.out.println(tempLine);
		}
	}

	public void printPictureSlow(String string) {
		try {
			FileReader file = new FileReader(string);
			BufferedReader reader = new BufferedReader(file);
			String tempLine = null;
			while ((tempLine = reader.readLine()) != null) {
				System.out.println(tempLine);
				Thread.sleep(1500);
			}
		} catch (IOException | InterruptedException e) {
			System.err.print("Cannot read " + string);
		}
	}
}