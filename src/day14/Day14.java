package day14;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {
	
	ArrayList<Integer> keyIndexes = new ArrayList<Integer>();
	HashMap<String, String> hashLookup = new HashMap<String, String>();
	String salt = "yjdafjpo";
	// Set repeat = 2017 for part 2
	int repeat = 1;
	
	public void findPassword() throws NoSuchAlgorithmException {
	
		int ticker = 0;
		int count = 0;
		while (count != 64) {
			String md5Hash = repeatedHash(salt + "" + ticker, repeat);
			String threeSequence = threeInARow(md5Hash);
		
			if (threeSequence.length() > 0) {
				String fiveSequence = threeSequence + "" + threeSequence.substring(0, 2);
				
				for (int x=1; x<=1000; x++) {
					String secondHash = repeatedHash(salt + "" + (x + ticker), repeat);
					if (secondHash.contains(fiveSequence)) {
						count++;
						keyIndexes.add(ticker);
						break;
					}
				}
			}
			
			ticker++;
		}
		
		System.out.println("NUMBER 64 is: " + keyIndexes.get(63));
		
	}
	
	public String threeInARow(String hash) {
		
		Pattern p = Pattern.compile("([a-z\\d])\\1\\1");
		Matcher m = p.matcher(hash);
		
		if (m.find()) {
			return m.group();
		}
		
		return "";

	}
	
	public String repeatedHash(String input, int amount) throws NoSuchAlgorithmException {
		
		if (hashLookup.containsKey(input)) {
			return hashLookup.get(input);
		}
		
		MessageDigest digest = MessageDigest.getInstance("MD5");
		String hash = input;
		for (int x=0; x<amount; x++) {
	         byte[] idBytes = hash.getBytes();
	         byte[] encodedBytes = digest.digest(idBytes);
	         hash =	byteArrayToHex(encodedBytes);
		}
		
		hashLookup.put(input, hash);
		return hash;
	}
	
	public static String byteArrayToHex(byte[] a) {
		String hexString = "";
		for (byte b : a) {
			String hex = Integer.toHexString(0xFF & b);
			if (hex.length() == 1) {
				hexString += "0";
			}
			hexString += hex;
		}
		return hexString;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		new Day14().findPassword();
	}
}
