package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	BoardCell[][] board;
	Map<Character, String> rooms;
	int numRows;
	int numCols;
		
	private Set<BoardCell> targets;
	private HashMap<Integer, LinkedList<BoardCell>> adjacencies;
	private Set<BoardCell> visited;
	
	public Board(Map<Character, String> rooms) {
		targets = new HashSet<BoardCell>();
		adjacencies = new HashMap<Integer, LinkedList<BoardCell>>();
		visited = new HashSet<BoardCell>();
		this.rooms = rooms;
	}
	
	public void loadBoardConfig(String layout) throws BadConfigFormatException {
		FileReader reader = null;
		Scanner in = null;
		//Read file
		try {
			reader = new FileReader(layout);
			in = new Scanner(reader);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		//Get number of rows and columns
		try {
			numRows = 0;
			numCols = 0;
			String temp = null;
			while (in.hasNext()) {
				numRows++;
				temp = in.nextLine();
			}
			String[] tmp = temp.split(",");
			numCols = tmp.length;
			in.close();
		} catch (Exception e) {
			System.out.println("The layout file is not formatted correctly");
		}
		//Read file again since we read through it already
		try {
			reader = new FileReader(layout);
			in = new Scanner(reader);
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		//Load board
		try{
			int row = 0;
			int col = 0;
			board = new BoardCell[numRows][numCols];
			
			while (in.hasNext()) {
				col = 0;
				String[] split = in.nextLine().split(",");
				
				//If not every column has the right length throw an exception
				if (split.length != numCols) {
					throw new BadConfigFormatException("The layout file has columns of different lengths.");
				}
				
				for(String cell : split) {
					//Check if cell has a proper initial
					if(!rooms.containsKey(cell.charAt(0))) {
						throw new BadConfigFormatException("The layout file has an initial not recognized by the legend.");
					}
					
					//Read in walkways
					if (cell.equals("W")){
						board[row][col] = new WalkwayCell(row, col);
					} else {
						//Read in rooms
						board[row][col] = new RoomCell(row, col, cell.charAt(0));
						if (cell.length() > 1) {
							if (cell.charAt(1) == 'L'){
								((RoomCell)board[row][col]).setDoorDirection(RoomCell.DoorDirection.LEFT);
							} else if (cell.charAt(1) == 'R') {
								((RoomCell)board[row][col]).setDoorDirection(RoomCell.DoorDirection.RIGHT);
							} else if (cell.charAt(1) == 'U') {
								((RoomCell)board[row][col]).setDoorDirection(RoomCell.DoorDirection.UP);
							} else if (cell.charAt(1) == 'D') {
								((RoomCell)board[row][col]).setDoorDirection(RoomCell.DoorDirection.DOWN);
							} else {
								((RoomCell)board[row][col]).setDoorDirection(RoomCell.DoorDirection.NONE);
							}
						} else {
							((RoomCell)board[row][col]).setDoorDirection(RoomCell.DoorDirection.NONE);
						}
					}
					col++;
				}
				row++;
			}
		} catch (BadConfigFormatException e) {
			System.out.println("The layout file is not formatted correctly");
			throw e;
		}
	}
	
	public void calcAdjacencies() {
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				LinkedList<BoardCell> temp = new LinkedList<BoardCell>();
				
				//For walkway cells
				if (this.getCellAt(r, c).isWalkway()) {
					if (c - 1 > -1) {
						if ( this.getCellAt(r, c - 1).isWalkway() || 
								(this.getCellAt(r, c - 1).isDoorway() 
										&& ((RoomCell)this.getCellAt(r, c - 1)).getDoorDirection() == RoomCell.DoorDirection.RIGHT )) 
						{
							temp.add(getCellAt(r, c - 1));
						}
					}
					if (c + 1 < numCols) {
						if ( this.getCellAt(r, c + 1).isWalkway() || 
								(this.getCellAt(r, c + 1).isDoorway() 
										&& ((RoomCell)this.getCellAt(r, c + 1)).getDoorDirection() == RoomCell.DoorDirection.LEFT )) 
						{
							temp.add(getCellAt(r, c + 1));
						}
						
					}
					if (r - 1 > -1) {
						if ( this.getCellAt(r - 1, c).isWalkway() || 
								(this.getCellAt(r - 1, c).isDoorway() 
										&& ((RoomCell)this.getCellAt(r - 1, c)).getDoorDirection() == RoomCell.DoorDirection.DOWN )) 
						{
							temp.add(getCellAt(r - 1, c));
						}
						
					}
					if (r + 1 < numRows) {
						if ( this.getCellAt(r + 1, c).isWalkway() || 
								(this.getCellAt(r + 1, c).isDoorway() 
										&& ((RoomCell)this.getCellAt(r + 1, c)).getDoorDirection() == RoomCell.DoorDirection.UP )) 
						{
							temp.add(getCellAt(r + 1, c));
						}
					}
				} else if (this.getCellAt(r, c).isRoom()) {
					//If room cell has a direction other than none it is a doorway and will have one adjacency
					//Otherwise it isn't a door and will have none
					RoomCell.DoorDirection direction = ((RoomCell)this.getCellAt(r, c)).getDoorDirection();
					switch (direction) {
					case LEFT:	if(c - 1 > -1){ temp.add(getCellAt(r, c - 1));}
						break;
					case RIGHT:  if(c + 1 < numCols){ temp.add(getCellAt(r, c + 1));}
						break;
					case UP:  if(r - 1 > -1){ temp.add(getCellAt(r - 1, c));}
						break;
					case DOWN:  if(r + 1 < numRows){ temp.add(getCellAt(r + 1, c));}
						break;
					case NONE:  
						break;
					}
				}
				adjacencies.put(getCellAt(r, c).getIndex(numCols), temp);
			}
		}
	}
	
	public void calcTargets(int row, int col, int distance) {
		targets.clear();
		this.calcTargets2(row, col, distance);
	}
	public void calcTargets2(int row, int col, int distance) {
		BoardCell cell = this.getCellAt(row, col);
		visited.add(cell);
		if (distance == 1) {
			for (BoardCell adj : getAdjList(cell.getRow(), cell.getCol())) {
				if(!targets.contains(adj) && !visited.contains(adj)) {
					targets.add(adj);
				}
			}
		} else {
			for(BoardCell adj : getAdjList(cell.getRow(), cell.getCol())) {
				if(!visited.contains(adj)){
					if (adj.isDoorway()) {
						targets.add(adj);
					} else {
						calcTargets2(adj.getRow(), adj.getCol(), distance - 1);
					}
				}
			} 
		}
		visited.remove(cell);
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public LinkedList<BoardCell> getAdjList(int row, int col) {
		return adjacencies.get(this.getCellAt(row, col).getIndex(numCols));
	}
	
	public BoardCell getCellAt(int row, int col) {
		return board[row][col];
	}
	
	public RoomCell getRoomCell(int row, int col) {
		if (board[row][col].isRoom()) {
			return (RoomCell) board[row][col];
		} else {
			return null;
		}
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}
	
	
}
