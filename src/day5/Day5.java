package day5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day5 {

	public String getPasswordPart1(String doorId) throws NoSuchAlgorithmException {
		String password = "";
		long currentIdx = 0;
		
		while (password.length() < 8) {
			String hexhash = getHexMd5Hash(doorId + currentIdx);
			
			if (hexhash.startsWith("00000")) {
				password += hexhash.charAt(5);
			}
			
			currentIdx++;
		}
		
		return password;
	}
	
	public String getPasswordPart2(String doorId) throws NoSuchAlgorithmException {
		StringBuilder password = new StringBuilder("--------");
		long currentIdx = 0;
		int positionIdx = 0;
	
		while (password.toString().contains("-")) {
			String hexhash = getHexMd5Hash(doorId + currentIdx);
			
			if (hexhash.startsWith("00000")) {
				if (Character.isDigit(hexhash.charAt(5))) {
					int position = Character.getNumericValue(hexhash.charAt(5));
					char letter =  hexhash.charAt(6);
					if (position >= 0 && position < 8 && password.charAt(position) == '-') {
						password.setCharAt(position, letter);
					}

				}

			}
			
			currentIdx++;
		}
		
		return password.toString();
	}
	
	public String getHexMd5Hash(String pass) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hash = md.digest(pass.getBytes());
		String hexString = "";
		
		for (byte h : hash) {
			String hex = Integer.toHexString(0xFF & h);
			if (hex.length() == 1) {
				hexString += "0";
			}
			hexString += hex;
		}
		
		return hexString;

	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		Day5 solution = new Day5();
		System.out.println("Password part 1 is: " + solution.getPasswordPart1("cxdnnyjw"));
		System.out.println("Password part 2 is: " + solution.getPasswordPart2("cxdnnyjw"));
		
	}

}
