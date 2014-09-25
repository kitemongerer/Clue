package clueGame;

import java.util.HashMap;
import java.util.Map;

public class ClueGame {
	Map<Character, String> rooms;
	Board board;
	
	public ClueGame(String layout, String legend) {
		rooms = new HashMap<Character, String>();
		board = new Board();
	}
	
	public void loadConfigFiles() {
		board.loadBoardConfig();
		loadLegendConfig();
	}
	
	public void loadLegendConfig() {
		
	}
	
	public Map<Character, String> getRooms() {
		return rooms;
	}
	
	public Board getBoard() {
		return board;
	}
}
