package day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Part1 {

	public static void main(String[] args) throws IOException {

		FileReader r = new FileReader("src/day4/input.txt");
		BufferedReader br = new BufferedReader(r);
		ArrayList<String> roomNames = new ArrayList<String>();
		ArrayList<String> roomIds = new ArrayList<String>();
		ArrayList<String> roomChecks = new ArrayList<String>();
		
		// Read and extract relevant data
		String line = null;
		while ((line = br.readLine()) != null) {
			int counter = 0;
			for (int x=0; x<line.length(); x++) {
				if (Character.isDigit(line.charAt(x))) {
					break;
				} 
				counter++;
			}
			roomNames.add(line.substring(0, counter-1).replaceAll("-", ""));
			roomIds.add(line.replaceAll("\\D+",""));
			roomChecks.add(line.substring(line.indexOf('[')+1, line.indexOf(']')));
		}
		
		int sectorSumId = 0;
		for (int x=0; x<roomNames.size(); x++) {
			if (validateRoom(roomNames.get(x), roomChecks.get(x))) {
				sectorSumId += Integer.parseInt(roomIds.get(x));
			}
		}
		
		System.out.println("Sum of valid room ID:s " + sectorSumId);

	}
	
	public static boolean validateRoom(String roomName, String checkSum) {
		
		Map<Character, Integer> occurances = new HashMap<Character, Integer>();
		
		// Calculate occurences of every letter
		for (int x=0; x<roomName.length(); x++) {
			if (occurances.containsKey(roomName.charAt(x))) {
				int count = occurances.get(roomName.charAt(x));
				occurances.put(roomName.charAt(x), count+1);
			} else {
				occurances.put(roomName.charAt(x), 1);
			}
		}
		
		Character firstChar = null;
		for (int x=0; x<checkSum.length(); x++) {
			char checkChar = checkSum.charAt(x);
			
			// Calculate first char of checksum
			int mostOccurances = 0;
			for (Map.Entry<Character, Integer> entry : occurances.entrySet()) {
				if (entry.getValue() > mostOccurances) {
					mostOccurances = entry.getValue();
					firstChar = entry.getKey();
				} else if (entry.getValue() == mostOccurances) {
					if (entry.getKey() < firstChar) {
						firstChar = entry.getKey();
						mostOccurances = entry.getValue();
					}
				}
			}
		
			// Handle special cases
			if ((!occurances.containsKey(checkChar) && occurances.size() >= checkSum.length()) || checkSum.charAt(0) != firstChar) {
				return false;
			}
			
			
			if (x>0) {
				char prevCheckChar = checkSum.charAt(x-1);
				int currCharOccurances = occurances.get(checkChar);
				int prevCharOccurances = occurances.get(prevCheckChar);
				
				if (prevCharOccurances < currCharOccurances) {
					return false;
				} else if (currCharOccurances == prevCharOccurances) {
					if ((int) prevCheckChar >= (int) checkChar) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static void debugHelper(String roomName, String roomCheck, String roomId) {
		
		if (validateRoom(roomName, roomCheck)) {
			System.out.println("THIS IS VALID: " + roomName + "-" + roomId + "[" + roomCheck + "]");
		} else {
			System.out.println("THIS IS INVALID: " + roomName + "-" + roomId + "[" + roomCheck + "]");
		}
	}
	
}
