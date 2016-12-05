package day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Part2 {

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
			for (int x = 0; x < line.length(); x++) {
				if (Character.isDigit(line.charAt(x))) {
					break;
				}
				counter++;
			}
			roomNames.add(line.substring(0, counter - 1));
			roomIds.add(line.replaceAll("\\D+", ""));
			roomChecks.add(line.substring(line.indexOf('[') + 1, line.indexOf(']')));
		}

		int sectorSumId = 0;
		for (int x = 0; x < roomNames.size(); x++) {
			if (validateRoom(roomNames.get(x), roomChecks.get(x))) {
				sectorSumId += Integer.parseInt(roomIds.get(x));
				if (decryptName(roomNames.get(x), roomIds.get(x)).contains("north")) {
					System.out.println(decryptName(roomNames.get(x), roomIds.get(x)) + " is the correct one with id: " + roomIds.get(x));
				}
			}
		}

		System.out.println("Sum of valid room ID:s " + sectorSumId);

	}

	public static boolean validateRoom(String roomName, String checkSum) {

		Map<Character, Integer> occurrences = new HashMap<Character, Integer>();

		// Calculate occurrences of every letter
		for (int x = 0; x < roomName.length(); x++) {
			if (roomName.charAt(x) != '-') {
				if (occurrences.containsKey(roomName.charAt(x))) {
					int count = occurrences.get(roomName.charAt(x));
					occurrences.put(roomName.charAt(x), count + 1);
				} else {
					occurrences.put(roomName.charAt(x), 1);
				}
			}
		}

		Character firstChar = null;
		for (int x = 0; x < checkSum.length(); x++) {
			char checkChar = checkSum.charAt(x);

			// Calculate first char of checksum
			int mostOccurrences = 0;
			for (Map.Entry<Character, Integer> entry : occurrences.entrySet()) {
				if (entry.getValue() > mostOccurrences) {
					mostOccurrences = entry.getValue();
					firstChar = entry.getKey();
				} else if (entry.getValue() == mostOccurrences) {
					if (entry.getKey() < firstChar) {
						firstChar = entry.getKey();
						mostOccurrences = entry.getValue();
					}
				}
			}

			// Handle special cases
			if ((!occurrences.containsKey(checkChar) && occurrences.size() >= checkSum.length()) || checkSum.charAt(0) != firstChar) {
				return false;
			}

			if (x > 0) {
				char prevCheckChar = checkSum.charAt(x - 1);
				int currCharOccurrences = occurrences.get(checkChar);
				int prevCharOccurrences = occurrences.get(prevCheckChar);

				if (prevCharOccurrences < currCharOccurrences) {
					return false;
				} else if (currCharOccurrences == prevCharOccurrences) {
					if ((int) prevCheckChar >= (int) checkChar) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static String decryptName(String roomName, String roomId) {

		int chifferIdx = Integer.parseInt(roomId) % 26;
		String decryptedName = "";
		for (int x = 0; x < roomName.length(); x++) {
			char tempChar = roomName.charAt(x);
			if (tempChar != '-') {
				if ((tempChar + chifferIdx) <= 'z') {
					tempChar += chifferIdx;
				} else {
					tempChar += (chifferIdx - 26);
				}
				decryptedName += tempChar;
			} else {
				decryptedName += '-';
			}
		}
		return decryptedName;
	}

	public static void debugHelper(String roomName, String roomCheck,
			String roomId) {

		if (validateRoom(roomName, roomCheck)) {
			System.out.println("THIS IS VALID: " + roomName + "-" + roomId + "[" + roomCheck + "]");
		} else {
			System.out.println("THIS IS INVALID: " + roomName + "-" + roomId + "[" + roomCheck + "]");
		}
	}

}
