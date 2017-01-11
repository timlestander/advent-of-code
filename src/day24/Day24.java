package day24;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

import org.omg.Messaging.SyncScopeHelper;

public class Day24 {

	ArrayList<Node> keyNodes = new ArrayList<Node>();
	HashMap<Point, Boolean> map = new HashMap<Point, Boolean>();
	Point startPoint = new Point(1,20);
	static ArrayList<String> paths = new ArrayList<String>();

	public int bfsDistance(Point start, Point end) {

		ArrayList<Point> visited = new ArrayList<Point>();
		Queue<Point> q = new LinkedList<Point>();
		Stack<Point> s = new Stack<Point>();
		q.add(start);
		visited.add(start);
		s.push(start);

		while (!q.isEmpty()) {
			Point currPoint = q.remove();

			if (currPoint.x == end.x && currPoint.y == end.y) {
				end = currPoint;
				break;
			}

			ArrayList<Point> adjacent = new ArrayList<Point>();
			adjacent.add(new Point(currPoint.x+1, currPoint.y));
			adjacent.add(new Point(currPoint.x-1, currPoint.y));
			adjacent.add(new Point(currPoint.x, currPoint.y+1));
			adjacent.add(new Point(currPoint.x, currPoint.y-1));

			for (Point p : adjacent) {
				if (map.get(p) && !visited.contains(p)) {
					q.add(p);
					s.push(p);
					visited.add(p);
				}
			}
		}

		Point n1;
		Point n2 = end;
		int distance = 0;
		while (!s.isEmpty()) {
			n1 = s.pop();

			if (Math.abs(n1.x-n2.x) + (Math.abs(n1.y-n2.y)) == 1) {
				distance++;
				n2 = n1;
			}
		}

		return distance;		
	}


	public void findSolution() throws IOException {
		initialize(); 
		permutation("1234567");

		int shortestDistance = Integer.MAX_VALUE;
		for (int x=0; x<paths.size(); x++) {
			final int pathIdx = x;
			int distance = 0;
			for (int y=0; y<paths.get(x).length()-1; y++) {
				final int charIdx = y;
				Node currNode = keyNodes.stream().filter(n -> n.nodeNum == Character.getNumericValue(paths.get(pathIdx).charAt(charIdx))).findAny().orElse(null);
				int to = Character.getNumericValue(paths.get(x).charAt(charIdx+1));
				distance += currNode.distances[to];
			}
			
			if (distance < shortestDistance) {
				shortestDistance = distance;
			}
		}
		
		System.out.println("Shortest path is: " + shortestDistance);
	}

	public static void permutation(String str) { 
		permutation("", str); 
	}

	private static void permutation(String prefix, String str) {
		int n = str.length();
		
		// Add + "0" in the end of path for part 2
		if (n == 0) paths.add("0" + prefix + "0");
		else {
			for (int i = 0; i < n; i++) {
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
			}
		}
	}

	public void initialize() throws IOException {
		FileReader fr = new FileReader("src/day24/input.txt");
		BufferedReader br = new BufferedReader(fr);

		String line = "";
		int y = 0;
		while ((line = br.readLine()) != null) {
			char[] lineArr = line.toCharArray();
			for (int x=0; x<lineArr.length; x++) {
				boolean open = (lineArr[x] == '.') ? true : false;
				if (Character.isDigit(lineArr[x]) && lineArr[x] != '0') {
					keyNodes.add(new Node(new Point(x, y), (lineArr[x]-'0')));
					open = true;
				} else if (lineArr[x] == '0') {
					keyNodes.add(new Node(new Point(x, y), 0));
					open = true;
				}

				map.put(new Point(x,y), open);
			}
			y++;
		}

		// Calculate distance from one node to all other nodes
		for (Node start : keyNodes) {
			int[] distances = new int[8];
			distances[start.nodeNum] = 0;
			for (Node end : keyNodes) {
				if (start.nodeNum != end.nodeNum) {
					distances[end.nodeNum] = bfsDistance(start.pos, end.pos);
				}
			}
			start.distances = distances;
		}

	}

	public void printMap() {
		int width = (int) map.keySet().stream().filter(p -> p.y == 0).count();
		for (int y=0; y<map.size()/width; y++) {
			for (int x=0; x<width; x++) {
				Point p = new Point(x, y);
				if (map.get(p)) {
					System.out.print(".");
				} else {
					System.out.print("#");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		new Day24().findSolution();
	}

}
