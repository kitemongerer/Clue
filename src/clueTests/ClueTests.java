package clueTests;
import clueGame.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ClueTests {
	private static IntBoard board;
	
	@Before
	public void setUpBeforeClass() {
		board = new Board();
		System.out.println("hi");
	}

	@Test
	public void testAdjacencyCorner() {
		LinkedList<BoardCell> testList = board.getAdjList(board.getCell(0,0));

		//System.out.println(testList.size());
		Assert.assertTrue(testList.contains(board.getCell(1,0)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(2, testList.size());
	}
}
