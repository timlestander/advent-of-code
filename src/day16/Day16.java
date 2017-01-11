package day16;

import java.util.HashMap;

public class Day16 {
	
	public void createChecksum() {
		
		String input = "10111011111001111";
		int discSize = 35651584;
		
		String a = null;
		String b = null;
		
		while (input.length() < discSize) {
			a = input;
			b = onesToZeros(reverseString(a));
			input = a + "0" + b;
		}
		
		System.out.println("Checksum: " + generateChecksum(input.substring(0, discSize)));
	}
	
	public String reverseString(String input) {
		StringBuilder sb = new StringBuilder();
		for (int x=input.length()-1; x>=0; x--) {
			sb.append(input.charAt(x));
		}
		return sb.toString();
	}
	
	public String onesToZeros(String input) {
		StringBuilder sb = new StringBuilder();
		for (int x=0; x<input.length(); x++) {
			if (input.charAt(x) == '1') {
				sb.append("0");
			} else {
				sb.append("1");
			}
		}
		return sb.toString();	
	}
	
	public String generateChecksum(String input) {
		StringBuilder sb = new StringBuilder();
		for (int x=0; x<input.length(); x+=2) {
			if (input.charAt(x) == input.charAt(x+1)) {
				sb.append("1");
			} else {
				sb.append("0");
			}
		}
		
		String checksum = sb.toString();
		if (checksum.length() % 2 == 0) {
			checksum = generateChecksum(checksum);
		}
		
		return checksum;
	}

	public static void main(String[] args) {
		new Day16().createChecksum();
	}

}
