package chess;

/**
 * Class performs base funcionality for all pieces, implements IChessPiece, and
 * is a super class for all of the pieces classes.
 * 
 * @author Andrew Olesak
 * @version February 23, 2016
 *
 */
public abstract class ChessPiece implements IChessPiece {

	private Player owner;

	/**
	 * Constructor sets the value to an instance variable
	 * 
	 * @param player
	 *            the type of player black or white
	 */
	protected ChessPiece(Player player) {
		this.owner = player;
	}

	/**
	 * Abstract method is used in the individual chess piece classes
	 */
	public abstract String type();

	/**
	 * @return the current player
	 */
	public Player player() {
		return this.owner;
	}

	/**
	 * Verifies whether the suggested move is allowed or not for all pieces
	 * 
	 * @param move
	 *            the move of the piece
	 * @param board
	 *            a two dimensional array of the chess board
	 * @return true if move is valid, otherwise false
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if (move.fromRow == move.toRow && move.fromColumn == move.toColumn) {
			return false;
		}
		ChessPiece startPiece = (ChessPiece) board[move.fromRow][move.fromColumn];
		ChessPiece endPiece = (ChessPiece) board[move.toRow][move.toColumn];
		if (startPiece == null) {
			return false;
		}
		if (endPiece != null && startPiece.owner == endPiece.owner) {
			return false;
		}
		return true;
	}

}
