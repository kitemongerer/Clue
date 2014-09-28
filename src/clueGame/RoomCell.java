package clueGame;

public class RoomCell extends BoardCell {

	public enum DoorDirection {LEFT, RIGHT, UP, DOWN, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;
	
	public RoomCell(int row, int col, char roomInitial) {
		super(row, col);
		this.roomInitial = roomInitial;
	}

	
	@Override
	public boolean isRoom() {
		return true;
	}
	
	@Override
	public boolean isDoorway() {
		if(doorDirection == DoorDirection.NONE) {
			return false;
		}
		return true;
	}
	
	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getInitial() {
		return roomInitial;
	}
}
