package clueGame;

abstract public class BoardCell {
	int col;
	int row;
	
	boolean isWalkway() {
		return false;
	}
	
	boolean isRoom() {
		return false;
	}
	
	boolean isDoorway() {
		return false;
	}
}
