package clueTests;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGame;

public class TargetAdjTests {
	private static ClueGame game;
	private static Board board;

	@Before
	public void setUpBeforeClass() {
		game = new ClueGame("Clue_Layout.csv", "Legend.csv");
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
	}

	//Orange - make sure you can't move around inside rooms
	@Test
	public void testAdjacenciesInsideRooms()
	{
		LinkedList<BoardCell> testList = board.getAdjList(0, 0);
		Assert.assertEquals(0, testList.size());

		testList = board.getAdjList(0, 8);
		Assert.assertEquals(0, testList.size());

		testList = board.getAdjList(0, 13);
		Assert.assertEquals(0, testList.size());

		testList = board.getAdjList(0, 18);
		Assert.assertEquals(0, testList.size());

		testList = board.getAdjList(14, 18);
		Assert.assertEquals(0, testList.size());

		testList = board.getAdjList(20, 18);
		Assert.assertEquals(0, testList.size());
	}

	//PURPLE - make sure each doorway has only one adjacent spot
	//And that spot must be the walkway in the corresponding direction
	@Test
	public void testAdjacencyRoomExit()
	{
		LinkedList<BoardCell> testList = board.getAdjList(15, 5);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(15, 6)));

		testList = board.getAdjList(12, 16);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(12, 15)));

		testList = board.getAdjList(1, 5);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(2, 5)));

		testList = board.getAdjList(14, 1);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(13, 1)));
		
	}
	
	//Green - room entrance adjacency
	@Test
	public void testAdjacencyDoorways()
	{
		LinkedList<BoardCell> testList = board.getAdjList(15, 6);
		Assert.assertTrue(testList.contains(board.getCellAt(14, 6)));
		Assert.assertTrue(testList.contains(board.getCellAt(16, 6)));
		Assert.assertTrue(testList.contains(board.getCellAt(15, 5)));
		Assert.assertTrue(testList.contains(board.getCellAt(15, 7)));
		Assert.assertEquals(4, testList.size());

		testList = board.getAdjList(2, 5);
		Assert.assertTrue(testList.contains(board.getCellAt(1, 5)));
		Assert.assertTrue(testList.contains(board.getCellAt(3, 5)));
		Assert.assertTrue(testList.contains(board.getCellAt(2, 6)));
		Assert.assertTrue(testList.contains(board.getCellAt(2, 4)));
		Assert.assertEquals(4, testList.size());

		testList = board.getAdjList(11, 15);
		Assert.assertTrue(testList.contains(board.getCellAt(10, 15)));
		Assert.assertTrue(testList.contains(board.getCellAt(12, 15)));
		Assert.assertTrue(testList.contains(board.getCellAt(11, 16)));
		Assert.assertEquals(3, testList.size());

		testList = board.getAdjList(13, 1);
		Assert.assertTrue(testList.contains(board.getCellAt(13, 0)));
		Assert.assertTrue(testList.contains(board.getCellAt(13, 2)));
		Assert.assertTrue(testList.contains(board.getCellAt(14, 1)));
		Assert.assertTrue(testList.contains(board.getCellAt(12, 1)));
		Assert.assertEquals(4, testList.size());
	}

	// LIGHT PURPLE - walkway scenarios
	@Test
	public void testAdjacencyWalkways()
	{
		LinkedList<BoardCell> testList = board.getAdjList(0, 6);
		Assert.assertTrue(testList.contains(board.getCellAt(1, 6)));
		Assert.assertEquals(1, testList.size());
		
		testList = board.getAdjList(3, 0);
		Assert.assertTrue(testList.contains(board.getCellAt(2, 0)));
		Assert.assertTrue(testList.contains(board.getCellAt(4, 0)));
		Assert.assertTrue(testList.contains(board.getCellAt(3, 1)));
		Assert.assertEquals(3, testList.size());

		testList = board.getAdjList(2, 10);
		Assert.assertTrue(testList.contains(board.getCellAt(1, 10)));
		Assert.assertTrue(testList.contains(board.getCellAt(3, 10)));
		Assert.assertEquals(2, testList.size());

		testList = board.getAdjList(13,12);
		Assert.assertTrue(testList.contains(board.getCellAt(14, 12)));
		Assert.assertTrue(testList.contains(board.getCellAt(12, 12)));
		Assert.assertTrue(testList.contains(board.getCellAt(13, 11)));
		Assert.assertTrue(testList.contains(board.getCellAt(13, 13)));
		Assert.assertEquals(4, testList.size());
		
		testList = board.getAdjList(5, 22);
		Assert.assertTrue(testList.contains(board.getCellAt(6, 22)));
		Assert.assertTrue(testList.contains(board.getCellAt(5, 21)));
		Assert.assertEquals(2, testList.size());

		testList = board.getAdjList(5, 21);
		Assert.assertTrue(testList.contains(board.getCellAt(5, 22)));
		Assert.assertTrue(testList.contains(board.getCellAt(5, 20)));
		Assert.assertTrue(testList.contains(board.getCellAt(6, 21)));
		Assert.assertTrue(testList.contains(board.getCellAt(4, 21)));
		Assert.assertEquals(4, testList.size());
	}
	
	
	//LIGHT YELLOW - test 1 step on walkways
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(1, 6, 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(0, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(2, 6)));	
		
		board.calcTargets(3, 0, 1);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(2, 0)));
		Assert.assertTrue(targets.contains(board.getCellAt(4, 0)));	
		Assert.assertTrue(targets.contains(board.getCellAt(3, 1)));			
	}
	
	//LIGHT YELLOW - test 2 steps on walkways
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(21, 6, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(19, 6)));
				
		board.calcTargets(5, 22, 2);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(5, 20)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 21)));	
		Assert.assertTrue(targets.contains(board.getCellAt(4, 21)));
	}
	
	//LIGHT YELLOW - test 4 steps on walkways
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(0, 15, 4);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(2, 17)));
		Assert.assertTrue(targets.contains(board.getCellAt(2, 14)));
		
		board.calcTargets(6, 0, 4);
		targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(2, 0)));
		Assert.assertTrue(targets.contains(board.getCellAt(3, 1)));	
		Assert.assertTrue(targets.contains(board.getCellAt(10, 0)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 1)));
		Assert.assertTrue(targets.contains(board.getCellAt(7, 1)));	
		Assert.assertTrue(targets.contains(board.getCellAt(8, 1)));
	}	
	
	//LIGHT BLUE - walkway plus door six steps
	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(20, 6, 6);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(14, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(15, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(15, 5)));
	}	

	//LIGHT BLUE - entering room
	@Test 
	public void testTargetsIntoRoom()
	{
		board.calcTargets(10, 6, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(5, targets.size());

		Assert.assertTrue(targets.contains(board.getCellAt(10, 4)));

		Assert.assertTrue(targets.contains(board.getCellAt(8, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(9, 5)));

		Assert.assertTrue(targets.contains(board.getCellAt(11, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(12, 6)));
	}
	
	//LIGHT BLUE - entering rooms without all steps
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(17, 21, 3);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(6, targets.size());

		Assert.assertTrue(targets.contains(board.getCellAt(15, 21)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 22)));
		Assert.assertTrue(targets.contains(board.getCellAt(16, 19)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 18)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 20)));
		Assert.assertTrue(targets.contains(board.getCellAt(16, 21)));
				
	}

	//LIGHT BLUE exiting a room
	@Test
	public void testRoomExit()
	{
		board.calcTargets(10, 4, 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(10, 5)));

		board.calcTargets(10, 4, 2);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(9, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(11, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(10, 6)));
	}

}