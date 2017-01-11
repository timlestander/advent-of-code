package day10;

import java.util.ArrayList;

public class Bot {
	
	ArrayList<Integer> values = new ArrayList<Integer>();
	int id = 0;
	
	public Bot(int id) {
		this.id = id;
	}
	
	public int findLowestValue() {
		int tempLow = Integer.MAX_VALUE;
		for (int x=0; x<values.size(); x++) {
			if (values.get(x) < tempLow) {
				tempLow = values.get(x);
			}
		}
		values.remove(values.indexOf(tempLow));
		return tempLow;
	}
	
	public void addValue(int val) {
		values.add(val);
	}
	
	public int findHighestValue() {
		int tempHigh = 0;
		for (int x=0; x<values.size(); x++) {
			if (values.get(x) > tempHigh) {
				tempHigh = values.get(x);
			}
		}
		values.remove(values.indexOf(tempHigh));
		return tempHigh;
	}

}
