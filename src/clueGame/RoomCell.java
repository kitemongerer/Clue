package clueGame;

public class RoomCell extends BoardCell {

	public enum DoorDirection {LEFT, RIGHT, UP, DOWN, NONE};
	DoorDirection doorDirection;
	char roomInitial;
	
	public RoomCell(int c, int r) {
		super(c, r);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean isRoom() {
		return true;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getInitial() {
		return roomInitial;
	}
}
