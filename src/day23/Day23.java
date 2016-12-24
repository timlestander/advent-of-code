package day23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day23 {

	HashMap<String, Integer> registers = new HashMap<String, Integer>();
	String[] instructions = new String[26];

	public void solvePuzzle() throws IOException {
		
		// Change file name to input2.txt for part 2
		FileReader fr = new FileReader("src/day23/input.txt");
		BufferedReader br = new BufferedReader(fr);
		
		// Change register a's starting value to 12 for part 2
		registers.put("a", 7);
		registers.put("b", 0);
		registers.put("c", 0);
		registers.put("d", 0);
		
		String line = "";
		int arrIdx = 0;
		while ((line = br.readLine()) != null) {
			instructions[arrIdx] = line;
			arrIdx++;
		}
		
		int index = 0;
		while (index < instructions.length) {
		
			String[] instruction = instructions[index].split(" ");
			String instr = instruction[0];
			String arg1 = instruction[1];
			String arg2 = (instruction.length >= 3) ? instruction[2] : null;
			
			if (instr.equals("cpy")) {
				if (Character.isLetter(arg1.charAt(0))) {
					registers.put(arg2, registers.get(arg1));
				} else {
					registers.put(arg2, Integer.parseInt(arg1));
				}	
			} else if (instr.equals("tgl")) {
				int tglIdx = registers.get(arg1)+index;
				if (tglIdx < instructions.length) {
					String[] tglInstr = instructions[tglIdx].split(" ");
					String tglArg1 = tglInstr[1];
					if (tglInstr.length == 2) {
						if (instructions[tglIdx].contains("inc")) {
							instructions[tglIdx] = "dec" + " " + tglArg1;
						} else {
							instructions[tglIdx] = "inc" + " " + tglArg1;
						}
					} else {
						String tglArg2 = (tglInstr.length >= 3) ? tglInstr[2] : null;
						if (instructions[tglIdx].contains("jnz")) {
							instructions[tglIdx] = "cpy" + " " + tglArg1 + " " + tglArg2;
						} else {
							instructions[tglIdx] = "jnz" + " " + tglArg1 + " " + tglArg2;
						}
					}
				}
			} else if (instr.equals("jnz")) {
				if (Character.isDigit(arg1.charAt(0))) {
					if (Character.isDigit(arg2.charAt(0))) {
						if (Integer.parseInt(arg1) != 0 ) {
							if (Integer.parseInt(arg2)+index-1 > 0) {
								index += Integer.parseInt(arg2) - 1;
							}
						} 
					} else {
						if (Integer.parseInt(arg1) != 0 && registers.get(arg2) != 0) {
							if (registers.get(arg2)-1+index > 0) {
								index += registers.get(arg2) - 1;
							}
						}
					}
				} else if (Character.isLetter(arg1.charAt(0))) {
					if (Character.isLetter(arg2.charAt(0))) {
						if (registers.get(arg1) != 0 && registers.get(arg2) != 0) {
							if (registers.get(arg1)-1+index > 0) {
								index += registers.get(arg2)-1;
							}
						}
					} else {
						if (registers.get(arg1) != 0) {
							if (Integer.parseInt(arg2) != 0) {
								if (Integer.parseInt(arg2)-1+index > 0) {
									index += Integer.parseInt(arg2)-1;
								}
							}
						}
					}
				} 
			} else if (instr.equals("inc")) {
				registers.put(arg1, registers.get(arg1) + 1);
			} else if (instr.equals("dec")) {
				registers.put(arg1, registers.get(arg1) - 1);
			} else if (instr.equals("mul")) {
				String arg3 = instruction[3];
				registers.put(arg3, registers.get(arg1)*registers.get(arg2));
			}

			index++;

		}
		
		printRegisters();
	}

	public void printRegisters() {
		System.out.println("Registers are now: ");
		for (Map.Entry<String, Integer> entry : registers.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	public static void main(String[] args) throws IOException {
		new Day23().solvePuzzle();
	}

}
