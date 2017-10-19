package chess;

/**
 * Class performs all the functionality of the king piece and inherits from the
 * class ChessPiece.
 * 
 * @author Andrew Olesak
 * @version February 23, 2016
 */
public class King extends ChessPiece {

	/**
	 * Constructor calls the super class to set the type of the player black or
	 * white
	 * 
	 * @param player
	 *            the type of player black or white
	 */
	public King(Player player) {
		super(player);
	}

	/**
	 * @return a string representing the type of the piece
	 */
	public String type() {
		return "King";
	}

	/**
	 * Verifies whether the suggested move for a king is allowed or not
	 * 
	 * @param move
	 *            the move of the piece
	 * @param board
	 *            a two dimensional array of the chess board
	 * @return true if the move is valid, otherwise false
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(!super.isValidMove(move, board)){
			return false;
		}
		if(Math.abs(move.fromRow-move.toRow)>1 || Math.abs(move.fromColumn-move.toColumn)>1){
			return false;
		}
		return true;
	}
}
