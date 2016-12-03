package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {

	public static void main(String[] args) throws IOException {
		
		FileReader r = new FileReader("src/day3/input.txt");
		BufferedReader br = new BufferedReader(r);
		int triangleCount = 0;
		String[][] inputData = new String[3][3];
		int rowCounter = 0;
		String tempLine;
		
		while ((tempLine = br.readLine()) != null) {
			inputData[rowCounter] = tempLine.trim().split("\\s+");
			rowCounter++;
			
			if (rowCounter == 3) {
				for (int x=0; x<3; x++) {
					int a = Integer.parseInt(inputData[0][x]);
					int b = Integer.parseInt(inputData[1][x]);
					int c = Integer.parseInt(inputData[2][x]);
					
					if ((a+b>c) && (a+c>b) && (b+c>a)) {
						triangleCount++;
					}
				}
				rowCounter = 0;
			}
		}
		
		System.out.println("Triangle count is: " + triangleCount);

	}

}
