package day11;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day11 {
	
	// I was lazy and took the easy way with a formula for calculating the total distance
	// Formula is: 2 * (total distance to get all items to top floor) - 9
	// Thanks to Mike Heaton (https://github.com/mikeheaton) for sharing.

	public void solvePuzzle() throws IOException {
		FileReader fr = new FileReader("src/day11/input.txt");
		BufferedReader br = new BufferedReader(fr);
		String regex = "([a-z]+)( generator|\\-compatible microchip)";
		int distanceValue = 3;
		int totalDistance = 0;
		Pattern p = Pattern.compile(regex);
		Matcher m;
		
		String line = "";
		while ((line = br.readLine()) != null) {
			m = p.matcher(line);
			while (m.find()) {
				totalDistance += distanceValue;
			}
			distanceValue--;
		}
		System.out.println(doTheMagic(totalDistance));
	}
	
	public int doTheMagic(int distance) {
		return 2*distance - 9;
	}

	public static void main (String[] args) throws IOException {
		new Day11().solvePuzzle();
	}

}
