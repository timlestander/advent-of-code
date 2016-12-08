package day7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {

	public static void main(String[] args) throws IOException {
		
		FileReader r = new FileReader("src/day7/input.txt");
		BufferedReader br = new BufferedReader(r);
		int TLSCounter = 0;
		int SSLCounter = 0;
		
		String line = "";
		while ((line = br.readLine()) != null) {
			
			ArrayList<String> outsideBrackets = new ArrayList<String>();
			ArrayList<String> insideBrackets = new ArrayList<String>();
			
			String[] strings = line.split("\\[|\\]");
			for (int x=0; x<strings.length; x++) {
				if (x % 2 == 0) {
					outsideBrackets.add(strings[x]);
				} else {
					insideBrackets.add(strings[x]);
				}
			}
			
			if (containsABBA(outsideBrackets) && !containsABBA(insideBrackets)) {
				TLSCounter++;
			}
			
			if (containsBAB(insideBrackets, outsideBrackets)) {
				SSLCounter++;
			}
			
		}
		
		System.out.println("The amount of IPs that are supporting TLS are: " + TLSCounter);
		System.out.println("The amount of IPs that are supporting SSL are: " + SSLCounter);

	}
	
	public static boolean containsABBA(ArrayList<String> data) {
		
		Pattern p = Pattern.compile("([a-z])([a-z])\\2\\1");
		for (int x=0; x<data.size(); x++) {
			String tempString = data.get(x);
			Matcher m = p.matcher(tempString);
			
			if (m.find()) {
				String firstHalf = m.group().substring(0,2);
				String secondHalf = m.group().substring(2,4);
				if (!firstHalf.equals(secondHalf)) {
					return true;
				}
			} 
	
		}
		return false;
	}
	
	public static boolean containsBAB(ArrayList<String> innerData, ArrayList<String> outerData) {
		
		// Find BAB:s outside brackets
		Pattern p = Pattern.compile("(?=([a-z])([a-z])\\1)");
		ArrayList<String> matchedSequences = new ArrayList<String>();
		for (int x=0; x<outerData.size(); x++) {
			String tempString = outerData.get(x);
			Matcher m = p.matcher(tempString);
			String matchedString = "";
			while (m.find()) {
				matchedString = m.group(2) + "" + m.group(1) + "" + m.group(2);
				matchedSequences.add(matchedString);
			}
		}
		
		// Look any of the found BAB:s are matching an inner string
		for (int y=0; y<matchedSequences.size(); y++) {
			for (int x=0; x<innerData.size(); x++) {
				if (innerData.get(x).contains(matchedSequences.get(y))) {
					return true;
				}
			}
		}

		return false;
	}

}
