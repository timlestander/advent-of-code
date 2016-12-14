package day13;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Day13 {
	
	int inputKey = 1350;
	
	public int bfs(Point startPoint, Point endPoint) {
		Queue<Node> q = new LinkedList<Node>();
		ArrayList<Point> visited = new ArrayList<Point>();
		q.add(new Node((startPoint), 0));
		visited.add(startPoint);
		
		while (!q.isEmpty()) {
			Node currNode = q.remove();
			
			if (currNode.pos.x == endPoint.x && currNode.pos.y == endPoint.y) {
				return currNode.distance;
			}
			
			/* Uncomment for part 2
			if (currNode.distance == 50) {
				return visited.size();
			} */
			
			ArrayList<Node> adjacent = new ArrayList<Node>();
			adjacent.add(new Node(new Point(currNode.pos.x+1, currNode.pos.y), currNode.distance+1));
			adjacent.add(new Node(new Point(currNode.pos.x-1, currNode.pos.y), currNode.distance+1));
			adjacent.add(new Node(new Point(currNode.pos.x, currNode.pos.y+1), currNode.distance+1));
			adjacent.add(new Node(new Point(currNode.pos.x, currNode.pos.y-1), currNode.distance+1));
			
			for (Node n : adjacent) {
				if (isOpenSpace(n.pos) && isWithinArea(n.pos) && !visited.contains(n.pos)) {
					visited.add(n.pos);
					q.add(n);
				}
			}
		}
		return 10000;
	}
	
	public boolean isOpenSpace(Point pos) {
		int value = pos.x*pos.x + 3*pos.x + 2*pos.x*pos.y + pos.y + pos.y*pos.y + inputKey;
		String binaryString = Integer.toString(value, 2);
		int bitCounter = 0;
		for (int x=0; x<binaryString.length(); x++) {
			if (binaryString.charAt(x) == '1') {
				bitCounter++;
			}
		}
		
		if (bitCounter % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isWithinArea(Point pos) {
		if (pos.x > -1 && pos.y > -1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void drawMaze() {
		for (int y=0; y<50; y++) {
			for (int x=0; x<50; x++) {
				if (isOpenSpace(new Point(x,y))) {
					System.out.print(".");
				} else {
					System.out.print("#");
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point startPoint = new Point(1, 1);
		Point endPoint = new Point(31, 39);
		Day13 day13 = new Day13();
		System.out.println("Answer is: " + day13.bfs(startPoint, endPoint));
		day13.drawMaze();
	}

}
