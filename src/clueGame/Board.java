package clueGame;

import java.util.Map;

public class Board {
	BoardCell[][] board;
	Map<Character, String> rooms;
	int numRows;
	int numCols;
	
	public Board() {
		// For failing tests
		board = new BoardCell[23][22];
		for (int c = 0; c < 23; c++) {
			for (int r = 0; r < 22; r++) {
				//TODO CHANGE IN REAL STUFF
				board[c][r] = new RoomCell(c, r);
			}
		}
	}
	
	public void loadBoardConfig() {
		
	}
	
	public BoardCell getCell(int col, int row) {
		return board[col][row];
	}
	
	public RoomCell getRoomCell(int col, int row) {
		return (RoomCell) board[col][row];
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}
	
	
}
