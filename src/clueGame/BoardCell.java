package clueGame;

abstract public class BoardCell {
	int col;
	int row;
	
	public BoardCell(int c, int r) {
		
	}
	public boolean isWalkway() {
		return false;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isDoorway() {
		return false;
	}
}
