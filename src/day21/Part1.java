package day21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Part1 {

	char[] password;
	static ArrayList<String> permutations = new ArrayList<String>();

	String swapPositions = "swap position (\\d+) with position (\\d+)";
	String swapLetters = "swap letter (.) with letter (.)";
	String reversePositions = "reverse positions (\\d+) through (\\d+)";
	String rotateLeft = "rotate left (\\d+) steps?";
	String rotateRight = "rotate right (\\d+) steps?";
	String rotateBasedOn = "rotate based on position of letter (.)";
	String movePosition = "move position (\\d+) to position (\\d+)";

	public String findPassword(String pw) throws IOException {
		FileReader fr = new FileReader("src/day21/input.txt");
		BufferedReader br = new BufferedReader(fr);
		password = pw.toCharArray();

		String line = "";
		while ((line = br.readLine()) != null) {
			String[] instructions = line.split(" ");

			if (line.matches(swapPositions)) {
				swapPositions(Integer.parseInt(instructions[2]), Integer.parseInt(instructions[5]));
			} else if (line.matches(swapLetters)) {
				swapLetters(instructions[2].charAt(0), instructions[5].charAt(0));
			} else if (line.matches(reversePositions)) {
				reversePositions(Integer.parseInt(instructions[2]), Integer.parseInt(instructions[4]));
			} else if (line.matches(rotateLeft)) {
				rotateLeft(Integer.parseInt(instructions[2]));
			} else if (line.matches(rotateRight)) {
				rotateRight(Integer.parseInt(instructions[2]));
			} else if (line.matches(rotateBasedOn)) {
				rotateBasedOn(instructions[6].charAt(0));
			} else if (line.matches(movePosition)) {
				movePosition(Integer.parseInt(instructions[2]), Integer.parseInt(instructions[5]));
			}

		}
		
		return String.copyValueOf(password);

	}

	public void swapPositions(int x, int y) {
		char xLetter = password[x];
		char yLetter = password[y];
		password[x] = yLetter;
		password[y] = xLetter;
	}

	public void swapLetters(char x, char y) {
		int xIndex = 0;
		int yIndex = 0;
		for (int i=0; i<password.length; i++) {
			if (password[i] == x) {
				xIndex = i;
			} else if (password[i] == y) {
				yIndex = i;
			}
		}
		password[xIndex] = y;
		password[yIndex] = x;
	}

	public void reversePositions(int x, int y) {
		char[] tempPass = password.clone();

		while (x < y) {
			password[y] = tempPass[x];
			password[x] = tempPass[y];
			x++;
			y--;
		}

	}

	public void rotateLeft(int x) {
		char[] tempPass = password.clone();
		for (int i=0; i<password.length; i++) {
			int pos = (i+x) % password.length;
			password[i] = tempPass[pos];
		}
	}

	public void rotateRight(int x) {
		char[] tempPass = password.clone();
		for (int i=0; i<password.length; i++) {
			int pos = (i+x) % password.length;
			tempPass[pos] = password[i];
		}
		password = tempPass;
	}

	public void rotateBasedOn(char x) {
		int index = 0;
		for (int i=0; i<password.length; i++) {
			if (password[i] == x) {
				index = i;
				break;
			}
		}
		index += (index >= 4) ? 2 : 1;
		rotateRight(index);

	}

	public void printPassword() {
		for (int x=0; x<password.length; x++) {
			System.out.print(password[x]);
		}
		System.out.println();
	}

	public void movePosition(int x, int y) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<password.length; i++) {
			sb.append(password[i]);
		}

		if (x > y) {
			sb.insert(y, password[x]);
			sb.deleteCharAt(x+1);
		} else {
			sb.insert(y+1, password[x]);
			sb.deleteCharAt(x);
		}
		password = sb.toString().toCharArray();
	}

	public static void main(String[] args) throws IOException {
		Part1 p1 = new Part1();
		String solution = p1.findPassword("abcdefgh");

		System.out.println("The scrambled password is: " + solution);
	}

}
