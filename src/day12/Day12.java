package day12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day12 {

	public void findPassword() throws IOException {
		
		FileReader fr = new FileReader("src/day12/input.txt");
		BufferedReader br = new BufferedReader(fr);

		HashMap<String, Integer> registers = new HashMap<String, Integer>();
		ArrayList<String> input = new ArrayList<String>();

		registers.put("a", 0);
		registers.put("b", 0);
		// Change value of c to 1 for part 2
		registers.put("c", 0);
		registers.put("d", 0);

		String line = "";
		while ((line = br.readLine()) != null) {
			input.add(line);
		}

		int index = 0;
		while (index < input.size()) {
			String[] instructions = input.get(index).split(" ");
			String instruction = instructions[0];
			String arg1 = instructions[1];
			String arg2 = (instructions.length == 3) ? instructions[2] : null;

			if (instruction.equals("cpy")) {
				if (Character.isLetter(arg1.charAt(0))) {
					registers.put(arg2, registers.get(arg1));
				} else {
					registers.put(arg2, Integer.parseInt(arg1));
				}
			} else if (instruction.equals("inc")) {
				registers.put(arg1, registers.get(arg1) + 1);
			} else if (instruction.equals("dec")) {
				registers.put(arg1, registers.get(arg1) - 1);
			} else if (instruction.equals("jnz")) {
				if (Character.isDigit(arg1.charAt(0))) {
					if (Integer.parseInt(arg1) != 0) {
						index += Integer.parseInt(arg2) - 1;
					}
				} else {
					if (registers.get(arg1) != 0) {
						index += Integer.parseInt(arg2) - 1;
					}
				}
			}
			index++;
		}

		System.out.println("Answer is: " + registers.get("a"));

	}

	public static void main(String[] args) throws IOException {
		new Day12().findPassword();
	}
}
