package assignment1;

import java.util.List;

public class BoardEvaluatorImpl implements BoardEvaluator {

    /**
     * Calculates a score for the given Board
     * A higher score means the Musketeer is winning
     * A lower score means the Guard is winning
     * Add heuristics to generate a score given a Board
     * @param board Board to calculate the score of
     * @return int Score of the given Board
     */
    @Override
    public int evaluateBoard(Board board) { // TODO
        List<Cell> a = board.getMusketeerCells();
        int result = 20;
        int col1 = a.get(0).getCoordinate().col;
        int row1 = a.get(0).getCoordinate().row;
        int col2 = a.get(1).getCoordinate().col;
        int row2 = a.get(1).getCoordinate().row;
        int col3 = a.get(2).getCoordinate().col;
        int row3 = a.get(2).getCoordinate().row;
        int totalc = 0;
        
        if (col1 == col2)
        	totalc += 1;
        if (col1 == col3)
        	totalc += 1;
        if (col2 == col3)
        	totalc += 1;
        if (row1 == row2)
        	totalc += 1;
        if (row1 == row3)
        	totalc += 1;
        if (row2 == row3)
        	totalc += 1;
        
        
        List<Move> allMoves = board.getPossibleMoves();
        int size = allMoves.size();
        if (size == 0)
        	result += 20;
        if (size == 1)
        	result += 15;
        if (size == 2)
        	result += 10;
        if (size == 3)
        	result += 5;
        if (size == 5)
        	result -= 5;
        if (size == 6)
        	result -= 10;
        if (size == 7)
        	result -= 15;
        if (size >= 8)
        	result -= 20;
        
        
        
        result = result - (totalc *5);
        return result;
        
    }
}
