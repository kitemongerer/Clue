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


public class ClueTests {
	private static ClueGame game;
	private static Board board;

	@Before
	public void setUpBeforeClass() {
		game = new ClueGame("Clue_Layout.csv", "Legend.csv");
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
		Map<Character, String> rooms = game.getRooms();
		Assert.assertTrue(rooms.get('C') == "Coat Closet");
		Assert.assertTrue(rooms.get('E') == "Exercise Room");
		Assert.assertTrue(rooms.get('G') == "Game Room");
		Assert.assertTrue(rooms.get('M') == "Movie Room");
		Assert.assertTrue(rooms.get('D') == "Dining Room");
		Assert.assertTrue(rooms.get('B') == "Bedroom");
		Assert.assertTrue(rooms.get('K') == "Kitchen");
		Assert.assertTrue(rooms.get('P') == "Pool");
		Assert.assertTrue(rooms.get('L') == "Laundry Room");
		Assert.assertTrue(rooms.get('X') == "Closet");
		Assert.assertTrue(rooms.get('W') == "Walkway");
	}

	@Test
	public void testBoardSize() {
		Assert.assertEquals( board.getNumRows(), 22 );
		Assert.assertEquals( board.getNumCols(), 23 );
	}

	@Test
	public void testDoorDirection() {
		RoomCell room = board.getRoomCell(5, 1);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(room.getDoorDirection(), RoomCell.DoorDirection.DOWN);
		room = board.getRoomCell(1, 14);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(room.getDoorDirection(), RoomCell.DoorDirection.UP);
		room = board.getRoomCell(4, 10);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(room.getDoorDirection(), RoomCell.DoorDirection.RIGHT);
		room = board.getRoomCell(1, 6);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(room.getDoorDirection(), RoomCell.DoorDirection.LEFT);
		room = board.getRoomCell(21, 19);
		Assert.assertFalse(room.isDoorway());
		room = board.getRoomCell(0, 0);
		Assert.assertFalse(room.isDoorway());
	}

	@Test
	public void testCountDoors() {
		int doors = 0;
		for(int c = 0; c < board.getNumCols(); c++) {
			for(int r = 0; r < board.getNumCols(); r++) {
				if (board.getCell(c, r).isDoorway() ) {
					doors++;
				}
			}
		}
		Assert.assertEquals(doors, 19);
	}

	@Test
	public void testRightInitial() {
		Assert.assertEquals('C', board.getRoomCell(0, 0).getInitial());
		Assert.assertEquals('E', board.getRoomCell(11, 0).getInitial());
		Assert.assertEquals('G', board.getRoomCell(17, 0).getInitial());
		Assert.assertEquals('D', board.getRoomCell(21, 21).getInitial());
		Assert.assertEquals('B', board.getRoomCell(9, 21).getInitial());
		Assert.assertEquals('K', board.getRoomCell(0, 21).getInitial());
	}

	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		ClueGame badGame = new ClueGame("ClueLayoutBadColumns.csv", "ClueLegend.txt");
		badGame.loadLegendConfig();
		badGame.getBoard().loadBoardConfig();
	}

	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		ClueGame badGame = new ClueGame("ClueLayoutBadRoom.csv", "ClueLegend.txt");
		badGame.loadLegendConfig();
		badGame.getBoard().loadBoardConfig();
	}

	@Test (expected = BadConfigFormatException.class)
	public void testBadRoomFormat() throws BadConfigFormatException, FileNotFoundException {
		ClueGame badGame = new ClueGame("ClueLayout.csv", "ClueLegendBadFormat.txt");
		badGame.loadLegendConfig();
		badGame.getBoard().loadBoardConfig();
	}

}
