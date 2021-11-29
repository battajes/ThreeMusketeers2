package assignment1;

import java.util.List;
import java.util.Random;

public class RandomAgent extends Agent {
	

    public RandomAgent(Board board) {
        super(board);
        this.name = this.setName();
    }

    /**
     * Gets a valid random move the RandomAgent can do.
     * @return a valid Move that the RandomAgent can perform on the Board
     */
    @Override
    public Move getMove() {
    	List<Move> movesList = board.getPossibleMoves();
        Random anyMove = new Random();
        Move randomMove = movesList.get(anyMove.nextInt(movesList.size()));
        return randomMove;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String setName() {
    	return this.name = "Random";
    }
    
}
