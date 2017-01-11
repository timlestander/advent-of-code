package day10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day10 {
	
	static ArrayList<Bot> bots = new ArrayList<Bot>();
	static int[] output = new int[100];
	
	public void solveProblem() throws IOException {
		FileReader fr = new FileReader("src/day10/input.txt");
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> instructions = new ArrayList<String>();
		
		String line = "";
		while ((line = br.readLine()) != null) {
			instructions.add(line);
		}
		
		for (int x=0; x<300; x++) {
			Bot b = new Bot(x);
			bots.add(b);
		}
		
		while (instructions.size() > 0) {
			for (int x=0; x<instructions.size(); x++) {
				//System.out.println("EXECUTING: " + instructions.get(x));
				String[] instr = instructions.get(x).split(" ");
				if (instr[0].equals("value")) {
					Bot bot = findBot(Integer.parseInt(instr[5]));
					//System.out.println("GIVING " + bot.id + " VALUE " + Integer.parseInt(instr[1]));
					bot.values.add(Integer.parseInt(instr[1]));
					instructions.remove(x--);
				} else {
					Bot bot = findBot(Integer.parseInt(instr[1]));
					if (bot.values.size() >= 2) {
						instructions.remove(x--);
						int high = bot.findHighestValue();
						int low = bot.findLowestValue();
						//System.out.println("HIGH IS: " + high);
						//System.out.println("LOW IS: " + low);
						
						if (high == 61 || low == 61) {
							if (low == 17 || high == 17) {
								System.out.println("Bot-ID: " + bot.id);
							}
						}
						
						if (instr[5].equals("bot")) {
							Bot b = findBot(Integer.parseInt(instr[6]));
							b.values.add(low);
						} else {
							output[Integer.parseInt(instr[6])] = low;
						}
						
						if (instr[10].equals("bot")) {
							Bot b = findBot(Integer.parseInt(instr[11]));
							b.values.add(high);
						} else {
							output[Integer.parseInt(instr[11])] = high;
						}
					}
					
					if (output[0] != 0 && output[1] != 0 && output[2] != 0) {
						System.out.println("A: " + output[0]*output[1]*output[2]);
					}
				}
			}
			
		}
		
        System.out.println("Output");
        for(Integer i : output) {
            System.out.println(i);
        }
	}
	
	public Bot findBot(int id) {
		for (int x=0; x<bots.size(); x++) {
			if (bots.get(x).id == id) {
				return bots.get(x);
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		new Day10().solveProblem();

	}

}
