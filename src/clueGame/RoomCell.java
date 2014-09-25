package clueGame;

public class RoomCell extends BoardCell {
	public enum DoorDirection {LEFT, RIGHT, UP, DOWN, NONE};
	DoorDirection doorDirection;
	char roomInitial;
	
	@Override
	boolean isRoom() {
		return true;
	}
}
