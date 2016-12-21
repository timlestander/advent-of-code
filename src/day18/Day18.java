package day18;

import java.util.ArrayList;

public class Day18 {
	
	ArrayList<String> rows = new ArrayList<String>();
	String input = ".^.^..^......^^^^^...^^^...^...^....^^.^...^.^^^^....^...^^.^^^...^^^^.^^.^.^^..^.^^^..^^^^^^.^^^..^";
	int WIDTH = input.length();
	int HEIGHT = 40;
	
	public void getAnswer() {
		
		rows.add(input);
		int rowIndex = 0;
		
		while (rowIndex < HEIGHT-1) {
			String prevRow = rows.get(rowIndex);
			String currRow = "";
			for (int x=0; x<WIDTH; x++) {
				char left = (x == 0) ? '.' : prevRow.charAt(x-1);
				char right = (x==WIDTH-1) ? '.' : prevRow.charAt(x+1);
				currRow += (left == right) ? '.' : '^';
			}
			
			rowIndex++;
			rows.add(currRow);
		}
	
		System.out.println("Number of safe tiles: " + countSafeTiles());
	}
	
	public int countSafeTiles() {
		int safeTiles = 0;
		for (int x=0; x<rows.size(); x++) {
			String row = rows.get(x);
			System.out.println((x+1) + ": " + row);
			for (int y=0; y<row.length(); y++) {
				if (row.charAt(y) == '.') {
					safeTiles++;
				}
			}
		}
		return safeTiles;
	}

	public static void main(String[] args) {
		new Day18().getAnswer();

	}

}
