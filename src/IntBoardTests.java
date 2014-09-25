
import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class IntBoardTests {
	private static IntBoard board;
	
	@Before
	public void setUpBeforeClass() {
		board = new IntBoard();
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
	
	@Test
	public void testAdjacencyEdge() {
		LinkedList<BoardCell> testList = board.getAdjList(board.getCell(1,0));
		Assert.assertTrue(testList.contains(board.getCell(0,0)));
		Assert.assertTrue(testList.contains(board.getCell(1, 1)));
		Assert.assertTrue(testList.contains(board.getCell(2, 0)));
		Assert.assertEquals(3, testList.size());
	}

	@Test
	public void testAdjacencyCenter() {
		LinkedList<BoardCell> testList = board.getAdjList(board.getCell(2,2));
		Assert.assertTrue(testList.contains(board.getCell(1,2)));
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(2, 1)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void testTargets0_3()
	{
		board.calcTargets(board.getCell(0, 0), 3);
		Set targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCell(3, 0)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(0, 3)));
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
		Assert.assertEquals(6, targets.size());
	}
	
	@Test
	public void testTargets11_3()
	{
		board.calcTargets(board.getCell(1, 1), 3);
		Set targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(3, 0)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
		Assert.assertTrue(targets.contains(board.getCell(0, 3)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(2, 3)));
		Assert.assertTrue(targets.contains(board.getCell(3, 2)));
		Assert.assertEquals(8, targets.size());
	}
	
	@Test
	public void testTargets10_2()
	{
		board.calcTargets(board.getCell(1, 0), 2);
		Set targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(3, 0)));
		Assert.assertEquals(4, targets.size());
	}
}
