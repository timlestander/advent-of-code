package Day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.omg.Messaging.SyncScopeHelper;

public class Day20 {
	
	ArrayList<Range> input = new ArrayList<Range>();
	
	public void  findLowestAllowedIP() throws IOException {
		FileReader fr = new FileReader("src/day20/input.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] values = line.split("-");
			input.add(new Range(Long.parseLong(values[0]), Long.parseLong(values[1])));
		}
		
		// Sort all ranges
        Collections.sort(input, new Comparator<Range>() {
			@Override
			public int compare(Range r1, Range r2) {
				return r1.start < r2.start ? -1  : r1.start == r2.start ? 0 : 1;
			}
        });
        
        // Merge overlapping ranges
        int index = 0;
        ArrayList<Range> mergedRanges = new ArrayList<Range>();
        while (index < input.size()) {
        	Range newRange = new Range(input.get(index).start, input.get(index).end);
        	while (index+1 < input.size() && input.get(index+1).start <= newRange.end + 1) {
        		index++;
        		if (input.get(index).end > newRange.end) {
        			newRange.end = input.get(index).end;
        		}
        	}
        	mergedRanges.add(newRange);
        	index++;
        }
      System.out.println("Lowest allowed IP is: " + (mergedRanges.get(0).end+1));
      System.out.println("Amount of valid IP adresses: " + countValidIPs(mergedRanges));
  	   
	}
	
	// Count amount of valid IPs for part 2
	public int countValidIPs(ArrayList<Range> mergedRanges) {
		int rangeIdx = 0;
		int ipCount = 0;
		long max = 4294967296L;
		for (long y=0; y<max; y++) {
			if (y >= mergedRanges.get(rangeIdx).start && y <= mergedRanges.get(rangeIdx).end) {
				y = mergedRanges.get(rangeIdx).end;
				rangeIdx++;
				continue;
			} else {
				ipCount++;
			}
		}
		return ipCount;
	}

	public static void main(String[] args) throws IOException {
		new Day20().findLowestAllowedIP();
	}

}
