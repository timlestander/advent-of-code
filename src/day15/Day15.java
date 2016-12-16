package day15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 {
	
	ArrayList<Disc> discs = new ArrayList<Disc>();

	public void findStartTime() throws IOException {

		createDiscs();
		
		int start = 0;
		while (true) {
			int x = 0;
			boolean oneFailure = false;
			for (Disc d : discs) {
				x++;
				if (d.newPosition(start + x) != 0) {
					oneFailure = true;
					break;
				}
				
			}	
			
			if (!oneFailure) {
				System.out.println("The start button should be pressed after: " + start + " seconds");
				return;
			}
			
			start++;
		}
	}
	
	public void createDiscs() {
		discs.add(new Disc(13, 11));
		discs.add(new Disc(5, 0));
		discs.add(new Disc(17, 11));
		discs.add(new Disc(3, 0));
		discs.add(new Disc(7, 2));
		discs.add(new Disc(19, 17));
		discs.add(new Disc(11, 0));
	}
	
	public static void main(String[] args) throws IOException {
		new Day15().findStartTime();
	}

}
