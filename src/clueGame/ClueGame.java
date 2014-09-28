package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClueGame {
	private Map<Character, String> rooms;
	private Board board;
	private String legend;
	private String layout;
	
	public ClueGame() {
		rooms = new HashMap<Character, String>();

		this.legend = "ClueLegend.txt";
		this.layout = "ClueLayout.csv";
	}
	
	public ClueGame(String layout, String legend) {
		rooms = new HashMap<Character, String>();

		this.legend = legend;
		this.layout = layout;
	}
	
	public void loadConfigFiles() {
		loadLegendConfig();
		board = new Board(rooms);
		board.loadBoardConfig(layout);
	}
	
	//Read in the legend file
	public void loadLegendConfig() throws BadConfigFormatException {
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader(legend);
			in = new Scanner(reader);
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		try {
			while (in.hasNext()) {
				String[] split = in.nextLine().split(",");
				
				//Check if line has exactly two entries
				if (split.length != 2) {
					throw new BadConfigFormatException("The legend file has entries of incorrect size.");
				}
				rooms.put(split[0].charAt(0), split[1]);
			}
		} catch (BadConfigFormatException e) {
			System.out.println("The legend file is not formatted correctly");
			throw e;
		}
	}
	
	
	public Map<Character, String> getRooms() {
		return rooms;
	}
	
	public Board getBoard() {
		return board;
	}
}
