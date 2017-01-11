package day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.Messaging.SyncScopeHelper;

public class Day22 {

	ArrayList<Node> nodes = new ArrayList<Node>();

	public void solvePuzzle() throws IOException {
		FileReader fr = new FileReader("src/day22/input.txt");
		BufferedReader br = new BufferedReader(fr);

		String line = "";
		while ((line = br.readLine()) != null) {
			String[] info = line.split("(\\s)+");
			Pattern p = Pattern.compile("x(\\d+)-y(\\d+)");
			Matcher m = p.matcher(info[0]);

			int xPos = 0;
			int yPos = 0;

			while (m.find()) {
				xPos = Integer.parseInt(m.group(1));
				yPos = Integer.parseInt(m.group(2));
			}

			int size = Integer.parseInt(info[1].substring(0, info[1].length()-1));
			int used = Integer.parseInt(info[2].substring(0, info[2].length()-1));
			int avail = Integer.parseInt(info[3].substring(0, info[3].length()-1));

			nodes.add(new Node(xPos, yPos, size, used, avail));

		}	

		System.out.println("Viable pairs: " + countViablePairs());
		drawProblem();
	}

	public int countViablePairs() {
		int viablePairs = 0;
		for (Node n1 : nodes) {
			for (Node n2 : nodes) {
				if (n1 != n2 && n1.used != 0 && n1.used <= n2.avail) {
					viablePairs++;
				}
			}
		}
		return viablePairs;
	}

	public void drawProblem() {

		int rowLength = (int) Math.ceil(Math.sqrt(nodes.size())+1);
		System.out.println(rowLength);
		Node empty = null;
		Node wallStart = null; 

		for (int y=0; y<rowLength; y++) {
			for (int x=0; x<rowLength; x++) {
				for (Node n : nodes) {
					if (n.x == x && n.y == y) {
						if (n.used == 0) {
							System.out.print("_");
							empty = n;
						} else if (n.x == 31 && n.y == 0) {
							System.out.print("G");
						} else if (n.size > 250) {
							System.out.print("#");
							if (wallStart == null) {
								wallStart = n;
							}
						} else if (n.x == 0 && n.y == 0) {
							System.out.print("x");
						} else {
							System.out.print(".");
						}
						
					}
				}
			}
			System.out.println();
		}
		
		int result = empty.y + 2*(empty.x - wallStart.x+1) + (rowLength - empty.x - 1);
	    System.out.println(result + 5 * (rowLength - 2));

	}

	public static void main (String[] args) throws IOException {
		new Day22().solvePuzzle();
	}

}
