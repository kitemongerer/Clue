package clueGame;

public class WalkwayCell extends BoardCell {
		
	public WalkwayCell(int row, int col) {
		super(row, col);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isWalkway() {
		return true;
	}
}
