package chess;

/**
 * Class performs all the functionality of the knight piece and inherits from
 * the class ChessPiece.
 * 
 * @author Andrew Olesak
 * @version February 23, 2016
 */
public class Knight extends ChessPiece {

	/**
	 * Constructor calls the super class to set the type of the player black or
	 * white
	 * 
	 * @param player
	 *            the type of player black or white
	 */
	public Knight(Player player) {
		super(player);
	}

	/**
	 * @return a string representing the type of the piece
	 */
	public String type() {
		return "Knight";
	}

	/**
	 * Verifies whether the suggested move for a knight is allowed or not
	 * 
	 * @param move
	 *            the move of the piece
	 * @param board
	 *            a two dimensional array of the chess board
	 * @return true if the move is valid, otherwise false
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if (!super.isValidMove(move, board)) {
			return false;
		}
		if (Math.abs(move.fromRow - move.toRow) == 2) {
			if (Math.abs(move.fromColumn - move.toColumn) != 1) {
				return false;
			}
		}
		if (Math.abs(move.fromRow - move.toRow) == 1) {
			if (Math.abs(move.fromColumn - move.toColumn) != 2) {
				return false;
			}
		}
		if (move.fromRow == move.toRow || Math.abs(move.fromRow - move.toRow) > 2) {
			return false;
		}
		return true;
	}
}
