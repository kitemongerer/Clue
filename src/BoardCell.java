
public class BoardCell {
	private int row;
	private int col;
	
	BoardCell (int col, int row) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
	public int getIndex() {
		return IntBoard.NUM_ROWS * row + col;
	}
	
	 public boolean equals(BoardCell cell) {
		 if (cell.getCol() == this.getCol() && cell.getRow() == this.getRow()) {
			 return true;
		 }
		 return false;
		 
	 }
}
