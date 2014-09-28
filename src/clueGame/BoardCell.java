package clueGame;


abstract public class BoardCell {
	int col;
	int row;

	public BoardCell(int row, int col) {
		this.col = col;
		this.row = row;
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

	public int getIndex(int numCols) {
		return numCols * row + col;
	}

	public boolean equals(BoardCell cell) {
		if (cell.getCol() == this.getCol() && cell.getRow() == this.getRow()) {
			return true;
		}
		return false;
	}

	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
}
