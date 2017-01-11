package day24;

import java.awt.Point;

public class Node {

	public Point pos;
	public int nodeNum;
	public int[] distances;
	
	public Node(Point pos, int nodeNum) {
		this.pos = pos;
		this.nodeNum = nodeNum;
	}

}