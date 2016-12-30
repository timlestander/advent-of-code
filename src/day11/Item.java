package day11;

public class Item {
	
	String element;
	char purpose;
	
	public Item(String element, char purpose) {
		this.element = element;
		this.purpose = purpose;
	}
	
	public boolean isCounterPart(Item other) {
		return other.element.equals(element) && other.purpose != purpose;
	}
	
	public String toString() {
		return element.substring(0, 2) + purpose;
	}

}
