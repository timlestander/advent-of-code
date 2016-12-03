package day2;

import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		char[][] numPad = new char[][]{{'-', '-', '1', '-', '-'},
									   {'-', '2', '3', '4', '-'},
									   {'5', '6', '7', '8', '9'},
									   {'-', 'A', 'B', 'C', '-'},
									   {'-', '-', 'D', '-', '-'}};

		// Read input data
		Scanner scanner = new Scanner(Part2.class.getResourceAsStream("input.txt"));
		ArrayList<String> instructions = new ArrayList<String>();
		while (scanner.hasNext()) {
			instructions.add(scanner.next());
		}

		int currX = 0;
		int currY = 2;
		for (String instruction : instructions) {
			for (char letter : instruction.toCharArray()) {
				
				// Don't allow movement out of bounds
				if ((letter == 'D' && currY == 4) || (letter == 'U' && currY == 0) || (letter == 'R' && currX == 4) || (letter == 'L' && currX == 0)) {
					continue;
				}
				
				// Don't allow movement to false positions
				if ((letter == 'D' && numPad[currX][currY+1] == '-') || (letter == 'U' && numPad[currX][currY-1] == '-') || (letter == 'R' && numPad[currX+1][currY] == '-') || (letter == 'L' && numPad[currX-1][currY] == '-')) {
						continue;
				} else {
					switch (letter) {
					case 'U': 
						currY--;
						break;
					case 'D':
						currY++;
						break;
					case 'R':
						currX++;
						break;
					case 'L':
						currX--;
						break;
					default: 
						System.out.println("This is default. Something went wrong.");
						break;
					}
				}

			}

			System.out.print(numPad[currY][currX]);
		}



	}

}
