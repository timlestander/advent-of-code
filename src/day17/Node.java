package day17;

import java.awt.Point;

public class Node {

	public Point pos;
	public boolean visited;
	public String input;
	
	public Node(Point pos, String input) {
		this.pos = pos;
		this.visited = false;
		this.input = input;
	}
	
}
