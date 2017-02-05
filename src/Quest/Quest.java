package Quest;

import java.io.Serializable;
import java.util.StringTokenizer;

import Items.Item;



public class Quest implements Serializable {
	private String nameOfQuest;
	private String nameOfPrequest;
	private String neededQuestItems;
	private int quantity;
	private Item price;

	public Quest(String line) {
		StringTokenizer buff = new StringTokenizer(line);
		setNameOfQuest(buff.nextToken(","));
		setNameOfPrequest(buff.nextToken(","));
		setNeededQuestItems(buff.nextToken(","));
		String temp =  buff.nextToken(",");
		temp = temp.trim();
		setQuantity(Integer.parseInt(temp));
	}
	
	public Item getPrice() {
		return price;
	}

	public void setPrice(Item price) {
		this.price = price;
	}

	public String getNameOfPrequest() {
		return nameOfPrequest;
	}
	

	public void setNameOfPrequest(String nameOfPrequest) {
		this.nameOfPrequest = nameOfPrequest;
		nameOfPrequest = nameOfPrequest.trim();
	}

	public String getNameOfQuest() {
		return nameOfQuest;
	}

	public void setNameOfQuest(String nameOfQuest) {
		this.nameOfQuest = nameOfQuest;
		nameOfQuest = nameOfQuest.trim();
	}

	public String getNeededQuestItems() {
		return neededQuestItems;
	}

	public void setNeededQuestItems(String neededQuestItems) {
		this.neededQuestItems = neededQuestItems.trim();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString(){
		System.out.printf("\n# Quest name : [%s]\n",this.nameOfQuest);
		System.out.printf("# You need to collect %d %s\n", quantity,neededQuestItems);
		return "";
	}
}
