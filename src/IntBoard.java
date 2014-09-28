import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class IntBoard {
	private Set<BoardCell> targets;
	private HashMap<Integer, LinkedList<BoardCell>> adjacencies;
	private Set<BoardCell> visited;
	private BoardCell[][] board;
	public static final int numRows = 4;
	public static final int numCols = 4;
	//LinkedList<BoardCell> linkedlist = new LinkedList<BoardCell>();
	//BoardCell currCell = new BoardCell();
	
	public IntBoard() {
		targets = new HashSet<BoardCell>();
		adjacencies = new HashMap<Integer, LinkedList<BoardCell>>();
		visited = new HashSet<BoardCell>();
		board = new BoardCell[numCols][numRows];
		for (int c = 0; c < numCols; c++) {
			for (int r = 0; r < numRows; r++) {
				board[c][r] = new BoardCell(c, r);
			}
		}
		calcAdjacencies();
	}

	public void calcAdjacencies() {
		for (int c = 0; c < numCols; c++) {
			for (int r = 0; r < numRows; r++) {
				LinkedList<BoardCell> temp = new LinkedList<BoardCell>();
				if (c - 1 > -1) {
					temp.add(getCell(c - 1, r));
				}
				if (c + 1 < numCols) {
					temp.add(getCell(c + 1, r));
				}
				if (r - 1 > -1) {
					temp.add(getCell(c, r - 1));
				}
				if (r + 1 < numRows) {
					temp.add(getCell(c, r + 1));
				}
				adjacencies.put(getCell(c, r).getIndex(), temp);
			}
		}
	}
	
	public void calcTargets(BoardCell cell, int distance) {
		visited.add(cell);
		if (distance == 1) {
			for (BoardCell adj : getAdjList(cell)) {
				if(!targets.contains(adj) && !visited.contains(adj)) {
					System.out.println(adj.getIndex());
					targets.add(adj);
				}
			}
		}
		for(BoardCell adj : getAdjList(cell)) {
			if(!visited.contains(adj)){
				calcTargets(adj, distance - 1);
			}
		}
		visited.remove(cell);
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public LinkedList<BoardCell> getAdjList(BoardCell cell) {
		//System.out.println(cell.getIndex());
		return adjacencies.get(cell.getIndex());
	}
	
	public BoardCell getCell(int col, int row) {
		return board[col][row];
	}
}
