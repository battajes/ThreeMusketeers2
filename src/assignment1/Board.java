package assignment1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Board {
    public int size = 5;

    // 2D Array of Cells for representation of the game board
    public final Cell[][] board = new Cell[size][size];

    private Piece.Type turn;
    private Piece.Type winner;
    private IconSelector icons = new IconSelector();

    /**
     * Create a Board with the current player turn set.
     */
    public Board() {
        this.loadBoard("Boards/Starter.txt");
    }

    /**
     * Create a Board with the current player turn set and a specified board.
     * @param boardFilePath The path to the board file to import (e.g. "Boards/Starter.txt")
     */
    public Board(String boardFilePath) {
        this.loadBoard(boardFilePath);
    }

    /**
     * Creates a Board copy of the given board.
     * @param board Board to copy
     */
    public Board(Board board) {
        this.size = board.size;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.board[row][col] = new Cell(board.board[row][col]);
            }
        }
        this.turn = board.turn;
        this.winner = board.winner;
    }

    /**
     * @return the Piece.Type (Muskeeteer or Guard) of the current turn
     */
    public Piece.Type getTurn() {
        return turn;
    }

    /**
     * Get the cell located on the board at the given coordinate.
     * @param coordinate Coordinate to find the cell
     * @return Cell that is located at the given coordinate
     */
    public Cell getCell(Coordinate coordinate) { // TODO
        return this.board[coordinate.row][coordinate.col];
    }

    /**
     * @return the game winner Piece.Type (Muskeeteer or Guard) if there is one otherwise null
     */
    public Piece.Type getWinner() {
        return winner;
    }

    /**
     * Gets all the Musketeer cells on the board.
     * @return List of cells
     */
    public List<Cell> getMusketeerCells() { // TODO
    	int a = board.length;
    	List<Cell> b = new ArrayList<>();
    	for (int row = 0; row < a; row++) {
            for (int col = 0; col < a; col++) {
            	if (!board[row][col].hasPiece()) {
        			continue;
        		}
            	if (this.board[row][col].getPiece().getType() == Piece.Type.MUSKETEER) {
            		b.add(this.board[row][col]);
            	}
            }
    	}
        return b;
    }
    public IconSelector getIcon() {
    	return this.icons;
    }

    /**
     * Gets all the Guard cells on the board.
     * @return List of cells
     */
    public List<Cell> getGuardCells() { // TODO
    	int a = board.length;
    	List<Cell> b = new ArrayList<>();
    	for (int row = 0; row < a; row++) {
            for (int col = 0; col < a; col++) {
            	if (!board[row][col].hasPiece()) {
        			continue;
        		}
            	if (this.board[row][col].getPiece().getType() == Piece.Type.GUARD) {
            		b.add(this.board[row][col]);
            	}
            }
    	}
        return b;
    }

    /**
     * Executes the given move on the board and changes turns at the end of the method.
     * @param move a valid move
     */
    public void move(Move move) { // TODO
    	Cell cor1 = move.fromCell;
    	
    	if (cor1.getPiece().getType() == Piece.Type.MUSKETEER) {
    		this.turn =  Piece.Type.GUARD;
    	} 
    	else
    		this.turn =  Piece.Type.MUSKETEER;
    	move.toCell.setPiece(cor1.getPiece());
    	move.fromCell.removePiece();
    	
    }

    /**
     * Undo the move given.
     * @param move Copy of a move that was done and needs to be undone. The move copy has the correct piece info in the
     *             from and to cell fields. Changes turns at the end of the method.
     */
    public void undoMove(Move move) { // TODO
    	Cell a = move.fromCell;
    	Cell b = move.toCell;
    	this.board[a.getCoordinate().row][a.getCoordinate().col].setPiece(a.getPiece());
    	this.board[b.getCoordinate().row][b.getCoordinate().col].setPiece(b.getPiece());
    	
    	if (a.getPiece().getType() == Piece.Type.MUSKETEER) {
    		this.turn =  Piece.Type.MUSKETEER;
    	} 
    	else
    		this.turn =  Piece.Type.GUARD;
    	
    }

    /**
     * Checks if the given move is valid. Things to check:
     * (1) the toCell is next to the fromCell
     * (2) the fromCell piece can move onto the toCell piece.
     * @param move a move
     * @return     True, if the move is valid, false otherwise
     */
    public Boolean isValidMove(Move move) { // TODO
    	Cell a = move.fromCell;
    	Cell b = move.toCell;
    	if (b.getCoordinate().col <0 || b.getCoordinate().col >= this.board.length)
    		return false;
    	if (b.getCoordinate().row <0 || b.getCoordinate().row >= this.board.length)
    		return false;
    	if (Math.abs(a.getCoordinate().col - b.getCoordinate().col) > 1)
    		return false;
    	if (Math.abs(a.getCoordinate().row - b.getCoordinate().row) > 1)
    		return false;
    	
    	if (Math.abs(a.getCoordinate().col - b.getCoordinate().col) == 1) {
    		if (Math.abs(a.getCoordinate().row - b.getCoordinate().row) != 0)
    			return false;
    	}
    	if (Math.abs(a.getCoordinate().row - b.getCoordinate().row) == 1) {
    		if (Math.abs(a.getCoordinate().col - b.getCoordinate().col) != 0)
    			return false;
    	}
    	return a.getPiece().canMoveOnto(b);
    	
    	
    }

    /**
     * Get all the possible cells that have pieces that can be moved this turn.
     * @return      Cells that can be moved from the given cells
     */
    public List<Cell> getPossibleCells() { // TODO
    	int a = board.length;
    	List<Cell> b = new ArrayList<>();
    	for (int row = 0; row <= a-1; row++) {
            for (int col = 0; col <= a-1; col++) {
            	if (!board[row][col].hasPiece()) {
        			continue;
        		}
            	if (getTurn() == Piece.Type.MUSKETEER) {
                	if (board[row][col].getPiece().getType() == Piece.Type.MUSKETEER) {
                		if(row+1 < a) {
                			if (isValidMove(new Move(this.board[row][col], this.board[row+1][col]))) {
                				
                				if (!b.contains(this.board[row][col])) {
                					b.add(this.board[row][col]);
                				}
                			}
                		}
                		if(col+1 <a) {
                			
                			if (isValidMove(new Move(this.board[row][col], this.board[row][col+1]))) {
                				
                				if (!b.contains(this.board[row][col])) {
                					b.add(this.board[row][col]);
                				}
                			}
                		}
                		
                		if(col-1 >= 0) {
                			if (isValidMove(new Move(this.board[row][col], this.board[row][col-1]))) {
                				if (!b.contains(this.board[row][col])) {
                					b.add(this.board[row][col]);
                				}
                			}
                		}
                		if(row-1 >= 0) {
                			if (isValidMove(new Move(this.board[row][col], this.board[row-1][col]))) {
                				if (!b.contains(this.board[row][col])) {
                					b.add(this.board[row][col]);
                				}
                			}
                		}
                		
                	
                	}
            	}
            	else {
            		if (board[row][col].getPiece().getType() == Piece.Type.GUARD) {
            			if(row+1 < a) {
                			if (isValidMove(new Move(this.board[row][col], this.board[row+1][col]))) {
                				if (!b.contains(this.board[row][col])) {
                					b.add(this.board[row][col]);
                				}
                			}
                		}
                		if(col+1 <a) {
                			if (isValidMove(new Move(this.board[row][col], this.board[row][col+1]))) {
                				if (!b.contains(this.board[row][col])) {
                					b.add(this.board[row][col]);
                				}
                			}
                		}
                		
                		if(col-1 >= 0) {
                			if (isValidMove(new Move(this.board[row][col], this.board[row][col-1]))) {
                				if (!b.contains(this.board[row][col])) {
                					b.add(this.board[row][col]);
                				}
                			}
                		}
                		if(row-1 >= 0) {
                			if (isValidMove(new Move(this.board[row][col], this.board[row-1][col]))) {
                				if (!b.contains(this.board[row][col])) {
                					b.add(this.board[row][col]);
                				}
                			}
                		}
            	}
            	
            }
    	}
    	}
    	return b;
    }

    /**
     * Get all the possible cell destinations that is possible to move to from the fromCell.
     * @param fromCell The cell that has the piece that is going to be moved
     * @return List of cells that are possible to get to
     */
    public List<Cell> getPossibleDestinations(Cell fromCell) { // TODO
    	List<Cell> b = new ArrayList<>();
    	int col1 = fromCell.getCoordinate().col;
    	int row1 = fromCell.getCoordinate().row;
    	if (row1+1<board.length) {
    		if (isValidMove(new Move(fromCell, this.board[row1+1][col1]))) {
    			b.add(this.board[row1+1][col1]);
    		}
    		
    	}
    	if (row1-1>=0) {
    		if(isValidMove(new Move(fromCell, this.board[row1-1][col1]))) {
    			b.add(this.board[row1-1][col1]);
    		}
    	}
    	
    	if (col1+1<board.length) {
    		if(isValidMove(new Move(fromCell, this.board[row1][col1+1]))) {
    			b.add(this.board[row1][col1+1]);
    		}
    	}
    	
    	if (col1-1 >=0) {
    		if(isValidMove(new Move(fromCell, this.board[row1][col1-1]))) {
    			b.add(this.board[row1][col1-1]);
    		}
    	}
    	return b;
    	
    }

    /**
     * Get all the possible moves that can be made this turn.
     * @return List of moves that can be made this turn
     */
    public List<Move> getPossibleMoves() { // TODO
    	List<Move> result = new ArrayList<>();
        List<Cell> b = getPossibleCells();
        for (int i = 0; i< b.size(); i++) {
        	List<Cell> a = getPossibleDestinations(b.get(i));
        	for (int x = 0; x< a.size(); x++) {
        		Move move1 = new Move(b.get(i), a.get(x));
        		result.add(move1);
        	}
        	
        }
        return result;
    }

    /**
     * Checks if the game is over and sets the winner if there is one.
     * @return True, if the game is over, false otherwise.
     */
    public boolean isGameOver() { // TODO
        List<Move> allMoves = getPossibleMoves();
        if (allMoves.size() == 0) {
        	this.winner = Piece.Type.MUSKETEER;
        	return true;
        }
        List<Cell> allMusks = getMusketeerCells();
        if (allMusks.get(0).getCoordinate().col ==  allMusks.get(1).getCoordinate().col && allMusks.get(0).getCoordinate().col ==  allMusks.get(2).getCoordinate().col) {
        	this.winner= Piece.Type.GUARD;
        	return true;
        }
        if (allMusks.get(0).getCoordinate().row ==  allMusks.get(1).getCoordinate().row && allMusks.get(0).getCoordinate().row ==  allMusks.get(2).getCoordinate().row) {
        	this.winner = Piece.Type.GUARD;
        	return true;
        }
        
        
        return false;
        
    }

    /**
     * Saves the current board state to the boards directory
     */
    public void saveBoard() {
        String filePath = String.format("boards/%s.txt",
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        File file = new File(filePath);

        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(turn.getType() + "\n");
            for (Cell[] row: board) {
                StringBuilder line = new StringBuilder();
                for (Cell cell: row) {
                    if (cell.getPiece() != null) {
                        line.append(cell.getPiece().getSymbol());
                    } else {
                        line.append("_");
                    }
                    line.append(" ");
                }
                writer.write(line.toString().strip() + "\n");
            }
            writer.close();
            System.out.printf("Saved board to %s.\n", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Failed to save board to %s.\n", filePath);
        }
    }
 
    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder("  | A B C D E\n");
        boardStr.append("--+----------\n");
        for (int i = 0; i < size; i++) {
            boardStr.append(i + 1).append(" | ");
            for (int j = 0; j < size; j++) {
                Cell cell = board[i][j];
                boardStr.append(cell).append(" ");
            }
            boardStr.append("\n");
        }
        return boardStr.toString();
    }

    /**
     * Loads a board file from a file path.
     * @param filePath The path to the board file to load (e.g. "Boards/Starter.txt")
     */
    private void loadBoard(String filePath) {
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.printf("File at %s not found.", filePath);
            System.exit(1);
        }

        turn = Piece.Type.valueOf(scanner.nextLine().toUpperCase());

        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece: pieces) {
                Cell cell = new Cell(new Coordinate(row, col));
                switch (piece) {
                    case "O" -> cell.setPiece(new Guard());
                    case "X" -> cell.setPiece(new Musketeer());
                    default -> cell.setPiece(null);
                }
                this.board[row][col] = cell;
                col += 1;
            }
            col = 0;
            row += 1;
        }
        scanner.close();
        System.out.printf("Loaded board from %s.\n", filePath);
    }
}
