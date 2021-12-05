package assignment1;


import java.util.List;
import java.util.Random;

public class RandomAgent extends Agent {
	

    private String icon;


	public RandomAgent(Board board) {
        super(board);
        this.name = this.setName();
        this.icon = this.getIcon();
       
        
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
    
	
	public String getIcon () {
		System.out.println("Printing the RandomAgent's icon");
		 int r = (int) (Math.random()*5);
	        String i = new String [] {"（˶′◡‵˶）","(⋟﹏⋞)","❀◕ ‿ ◕❀"}[r];
	       this.icon = i;
	       return i;
	}
		
	
	public Object icon () {
		
		return this.icon;
	
	}
    
}