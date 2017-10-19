package chess;

/**
 * Class performs all the functionality of the pawn piece and inherits from the
 * class ChessPiece.
 * 
 * @author Andrew Olesak
 * @version February 23, 2016
 */
public class Pawn extends ChessPiece {

	private boolean firstMove;

	/**
	 * Constructor sets an instance variable and calls the super class to set
	 * the type of the player black or white
	 * 
	 * @param player
	 *            the type of player black or white
	 */
	public Pawn(Player player) {
		super(player);
		this.firstMove = true;
	}

	/**
	 * @return a string representing the type of the piece
	 */
	public String type() {
		return "Pawn";
	}

	/**
	 * Verifies whether the suggested move for a pawn is allowed or not
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
		if (p.player() == Player.WHITE) {
			if (firstMove) {
				if (move.fromRow != move.toRow + 1 && move.fromRow != move.toRow + 2) {
					return false;
				}
				if (move.toRow + 1 == move.fromRow) {
					if (move.toColumn != move.fromColumn && Math.abs(move.fromColumn - move.toColumn) != 1) {
						return false;
					}
					if (move.toColumn == move.fromColumn && board[move.toRow][move.toColumn] != null) {
						return false;
					}
					if (Math.abs(move.fromColumn - move.toColumn) == 1 && board[move.toRow][move.toColumn] == null) {
						return false;
					}
				}
				if (move.toRow + 2 == move.fromRow) {
					if (move.toColumn != move.fromColumn) {
						return false;
					}
					if (move.toColumn == move.fromColumn) {
						if (board[move.toRow][move.toColumn] != null
								|| board[move.fromRow - 1][move.fromColumn] != null) {
							return false;
						}
					}
				}
				this.firstMove = false;
			} else {

				if (move.fromRow != move.toRow + 1) {
					return false;
				}
				if (move.toRow + 1 == move.fromRow) {
					if (move.toColumn != move.fromColumn && Math.abs(move.fromColumn - move.toColumn) != 1) {
						return false;
					}
					if (move.toColumn == move.fromColumn && board[move.toRow][move.toColumn] != null) {
						return false;
					}
					if (Math.abs(move.fromColumn - move.toColumn) == 1 && board[move.toRow][move.toColumn] == null) {
						return false;
					}
				}
			}
		} else {
			if (firstMove) {
				if (move.fromRow != move.toRow - 1 && move.fromRow != move.toRow - 2) {
					return false;
				}
				if (move.toRow - 1 == move.fromRow) {
					if (move.toColumn != move.fromColumn && Math.abs(move.fromColumn - move.toColumn) != 1) {
						return false;
					}
					if (move.toColumn == move.fromColumn && board[move.toRow][move.toColumn] != null) {
						return false;
					}
					if (Math.abs(move.fromColumn - move.toColumn) == 1 && board[move.toRow][move.toColumn] == null) {
						return false;
					}
				}
				if (move.toRow - 2 == move.fromRow) {
					if (move.toColumn != move.fromColumn) {
						return false;
					}
					if (move.fromColumn == move.toColumn) {
						if (board[move.toRow][move.toColumn] != null || board[move.toRow - 1][move.toColumn] != null) {
							return false;
						}
					}
				}
				firstMove = false;
			} else {
				if (move.fromRow != move.toRow - 1) {
					return false;
				}
				if (move.toRow - 1 == move.fromRow) {
					if (move.toColumn != move.fromColumn && Math.abs(move.fromColumn - move.toColumn) != 1) {
						return false;
					}
					if (move.toColumn == move.fromColumn && board[move.toRow][move.toColumn] != null) {
						return false;
					}
					if (Math.abs(move.fromColumn - move.toColumn) == 1 && board[move.toRow][move.toColumn] == null) {
						return false;
					}
				}
			}
		}
		return true;
	}
}