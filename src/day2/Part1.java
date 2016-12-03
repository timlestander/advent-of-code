package day2;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.rmi.CORBA.Util;

public class Part1 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		//Insert values for numpad part 1
		int[][] numPad = new int[3][3];
		int number = 1;
		for (int x=0; x<numPad.length; x++) {
			for (int y=0; y<numPad[x].length; y++) {
				numPad[x][y] = number;
				number++;
			}
		}
		
		// Read input data
		Scanner scanner = new Scanner(Part1.class.getResourceAsStream("input.txt"));
		ArrayList<String> instructions = new ArrayList<String>();
		while (scanner.hasNext()) {
			instructions.add(scanner.next());
		}
		
		int currX = 1;
		int currY = 1;
		for (String instruction : instructions) {
			for (char letter : instruction.toCharArray()) {
				if ((letter == 'D' && currY == 2) || 
					(letter == 'U' && currY == 0) || 
					(letter == 'R' && currX == 2) || 
					(letter == 'L' && currX == 0)) {
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
