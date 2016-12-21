package day19;

import java.util.Iterator;
import java.util.LinkedList;

public class Part1 {
	
	int input = 3014387;
	LinkedList<Integer> elves = new LinkedList<Integer>();
	
	public void findTheLuckyElf() {
		
		for (int x=1; x<=input; x++) {
			elves.addLast(x);
		}
		
		boolean robbed = false;
		while (elves.size() > 1) {
			Iterator<Integer> it = elves.iterator();
			while (it.hasNext()) {
				it.next();
				if (robbed) {
					it.remove();
				} 
				robbed = !robbed;
			}
		}
		System.out.println("Elf number " + elves.get(0) + " gets all the presents");
	}

	public static void main(String[] args) {
		new Part1().findTheLuckyElf();
	}

}
