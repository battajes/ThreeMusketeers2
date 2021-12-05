package assignment1;

import java.util.Scanner;

import assignment1.Piece.Type;
import assignment1.Exceptions.InvalidMoveException;

public class HumanAgent extends Agent  {
	private final Scanner scanner = new Scanner(System.in);
	private final Scanner s = new Scanner(System.in);
	private final Scanner m = new Scanner(System.in);
	private Type type;
	private Object icon;
	

    public HumanAgent(Board board, Type type) {
        super(board);
        this.type = type;
        this.name = this.setName();
        this.icon = this.getIcon();
        
    }

    /**
     * Asks the human for a move with from and to coordinates and makes sure its valid.
     * Create a Move object with the chosen fromCell and toCell
     * @return the valid human inputted Move
     */
    @Override
    public Move getMove() { // TODO
    	Coordinate first = null;
    	Boolean check = true;
    	
    	while(check) {
		try {
			System.out.print("Enter the cell you want to move: ");
	    	String from = scanner.next();
			first = Utils.parseUserMove(from);
			if (!board.getCell(first).hasPiece()) {
				continue;
			}
			if (board.getTurn() == board.getCell(first).getPiece().getType()) {
				check = false;
			}
			
		} catch (InvalidMoveException e) {
			System.out.println("Invalid Entry");
			
		}
    	}
    	
    	Coordinate second = null;
    	Boolean check1 = true;
    	while(check1) {
		try {
			System.out.print("Enter the cell you want to go to: ");
	    	String towards = scanner.next();
			second = Utils.parseUserMove(towards);
			if (board.getCell(first).getPiece().canMoveOnto(board.getCell(second))) {
				check1 = false;
			}
			
		} catch (InvalidMoveException e) {
			System.out.println("Invalid Entry");
		}
    	}
    	
    	Move move1 = new Move(board.getCell(first), board.getCell(second));
    	if (board.isValidMove(move1)) {
    		return move1;
    	}
    	else
    		return getMove();
    	
    	
    }

	@Override
	public String getName() {
		return this.name;
	
	}

	@Override
	public String setName() {
		System.out.print(this.type + ": Enter player name: " );
		return s.next();
	}
	
	public Object getIcon() {
		IconSelector hi = this.board.getIcon();

		System.out.println("For this game, each players get icons");
		System.out.println(hi.getNext());
		System.out.println("Showing icon, press\n"+ "N: to see the next icon for selection,\n"
				+ "S: to select the icon, and\n"
				+ "A: to add your own icon for " + this.type); 
		
		while (true) {	
		
		String h = m.next();

		if (h.equals("N")) {
			System.out.println(hi.getNext());
			System.out.println("Showing icon, press\n"+ "N: to see the next icon for selection,\n"
					+ "S: to select the icon, and\n"
					+ "A: to add your own icon "); 
		}
		else if (h.equals("S")) {
			
			Object hqw = hi.x.currItem();
			hi.selected();
			
			return hqw;
			
		}
		else if (h.equals("A")) {
			System.out.println("Please enter your own icon: ");
			return m.next();
		}
		else {
			System.out.println("Please enter a valid input");
		}
	}
	}
	public Object icon () {
		
		return this.icon;
	
	}
}
