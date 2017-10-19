package chess;

/**
 * Class stores the chess board and implements the game logic
 * 
 * @author Andrew Olesak
 * @version February 23, 216
 */
public class ChessModel implements IChessModel {

	private IChessPiece[][] board;
	private Player player;
	private int killPieceRow;
	private int killPieceCol;

	/**
	 * Constructor sets values to the instance variables and creates the virtual
	 * aspects of the board and pieces
	 */
	public ChessModel() {
		this.board = new IChessPiece[8][8];
		this.player = Player.WHITE;
		this.killPieceRow = 0;
		this.killPieceCol = 0;

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (row == 0) {
					switch (col) {
					case 0:
					case 7:
						this.board[row][col] = new Rook(Player.BLACK);
						break;

					case 1:
					case 6:
						this.board[row][col] = new Knight(Player.BLACK);
						break;

					case 2:
					case 5:
						this.board[row][col] = new Bishop(Player.BLACK);
						break;

					case 3:
						this.board[row][col] = new Queen(Player.BLACK);
						break;

					case 4:
						this.board[row][col] = new King(Player.BLACK);
						break;
					}
				}
				if (row == 1) {
					this.board[row][col] = new Pawn(Player.BLACK);
				}
				if (row == 6) {
					this.board[row][col] = new Pawn(Player.WHITE);
				}
				if (row == 7) {
					switch (col) {
					case 0:
					case 7:
						this.board[row][col] = new Rook(Player.WHITE);
						break;

					case 1:
					case 6:
						this.board[row][col] = new Knight(Player.WHITE);
						break;

					case 2:
					case 5:
						this.board[row][col] = new Bishop(Player.WHITE);
						break;

					case 3:
						this.board[row][col] = new Queen(Player.WHITE);
						break;

					case 4:
						this.board[row][col] = new King(Player.WHITE);
						break;
					}
				}
			}
		}
	}

	/**
	 * Checks for a valid move by calling the according chess piece classes
	 * 
	 * @param move
	 *            the move that a specific piece would like to perform
	 * @return true if the move is valid, otherwise false
	 */
	public boolean isValidMove(Move move) {
		ChessPiece p = (ChessPiece) this.board[move.fromRow][move.fromColumn];
		if (p == null) {
			return false;
		} else {
			if (!p.isValidMove(move, board)) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * Moves the piece on the board from the start location to the end location
	 * 
	 * @param move
	 *            the move that a specific piece would like to perform
	 */
	public void move(Move move) {
		ChessPiece p = (ChessPiece) this.board[move.fromRow][move.fromColumn];
		if (p.type() == "Pawn" && this.currentPlayer() == Player.BLACK && move.toRow == 7) {
			this.board[move.fromRow][move.fromColumn] = null;
			this.board[move.toRow][move.toColumn] = new Queen(Player.BLACK);
		} else if (p.type() == "Pawn" && this.currentPlayer() == Player.WHITE && move.toRow == 0) {
			this.board[move.fromRow][move.fromColumn] = null;
			this.board[move.toRow][move.toColumn] = new Queen(Player.WHITE);
		} else {
			this.board[move.fromRow][move.fromColumn] = null;
			this.board[move.toRow][move.toColumn] = p;
		}
	}

	/**
	 * checks to see if the given player's king is in check
	 * 
	 * @param p
	 *            the player black or white
	 * @return true if the player's king is in check, otherwise false
	 */
	public boolean inCheck(Player p) {
		int kingRow = 0;
		int kingCol = 0;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				ChessPiece piece = (ChessPiece) board[row][col];
				if (piece != null && piece.player() == p && piece.type() == "King") {
					kingRow = row;
					kingCol = col;
					break;
				}
			}
		}

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				ChessPiece piece = (ChessPiece) board[row][col];
				if (piece != null && piece.player() != p) {
					Move m = new Move(row, col, kingRow, kingCol);
					if (piece.isValidMove(m, board)) {
						this.setKillPiece(row, col);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * checks to see if a player is in check mate
	 * 
	 * @return true if the player is in check mate, otherwise false
	 */
	public boolean isComplete() {
		if (this.canKillInCheckPiece(this.killPieceRow, this.killPieceCol)) {
			return false;
		}
		int kingRow = 0;
		int kingCol = 0;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				ChessPiece piece = (ChessPiece) board[row][col];
				if (piece != null && piece.player() == this.currentPlayer() && piece.type() == "King") {
					kingRow = row;
					kingCol = col;
					break;
				}
			}
		}
		boolean neverMoved = true;
		int surroundingSpotsInCheck = 0;
		int lastRow = kingRow;
		int lastCol = kingCol;
		for (int r = kingRow - 1; r < kingRow + 2; r++) {
			for (int c = kingCol - 1; c < kingCol + 2; c++) {
				if (r > 0 && r < 8 && c > 0 && c < 8) {
					if (board[r][c] == null) {
						neverMoved = false;
						Move m = new Move(lastRow, lastCol, r, c);
						lastRow = r;
						lastCol = c;
						this.move(m);
						if (!this.inCheck(this.currentPlayer())) {
							if (kingRow == r && kingCol == c && this.onlyKingIsLeft()) {
								continue;
							} else {
								Move place = new Move(lastRow, lastCol, kingRow, kingCol);
								this.move(place);
								return false;
							}
						} else {
							if (this.onlyKingIsLeft() && kingRow != r || kingCol != c) {
								surroundingSpotsInCheck++;
							}
						}
					}
				}
			}
		}
		if (neverMoved) {
			return false;
		}
		Move m = new Move(lastRow, lastCol, kingRow, kingCol);
		this.move(m);
		if (!this.inCheck(this.currentPlayer()) && !this.onlyKingIsLeft()) {
			return false;
		}
		if (this.onlyKingIsLeft() && !this.inCheck(this.currentPlayer()) && surroundingSpotsInCheck == 8) {
			return true;
		}
		if (this.onlyKingIsLeft() && !this.inCheck(this.currentPlayer()) && surroundingSpotsInCheck != 8) {
			return false;
		}
		return true;
	}

	/**
	 * @return true if all the players of the current player are eliminated
	 *         except the king, otherwise false
	 */
	private boolean onlyKingIsLeft() {
		for (int row = 0; row < 8; row++)
			for (int col = 0; col < 8; col++) {
				ChessPiece p = (ChessPiece) board[row][col];
				if (p != null && p.player() == this.currentPlayer() && p.type() != "King") {
					return false;
				}
			}
		return true;
	}

	/**
	 * Helper method that sets the place on the board for the player that currently
	 * has the king in check
	 * 
	 * @param row
	 *            the row of the piece
	 * @param col
	 *            the column of the piece
	 */
	private void setKillPiece(int row, int col) {
		this.killPieceRow = row;
		this.killPieceCol = col;
	}

	/**
	 * Helper method that checks to see if a player can kill an opposing player
	 * who currently has the king in check
	 * 
	 * @param toRow
	 *            the row of the piece that has the current king in check
	 * @param toCol
	 *            the column of the piece that has the current king in check
	 * @return true if a player can kill the given piece, otherwise false
	 */
	private boolean canKillInCheckPiece(int toRow, int toCol) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				ChessPiece piece = (ChessPiece) board[row][col];
				Move move = new Move(row, col, toRow, toCol);
				if (piece != null && piece.player() == this.currentPlayer() && piece.isValidMove(move, board)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * creates a new game object
	 * 
	 * @return a new ChessModel object
	 */
	public ChessModel reset() {
		return new ChessModel();
	}

	/**
	 * @return the current player white or black
	 */
	public Player currentPlayer() {
		return player;
	}

	/**
	 * Changes the player turn
	 */
	public void changePlayer() {
		this.player = this.player.next();
	}

	/**
	 * @return the number of rows on the grid
	 */
	public int numRows() {
		return 8;
	}

	/**
	 * @return the number of columns on the grid
	 */
	public int numColumns() {
		return 8;
	}

	/**
	 * Method returns a specific chess piece
	 * 
	 * @param row
	 *            the row on the grid
	 * @param column
	 *            the column on the grid
	 * @return the chess piece of type IChessPiece that is at the current row
	 *         and column
	 */
	public IChessPiece pieceAt(int row, int column) {
		return this.board[row][column];
	}
}
