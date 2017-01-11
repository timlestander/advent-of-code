package day15;

public class Disc {

	int size;
	int position;
	
	public Disc(int size, int position) {
		this.size = size;
		this.position = position;
	}
	
	public int newPosition(int move) {
		return (position + move) % size;
	}
}
