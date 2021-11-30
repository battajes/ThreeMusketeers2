package assignment1;

public class Musketeer extends Piece {

    public Musketeer() {
        super("X", Type.MUSKETEER);
    }
 
    /**
     * Returns true if the Musketeer can move onto the given cell.
     * @param cell Cell to check if the Musketeer can move onto
     * @return True, if Musketeer can move onto given cell, false otherwise
     */
    @Override
    public boolean canMoveOnto(Cell cell) {
    	if  (cell.hasPiece() == false) {
			return false;
		}
		if (cell.getPiece().getType() == Piece.Type.GUARD) {
			return true;
		}
		else
			return false;    
		}
}
