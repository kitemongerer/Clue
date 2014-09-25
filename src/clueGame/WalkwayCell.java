package clueGame;

public class WalkwayCell extends BoardCell {
		
	public WalkwayCell(int c, int r) {
		super(c, r);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isWalkway() {
		return true;
	}
}
