package chess;

/**
 * Class performs all the functionality of the bishop piece and inherits from
 * the class ChessPiece.
 * 
 * @author Andrew Olesak
 * @version February 23, 2016
 */
public class Bishop extends ChessPiece {

	/**
	 * Constructor calls the super class to set the type of the player black or
	 * white
	 * 
	 * @param player
	 *            the type of player black or white
	 */
	public Bishop(Player player) {
		super(player);
	}

	/**
	 * @return a string representing the type of the piece
	 */
	public String type() {
		return "Bishop";
	}

	/**
	 * Verifies whether the suggested move for a bishop is allowed or not
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
		if (Math.abs(move.fromRow - move.toRow) != Math.abs(move.fromColumn - move.toColumn)) {
			return false;
		}
		if (move.fromRow > move.toRow) {
			if (move.fromColumn > move.toColumn) {
				for (int row = move.toRow + 1; row < move.fromRow; row++) {
					for (int col = move.toColumn + 1; col < move.fromColumn; col++) {
						if (row - move.toRow == col - move.toColumn && board[row][col] != null) {
							return false;
						}
					}
				}
			} else {
				if (move.toColumn > move.fromColumn) {
					for (int row = move.toRow + 1; row < move.fromRow; row++) {
						for (int col = move.toColumn - 1; col > move.fromColumn; col--) {
							if (row - move.toRow == Math.abs(col - move.toColumn) && board[row][col] != null) {
								return false;
							}
						}
					}
				}
			}
		} else {
			if (move.fromColumn > move.toColumn) {
				for (int row = move.toRow - 1; row > move.toRow; row--) {
					for (int col = move.toColumn + 1; col < move.fromColumn; col++) {
						if (Math.abs(row - move.toRow) == col - move.toColumn && board[row][col] != null) {
							return false;
						}
					}
				}
			} else {
				for (int row = move.fromRow + 1; row < move.toRow; row++) {
					for (int col = move.fromColumn + 1; col < move.toColumn; col++) {
						if (row - move.fromRow == col - move.fromColumn && board[row][col] != null) {
							return false;
						}
					}
				}
			}
		}

		return true;
	}
}
