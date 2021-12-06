
package assignment1;

public abstract class Agent {

    protected Board board;
    public String name;
    
    /**
     * An Agent that can play ThreeMusketeers
     * @param board a Board the Agent can play on
     */
    public Agent(Board board){
        this.board = board;
    }
    
     /**
     * Gets a valid move that the Agent can perform on the Board
     * @return a valid Move that the Agent can do
     */
    public abstract Move getMove();
    
    public abstract String getName();
     
    public abstract String setName();
    public abstract Object getIcon();
    public abstract Object icon();
}

