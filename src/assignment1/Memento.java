
package assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import assignment1.Piece.Type;

import java.util.HashMap;

public class Memento {
	
	private List<Move> moves= new ArrayList<>();
	private final Scanner scanner = new Scanner(System.in);
	private Board board;
	private Type state;
	
	
	public Memento(Board board) {
		this.board = board;
		this.state = Piece.Type.MUSKETEER;
	}


	public Move getState() {
		// TODO Auto-generated method stub
		
		int remove = this.getSize();
		int moveNum = 1;
		if( this.getSize() == 1){
			return moves.remove(0);
		}
		System.out.println("Printing out all your pervious moves: ");
		this.state = Piece.Type.MUSKETEER;
		for (Move move: this.moves) {
			
			System.out.println(moveNum + ": " + this.state + " - " + move.toString());
			this.changeState();
			moveNum = moveNum + 1;
		}
		
		System.out.println("Please enter the number of the move you want to undo; ");
		scanner.hasNextInt();
		while (true ) {
			if (!scanner.hasNextInt()) {
            System.out.print("Invalid option. Please enter a number: ");
            scanner.next();
			}
        
		while (scanner.hasNextInt()) {
			int hi = scanner.nextInt();

			if (hi <= this.getSize()) {
				while (remove != hi) {
					board.undoMove(moves.remove(remove-1));
					remove -= 1;
				}
				return moves.remove(hi-1);
				
			}
			else {
				System.out.println("Please enter a valid move");
				
			}
		}
		
		}
		
	}
	


	public int getSize() {
		// TODO Auto-generated method stub
		return this.moves.size();
	}

	public void setState(Move move2) {
		this.moves.add(move2);
		this.state = board.getTurn();
		this.changeState();
	}

	private void changeState() {
		if (this.state == Piece.Type.GUARD){
			this.state = Piece.Type.MUSKETEER;
		}
		else {
			this.state = Piece.Type.GUARD;
		}
	}

}