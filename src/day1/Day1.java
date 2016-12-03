package day1;

import java.awt.List;
import java.util.ArrayList;

public class Day1 {
	
	public static ArrayList<Position> visited = new ArrayList<Position>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input = " L2, L5, L5, R5, L2, L4, R1, R1, L4, R2, R1, L1, L4, R1, L4, L4, R5, R3, R1, L1, R1, L5, L1, R5, L4, R2, L5, L3, L3, R3, L3, R4, R4, L2, L5, R1, R2, L2, L1, R3, R4, L193, R3, L5, R45, L1, R4, R79, L5, L5, R5, R1, L4, R3, R3, L4, R185, L5, L3, L1, R5, L2, R1, R3, R2, L3, L4, L2, R2, L3, L2, L2, L3, L5, R3, R4, L5, R1, R2, L2, R4, R3, L4, L3, L1, R3, R2, R1, R1, L3, R4, L5, R2, R1, R3, L3, L2, L2, R2, R1, R2, R3, L3, L3, R4, L4, R4, R4, R4, L3, L1, L2, R5, R2, R2, R2, L4, L3, L4, R4, L5, L4, R2, L4, L4, R4, R1, R5, L2, L4, L5, L3, L2, L4, L4, R3, L3, L4, R1, L2, R3, L2, R1, R2, R5, L4, L2, L1, L3, R2, R3, L2, L1, L5, L2, L1, R4";
		String[] instructions = input.split(",");
		int direction = 0;
		int blocksX = 0;
		int blocksY = 0;
		
		for (int x=0; x<instructions.length; x++) {
			String rot = instructions[x].substring(1,2);
			int distance = Integer.parseInt(instructions[x].substring(2, instructions[x].length()));
		
			direction = newDirection(direction, rot);
			
			// For part 2 to add all steps between
			addToVisited(distance, direction, blocksX, blocksY);
			
			if (direction == 0) {
				blocksY += distance;
			} else if (direction == 1) {
				blocksX += distance;
			} else if (direction == 2) {
				blocksY -= distance;
			} else if (direction == 3) {
				blocksX -= distance;
			}
			
			// System.out.println("We moved " + distance + " blocks towards " + getHeading(direction));
		}
		
		System.out.println("HQ is " + (Math.abs(blocksX) + Math.abs(blocksY)) + " blocks away!");
		findRealHQ();

	}
	
	public static int newDirection(int direction, String rot) {
		
		if (rot.equals("R")) {
			direction++;
		}
		
		if (rot.equals("L")) {
			direction--;
		}
		
		if (direction < 0) {
			direction = 3;
		}
		
		if (direction > 3) {
			direction = 0;
		}
		
		return direction;
	}
	
	public static String getHeading(int direction) {
		switch (direction) {
			case 0: 
				return "NORTH";
			case 1:
				return "EAST";
			case 2:
				return "SOUTH";
			case 3: 
				return "WEST";
			default: 
				return "SOMETHING WENT WRONG";
		}
	}
	
	public static void addToVisited(int distance, int direction, int blocksX, int blocksY) {
		Position tempPos = null;
		for (int x=0; x<distance; x++) {

			if (direction == 0) {
				tempPos = new Position(blocksX, (blocksY+x));
			} else if (direction == 1) {
				tempPos = new Position((blocksX+x), blocksY);
			} else if (direction == 2) {
				tempPos = new Position(blocksX, (blocksY-x));
			} else if (direction == 3) {
				tempPos = new Position(blocksX-x, blocksY);
			}
			
			visited.add(tempPos);
		}
	}
	
	public static void findRealHQ() {
		for (int x=0; x<visited.size(); x++) {
			for (int y=0; y<visited.size(); y++) {
				if (x != y) {
					if (visited.get(x).xPos == visited.get(y).xPos && visited.get(x).yPos == visited.get(y).yPos) {
						System.out.println("Real HQ is " + (Math.abs(visited.get(x).xPos) + Math.abs(visited.get(x).yPos)) + " blocks away!");
						return;
					}
				}
			}
		}
	}

}
