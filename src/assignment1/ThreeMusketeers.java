package assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import assignment1.Piece.Type;


public class ThreeMusketeers {

    private final Board board;
    private Agent musketeerAgent, guardAgent;
    private final Scanner scanner = new Scanner(System.in);
    //private final List<Move> moves = new ArrayList<>();
    private final Memento menento;
=======

    private Agent player;

    // All possible game modes
    public enum GameMode {
        Human("Human vs Human"),
        HumanRandom("Human vs Computer (Random)"),
        HumanGreedy("Human vs Computer (Greedy)");

        private final String  gameMode;
        GameMode(final String gameMode) {
            this.gameMode = gameMode;
        }
    }

    /**
     * Default constructor to load Starter board
     */
    public ThreeMusketeers() {
        this.board = new Board();
        this.menento = new Memento(this.board);
    }

    /**
     * Constructor to load custom board
     * @param boardFilePath filepath of custom board
     */
    public ThreeMusketeers(String boardFilePath) {
        this.board = new Board(boardFilePath);
		this.menento =  new Memento(this.board);
    }

    /**
     * Play game with human input mode selector
     */
    public void play(){
        System.out.println("Welcome! \n");
        final GameMode mode = getModeInput();
        System.out.println("Playing " + mode.gameMode);
        play(mode);
    }

    /**
     * Play game without human input mode selector
     * @param mode the GameMode to run
     */
    public void play(GameMode mode){
        selectMode(mode);
        runGame();
    }

    /**
     * Mode selector sets the correct agents based on the given GameMode
     * @param mode the selected GameMode
     */
    private void selectMode(GameMode mode) {
        switch (mode) {
            case Human -> {
                musketeerAgent = new HumanAgent(board, Piece.Type.MUSKETEER);
                
                guardAgent = new HumanAgent(board, Piece.Type.GUARD);
            }
            case HumanRandom -> {
                String side = getSideInput();
                
                // The following statement may look weird, but it's what is known as a ternary statement.
                // Essentially, it sets musketeerAgent equal to a new HumanAgent if the value M is entered,
                // Otherwise, it sets musketeerAgent equal to a new RandomAgent
                musketeerAgent = side.equals("M") ? new HumanAgent(board, Piece.Type.MUSKETEER) : new RandomAgent(board);
                
                guardAgent = side.equals("G") ? new HumanAgent(board, Piece.Type.GUARD) : new RandomAgent(board);
            }
            case HumanGreedy -> {
                String side = getSideInput();
                musketeerAgent = side.equals("M") ? new HumanAgent(board, Piece.Type.MUSKETEER) : new GreedyAgent(board);
                guardAgent = side.equals("G") ? new HumanAgent(board, Piece.Type.GUARD) : new GreedyAgent(board);
            }
        }
    }

    /**
     * Runs the game, handling human input for move actions
     * Handles moves for different agents based on current turn 
     */
    private void runGame() {
        while(!board.isGameOver()) {
        	
            System.out.println("\n" + board);
            
            final Agent currentAgent;
            
           
            if (board.getTurn() == Piece.Type.MUSKETEER) {
                currentAgent = musketeerAgent;

            }
            else {
                currentAgent = guardAgent;

            }

            if (currentAgent instanceof HumanAgent) // Human move
            	
            	
                switch (getInputOption()) {
                    case "M":
                        move(currentAgent);
                        break;
                    case "U":
                        if (this.menento.getSize() == 0) {
                            System.out.println("No moves to undo.");
                            continue;
                        }
                        else if (this.menento.getSize()  == 1 || isHumansPlaying()) {
                            undoMove();
                        }
                        else {
                            undoMove();
                        }
                        break;
                    case "S":
                        board.saveBoard();
                        break;
                }
            else { // Computer move
                System.out.printf("[%s] Calculating move...\n", currentAgent.getClass().getSimpleName());
                move(currentAgent);
            }
        }

        System.out.println(board);
        System.out.printf("\n%s won!%n", this.getAgentName(this.board.getWinner()));

        if (!isHumansPlaying()) {
        	final Agent currentAgent;
        	if (board.getTurn() == Piece.Type.MUSKETEER)
                currentAgent = musketeerAgent;
            else
                currentAgent = guardAgent;
        	if (currentAgent instanceof HumanAgent) {
	             System.out.println("Who was playing? ");
	             String test = scanner.next();
	             System.out.println("------");
	             System.out.print(test);
        	}
        }

    }

    /**
     * Gets a move from the given agent, adds a copy of the move using the copy constructor to the moves stack, and
     * does the move on the board.
     * @param agent Agent to get the move from.
     */
    protected void move(final Agent agent) {
    	Move move1 = agent.getMove();
    	Move move2 = new Move(move1);
    	this.menento.setState(move2);
    	//moves.add(move2);
    	board.move(move1);
    }

    /**
     * Removes a move from the top of the moves stack and undoes the move on the board.
     */
    private void undoMove() {
    	//int a = moves.size();
    	
    	//Move oldmove = moves.remove(a - 1);
    	Move oldmove = this.menento.getState();
    	board.undoMove(oldmove);
    }
    
    private Agent getPlayer() {
    	this.getCurrentAgent();
    	return this.player;
    }

    /**
     * Get human input for move action
     * @return the selected move action, 'M': move, 'U': undo, and 'S': save
     */
    private String getInputOption() {
        System.out.printf("[%s] Enter 'M' to move, 'U' to undo, and 'S' to save: " , this.getCurrentAgent().getName() );
        while (!scanner.hasNext("[MUSmus]")) {
            System.out.print("Invalid option. Enter 'M', 'U', or 'S': ");
            scanner.next();
        }
        return scanner.next().toUpperCase();
    }
    
    public Agent getCurrentAgent() {
        if (board.getTurn() == Piece.Type.MUSKETEER) {
        	this.player = musketeerAgent;
            return musketeerAgent;
        }
        this.player = guardAgent;
        return guardAgent;
    }
    public String getAgentName(Type type) {
        if (type == Piece.Type.MUSKETEER) {
            return musketeerAgent.getName();
        }
  
        return guardAgent.getName();
    }


    /**
     * Returns whether both sides are human players
     * @return True if both sides are Human, False if one of the sides is a computer
     */
    private boolean isHumansPlaying() {
        return musketeerAgent instanceof HumanAgent && guardAgent instanceof HumanAgent;
    }

    /**
     * Get human input for side selection
     * @return the selected Human side for Human vs Computer games,  'M': Musketeer, G': Guard
     */
    private String getSideInput() {
        System.out.print("Enter 'M' to be a Musketeer or 'G' to be a Guard: ");
        while (!scanner.hasNext("[MGmg]")) {
            System.out.println("Invalid option. Enter 'M' or 'G': ");
            scanner.next();
        }
        return scanner.next().toUpperCase();
    }
 
    /**
     * Get human input for selecting the game mode
     * @return the chosen GameMode
     */
    private GameMode getModeInput() {
        System.out.println("""
                    0: Human vs Human
                    1: Human vs Computer (Random)
                    2: Human vs Computer (Greedy)""");
        System.out.print("Choose a game mode to play i.e. enter a number: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid option. Enter 0, 1, or 2: ");
            scanner.next();
        }
        final int mode = scanner.nextInt();
        if (mode < 0 || mode > 2) {
            System.out.println("Invalid option.");
            return getModeInput();
        }
        return GameMode.values()[mode];
    }

    public static void main(String[] args) {
        String boardFileName = "Boards/Starter.txt";
        ThreeMusketeers game = new ThreeMusketeers(boardFileName);
        game.play();
    }
}
