package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {

	public static void main(String[] args) throws IOException {
		
		FileReader r = new FileReader("src/day3/input.txt");
		BufferedReader br = new BufferedReader(r);
		String[] inputRow = null;
		int triangleCount = 0;
		
		String tempLine;
		while ((tempLine = br.readLine()) != null) {
			inputRow = tempLine.trim().split("\\s+");
			System.out.println(tempLine);
			int a = Integer.parseInt(inputRow[0]);
			int b = Integer.parseInt(inputRow[1]);
			int c = Integer.parseInt(inputRow[2]);
			if ((a+b>c) && (b+c>a) && (a+c>b)) {
				triangleCount++;
			}
		}
		
		System.out.println("Triangle count is: " + triangleCount);

	}

}
