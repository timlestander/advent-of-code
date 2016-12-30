package day11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class State {

	ArrayList<Set<Item>> setup = new ArrayList<Set<Item>>();
	int elevator;
	
	public State(ArrayList<Set<Item>> setup, int elevator) {
		this.setup = setup;
		this.elevator = elevator;
	}
	
	public void removeItemsOnFloor(Set<Item> items, int floor) {
		setup.get(floor).removeAll(items);
	}
	
	public void addItemsOnFloor(Set<Item> items, int floor) {
		setup.get(floor).addAll(items);
	}
	
	public ArrayList<Item> getPairs() {
		ArrayList<Item> pairs = new ArrayList<Item>();
		
		for (Item i : setup.get(elevator)) {
			for (Item j : setup.get(elevator)) {
				if (i != j && i.element == j.element) {
					pairs.add(i);
					pairs.add(j);
				}
			}
		}
		
		return pairs;
	}
	
	public ArrayList<Item> getSinglesWithCounterPart() {
		ArrayList<Item> singlesCP = new ArrayList<Item>();
		for (Item i1 : setup.get(elevator)) {
			for (Item i2 : setup.get(elevator-1)) {
				if (i1 != i2 && i1.element == i2.element) {
					singlesCP.add(i1);
				}
			}
		}
		return singlesCP;
	}
	
	public ArrayList<Item> getSingles() {
		ArrayList<Item> singles = new ArrayList<Item>();
		for (Item i1 : setup.get(elevator)) {
			singles.add(i1);
		}
		return singles;
	}
	
	public ArrayList<Item> getTuple() {
		ArrayList<Item> tuples = new ArrayList<Item>();
		for (Item i : setup.get(elevator)) {
			tuples.add(i);
		}
		return tuples;
	}
	
	public ArrayList<Set<Item>> getCopyOfSetup() {
		ArrayList<Set<Item>> copy = new ArrayList<Set<Item>>();
		for (int x=0; x<setup.size(); x++) {
			Set<Item> floor = new HashSet<Item>();
			for (Item i : setup.get(x)) {
				floor.add(i);
			}
			copy.add(floor);
		}
		return copy;
	}
	
	public boolean canGoUp() {
		return elevator < 3 && setup.get(3).size() < 4 && setup.get(elevator).size() > 1;
	}
	
	public boolean canGoDown() {
		return elevator > 1 && setup.get(0).size() > 0;
	}
	
}
