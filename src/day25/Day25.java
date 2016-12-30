package day25;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day25 {

	HashMap<String, Integer> registers = new HashMap<String, Integer>();
	ArrayList<String> instructions = new ArrayList<String>();
	
	public void solvePuzzle() throws IOException {
		FileReader fr = new FileReader("src/day25/input.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String line = "";
		while ((line = br.readLine()) != null) {
			instructions.add(line);
		}
		
		for (int ans=0; ans<Integer.MAX_VALUE; ans++) {
			StringBuilder sb = new StringBuilder();
			registers.put("a", ans);
			registers.put("b", 0);
			registers.put("c", 0);
			registers.put("d", 0);
			
			int counter = 0;
			int index = 0;
			while (index < instructions.size()) {
				String[] instr = instructions.get(index).split(" ");
				String cmd = instr[0];
				String arg1 = instr[1];
				String arg2 = (instr.length == 3) ? instr[2] : null;
				
				if (cmd.equals("inc")) {
					registers.put(arg1, registers.get(arg1)+1);
				} else if (cmd.equals("dec")) {
					registers.put(arg1, registers.get(arg1)-1);
				} else if (cmd.equals("cpy")) {
					if (registers.containsKey(arg1)) {
						registers.put(arg2, registers.get(arg1));
					} else {
						registers.put(arg2, Integer.parseInt(arg1));
					}
				} else if (cmd.equals("jnz")) {
					if (registers.containsKey(arg1)) {
						if (registers.get(arg1) != 0) {
							index += Integer.parseInt(arg2)-1;
						}
					} else {
						if (Integer.parseInt(arg1) != 0) {
							index += Integer.parseInt(arg2)-1;
						}
					}
				} else if (cmd.equals("out")) {
					if (sb.length() == 0 && registers.get(arg1) == 0)
						sb.append(registers.get(arg1));
					else if (sb.length() == 0 && registers.get(arg1) != 0)
						break;
					else if (sb.charAt(sb.length()-1) == '0' && registers.get(arg1) == 1)
						sb.append(registers.get(arg1));
					else if (sb.charAt(sb.length() - 1) == '1' && registers.get(arg1) == 0)
						sb.append(registers.get(arg1));
					else 
						break;

				}
				
				if (sb.length() > 100) {
					break;
				}
				
				// printRegisters();
				index++;
			}
			
			if (sb.length() > 100) {
				System.out.println("Solution is: " + ans);
				break;
			}
		}
	}
	
	public void printRegisters() {
		
		for (Map.Entry<String, Integer> entry : registers.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
		
	public static void main(String[] args) throws IOException {
		new Day25().solvePuzzle();
	}
}
