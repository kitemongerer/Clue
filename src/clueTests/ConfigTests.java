package clueTests;
import clueGame.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ConfigTests {
	private static ClueGame game;
	private static Board board;

	@Before
	public void setUpBeforeClass() {
		game = new ClueGame("Clue_Layout.csv", "Legend.csv");
		game.loadConfigFiles();
		board = game.getBoard();
	}

	@Test
	public void testNumRooms() {
		//Includes closet and walkway as rooms
		Assert.assertEquals( game.getRooms().size(), 11 );
	}

	@Test
	public void testLegend() {
		//Includes closet as a room
		game.loadLegendConfig();
		Map<Character, String> rooms = game.getRooms();
		Assert.assertTrue(rooms.get('C').equals("Coat Closet"));
		Assert.assertTrue(rooms.get('E').equals("Exercise Room"));
		Assert.assertTrue(rooms.get('G').equals("Game Room"));
		Assert.assertTrue(rooms.get('M').equals("Movie Room"));
		Assert.assertTrue(rooms.get('D').equals("Dining Room"));
		Assert.assertTrue(rooms.get('B').equals("Bedroom"));
		Assert.assertTrue(rooms.get('K').equals("Kitchen"));
		Assert.assertTrue(rooms.get('P').equals("Pool"));
		Assert.assertTrue(rooms.get('L').equals("Laundry Room"));
		Assert.assertTrue(rooms.get('X').equals("Closet"));
		Assert.assertTrue(rooms.get('W').equals("Walkway"));
	}

	@Test
	public void testBoardSize() {
		Assert.assertEquals( board.getNumRows(), 22 );
		Assert.assertEquals( board.getNumCols(), 23 );
	}

	@Test
	public void testDoorDirection() {
		RoomCell room = board.getRoomCell(1, 5);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(room.getDoorDirection(), RoomCell.DoorDirection.DOWN);
		room = board.getRoomCell(14, 1);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(room.getDoorDirection(), RoomCell.DoorDirection.UP);
		room = board.getRoomCell(10, 4);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(room.getDoorDirection(), RoomCell.DoorDirection.RIGHT);
		room = board.getRoomCell(6, 1);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(room.getDoorDirection(), RoomCell.DoorDirection.LEFT);
		room = board.getRoomCell(19, 21);
		Assert.assertFalse(room.isDoorway());
		room = board.getRoomCell(0, 0);
		Assert.assertFalse(room.isDoorway());
	}

	@Test
	public void testCountDoors() {
		int doors = 0;
		for(int c = 0; c < board.getNumCols(); c++) {
			for(int r = 0; r < board.getNumRows(); r++) {
				if (board.getCellAt(r, c).isDoorway()) {
					doors++;
				}
			}
		}
		Assert.assertEquals(doors, 19);
	}

	@Test
	public void testRightInitial() {
		Assert.assertEquals('C', board.getRoomCell(0, 0).getInitial());
		Assert.assertEquals('E', board.getRoomCell(0, 11).getInitial());
		Assert.assertEquals('G', board.getRoomCell(0, 17).getInitial());
		Assert.assertEquals('D', board.getRoomCell(21, 21).getInitial());
		Assert.assertEquals('B', board.getRoomCell(21, 9).getInitial());
		Assert.assertEquals('K', board.getRoomCell(21, 0).getInitial());
	}

	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		ClueGame badGame = new ClueGame("ClueLayoutBadColumns.csv", "Legend.csv");
		badGame.loadConfigFiles();
	}

	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		ClueGame badGame = new ClueGame("ClueLayoutBadRoom.csv", "Legend.csv");
		badGame.loadConfigFiles();
	}

	@Test (expected = BadConfigFormatException.class)
	public void testBadRoomFormat() throws BadConfigFormatException, FileNotFoundException {
		ClueGame badGame = new ClueGame("ClueLayout.csv", "ClueLegendBadFormat.txt");
		badGame.loadConfigFiles();
	}

}
