package chess;

/**
 * Class performs all the functionality of the queen piece and inherits from the
 * class ChessPiece.
 * 
 * @author Andrew Olesak
 * @version February 23, 2016
 */
public class Queen extends ChessPiece {

	/**
	 * Constructor calls the super class to set the type of the player black or
	 * white
	 * 
	 * @param player
	 *            the type of player black or white
	 */
	public Queen(Player player) {
		super(player);
	}

	/**
	 * @return a string representing the type of the piece
	 */
	public String type() {
		return "Queen";
	}

	/**
	 * Verifies whether the suggested move for a queen is allowed or not
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
		ChessPiece p = (ChessPiece) board[move.fromRow][move.fromColumn];
		Rook r = new Rook(p.player());
		Bishop b = new Bishop(p.player());
		if (!r.isValidMove(move, board) && !b.isValidMove(move, board)) {
			return false;
		}
		return true;
	}

}
