package day17;

import java.awt.Point;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Part1 {
	
	public char[] dirs = {'U', 'D', 'L', 'R'};
	public int[] dx = {0, 0, -1, 1};
	public int[] dy = {-1, 1, 0, 0};
	
	String input = "njfxhljp";
	
	public void findShortestPath(Point start, Point end) throws NoSuchAlgorithmException {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(start, input));
		
		while (!q.isEmpty()) {
			Node currNode = q.remove();
			String doors = md5Hash(currNode.input).substring(0,4);
			
			if (currNode.pos.x == 3 && currNode.pos.y == 3) {
				System.out.println("Shortest route is: " + currNode.input.substring(input.length()));
				break;
			}

			for (int x=0; x<doors.length(); x++) {
				Node n = new Node(new Point(currNode.pos.x+dx[x], currNode.pos.y+dy[x]), currNode.input+dirs[x]);
				if (doors.charAt(x) >= 'b' && doors.charAt(x) <= 'f' && isValid(n)) {
					q.add(n);
				}
				
			}
		}
	}
	
	public boolean isValid(Node n) {
		
		if (n.pos.x >= 0 && n.pos.x <= 3 && n.pos.y >= 0 && n.pos.y <= 3) {
			return true;
		}
		
		return false;
	}
	
	public String md5Hash(String input) throws NoSuchAlgorithmException {
		
		String hash = "";
		MessageDigest digest = MessageDigest.getInstance("MD5");
	    byte[] idBytes = input.getBytes();
	    byte[] encodedBytes = digest.digest(idBytes);
	    hash = byteArrayToHex(encodedBytes);

		return hash;
	}
	
	public static String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for (byte b : a) {
			String hex = Integer.toHexString(0xFF & b);
			if (hex.length() == 1) {
				sb.append("0");
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		new Part1().findShortestPath(new Point(0,0), new Point(3,3));
	}
	
}
