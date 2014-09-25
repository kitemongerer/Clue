package clueGame;

import java.util.Map;

public class Board {
	BoardCell[][] board;
	Map<Character, String> rooms;
	int numRows;
	int numCols;
	
	public void loadBoardConfig() {
		
	}
	
	public BoardCell getCell(int col, int row) {
		return board[col][row];
	}
}
