package day19;

import java.util.LinkedList;

public class Part2 {

	LinkedList<Integer> firstHalf = new LinkedList<Integer>();
	LinkedList<Integer> secondHalf = new LinkedList<Integer>();
	int input = 3014387;
	
	public void findTheLuckyElf() {
	
		for (int x=1; x<=input; x++) {
			if (x <= input/2) {
				firstHalf.addLast(x);
			} else {
				secondHalf.addFirst(x);
			}
		}
		
		while (firstHalf.size() + secondHalf.size() != 1) {
			int curr = firstHalf.pollFirst();
			if (firstHalf.size() == secondHalf.size()) {
				firstHalf.pollLast();
			} else {
				secondHalf.pollFirst();
			}
			
			secondHalf.addLast(curr);
			firstHalf.addLast(secondHalf.pollFirst());
		}
		
		System.out.println("The lucky elf is " + firstHalf.pollFirst());
		
	}
	
	public static void main(String[] args) {
		new Part2().findTheLuckyElf();
	}

}
