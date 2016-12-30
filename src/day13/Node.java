package day13;

import java.awt.Point;
import java.util.ArrayList;

public class Node {

	public Point pos;
	public int distance;
	public boolean visited;
	
	public Node(Point pos, int distance) {
		this.pos = pos;
		this.distance = distance;
		this.visited = false;
	}

}
