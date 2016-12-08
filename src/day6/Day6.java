package day6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day6 {
	
	public void findPassword() throws IOException {
		
		FileReader r = new FileReader("src/day6/input.txt");
		BufferedReader br = new BufferedReader(r);
		ArrayList<String> data = new ArrayList<String>();
		
		String line;
		while ((line = br.readLine()) != null) {
			data.add(line.trim());
		}
		
		String mostCommonPassword = "";
		String leastCommonPassword = "";
		for (int x=0; x<data.get(0).length(); x++) {
			int occurances = 0;
			HashMap<Character, Integer> columnData = new HashMap<Character, Integer>();
			for (int y=0; y<data.size(); y++) {
				char currChar = data.get(y).charAt(x);
				if (!columnData.containsKey(currChar)) {
					columnData.put(currChar, 1);
				} else {
					occurances = columnData.get(currChar);
					columnData.put(currChar, occurances + 1);
				}
			}
			mostCommonPassword += findMostCommonChar(columnData);
			leastCommonPassword += findLeastCommonChar(columnData);
		}
		
		System.out.println("Password with most common letters is: " + mostCommonPassword);
		System.out.println("Password with least common letters is: " + leastCommonPassword);
		
	}
	
	public char findMostCommonChar(HashMap<Character, Integer> columnData) {
		
		char mostCommonChar = 0;
		int mostOccurrences = 0;
		
		for (Map.Entry<Character, Integer> entry : columnData.entrySet()) {
			int value = entry.getValue();
			char key = entry.getKey();
			if (value > mostOccurrences) {
				mostCommonChar = key;
				mostOccurrences = value;
			}
		}
		
		return mostCommonChar;
	}
	
	public char findLeastCommonChar(HashMap<Character, Integer> columnData) {
		
		char leastCommonChar = 0;
		int leastOccurrences = 1000;
		
		for (Map.Entry<Character, Integer> entry : columnData.entrySet()) {
			int value = entry.getValue();
			char key = entry.getKey();
			if (value < leastOccurrences) {
				leastCommonChar = key;
				leastOccurrences = value;
			}
		}
		
		return leastCommonChar;
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new Day6().findPassword();
	}

}
