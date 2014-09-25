package clueGame;

import java.util.Map;

public class ClueGame {
	Map<Character, String> rooms;
	
	public void loadConfigFiles() {
		Board board = new Board();
		board.loadBoardConfig();
		loadLegendConfig();
	}
	
	public void loadLegendConfig() {
		
	}
}
