package day8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8 {

	enum Instructions { ROTATEROW, ROTATECOL, RECT };
	
	char[][] display = new char[6][50];
	
	public void findPassword() throws IOException {
		FileReader r = new FileReader("src/day8/input.txt");
		BufferedReader br = new BufferedReader(r);
		
		String line = "";
		while ((line = br.readLine()) != null) {
			
			switch(parseInstruction(line)) {
				case RECT:
					createRect(line);
					break;
				case ROTATEROW:
					System.out.println("ROTATE ROW");
					break;
				case ROTATECOL:
					System.out.println("ROTATE COL");
					break;	
				default:
					System.out.println("Switch case couldn't match any cases in switch");
					break;
			}
			
		}
	}

	public void createRect(String line) {
		Pattern p = Pattern.compile("(\\d+)");
		Matcher m = p.matcher(line);
		
		int[] rectSize = new int[2];
		int idx = 0;
		while (m.find()) {
			rectSize[idx] = Integer.parseInt(m.group(1)); 
			idx++;
		}
		
		for (int x=0; x<rectSize[0]; x++) {
			for (int y=0; y<rectSize[0]; y++) {
				
			}
		}
		
		System.out.println("
	}
	
	public Instructions parseInstruction(String line) {
		
		if (line.contains("rect")) {
			return Instructions.RECT;
		} else if (line.contains("rotate row")) {
			return Instructions.ROTATEROW;
		} else if (line.contains("rotate column")) {
			return Instructions.ROTATECOL;
		}
		
		System.out.println("Wrong while parsing instruction..");
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		new Day8().findPassword();
	}

}
