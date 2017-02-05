package Items;

import gameIO.Reader;

import java.io.IOException;
import java.util.LinkedList;


/*
 * @author Livruen Nati
 * @version 1.0
 * 
 */
public class Fairy extends Item {
	private LinkedList<Item> fairysPrezent = new LinkedList<Item>();

	public Fairy() {
		super("Fairy", 1000, 2);
		fairysPrezent.add(new Rubin());
		fairysPrezent.add(new Bomb());
		fairysPrezent.add(new Armor("Rabit Shirt", 10, 1));
		fairysPrezent.add(new Weapon("Encyclopedia Galactica", 2000, 1));
	}

	public Item sparkle() {
		Reader reader = new Reader();
		try {
			int goodMood = (int) (Math.random() * 6);
			System.out.println(goodMood);
			if (goodMood != 2 && goodMood != 6) {
				System.out.println("You are lucky. I am in a good mood.");
				Thread.sleep(3000);
				reader.printPicture("Fairy1.txt");
				return fairysPrezent.get((int) (Math.random() * (fairysPrezent
						.size())));
			} else {
				reader.printPicture("Fairy2.txt");
				System.out
						.println("No no no! Everything is stupid!\n SPARKLE ... SPARKLE ... \n ups ... The fairy was upset. She took you some Money.");
				return null;

			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
