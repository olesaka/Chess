package chess;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Class is responsible for creating the graphical user interface, responding to
 * user actions, and updating the board.
 * 
 * @author Andrew Olesak
 * @version February 23, 2016
 */
public class ChessPanel extends JPanel {

	private JButton[][] board;
	private ChessModel model;
	private ChessPiece piece;
	private boolean firstClick;
	private int[] moves;
	private Icon[] iconPiece;

	/**
	 * Constructor sets the instance variables and creates the starter board
	 */
	public ChessPanel() {

		this.board = new JButton[8][8];
		this.model = new ChessModel();
		this.piece = null;
		this.firstClick = true;
		this.moves = new int[4];
		this.createIcons();

		// creates a layout for the board
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridBagLayout());
		GridBagConstraints place = new GridBagConstraints();
		place.gridheight = 8;
		place.gridwidth = 8;
		place = new GridBagConstraints();

		// create the checker board
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if ((row + col) % 2 == 0) {
					this.board[row][col] = new JButton();
					this.board[row][col].setBackground(Color.WHITE);
				} else {
					this.board[row][col] = new JButton();
					this.board[row][col].setBackground(Color.GRAY);
				}
				place.gridy = row;
				place.gridx = col;
				this.board[row][col].addActionListener(new ButtonListener());
				this.board[row][col].setBorder(BorderFactory.createEmptyBorder());
				boardPanel.add(this.board[row][col], place);
			}
		}

		// Set the initial board
		displayBoard();
		add(boardPanel);
	}

	/**
	 * Helper method that creates the icons
	 */
	private void createIcons() {
		this.iconPiece = new Icon[13];
		this.iconPiece[0] = new ImageIcon("BRook.png");
		this.iconPiece[1] = new ImageIcon("BKnight.png");
		this.iconPiece[2] = new ImageIcon("BBishop.png");
		this.iconPiece[3] = new ImageIcon("BQueen.png");
		this.iconPiece[4] = new ImageIcon("BKing.png");
		this.iconPiece[5] = new ImageIcon("BPawn.png");
		this.iconPiece[6] = new ImageIcon("WPawn.png");
		this.iconPiece[7] = new ImageIcon("WRook.png");
		this.iconPiece[8] = new ImageIcon("WKnight.png");
		this.iconPiece[9] = new ImageIcon("WBishop.png");
		this.iconPiece[10] = new ImageIcon("WQueen.png");
		this.iconPiece[11] = new ImageIcon("WKing.png");
		this.iconPiece[12] = new ImageIcon("Blank.png");
	}

	/**
	 * Helper method that displays the current board
	 */
	public void displayBoard() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				piece = (ChessPiece) model.pieceAt(row, col);
				if (piece != null) {
					if (piece.player() == Player.BLACK) {
						if (piece.type() == "Pawn") {
							this.board[row][col].setIcon(this.iconPiece[5]);
						}
						if (piece.type() == "Rook") {
							this.board[row][col].setIcon(this.iconPiece[0]);
						}
						if (piece.type() == "Knight") {
							this.board[row][col].setIcon(this.iconPiece[1]);
						}
						if (piece.type() == "Bishop") {
							this.board[row][col].setIcon(this.iconPiece[2]);
						}
						if (piece.type() == "Queen") {
							this.board[row][col].setIcon(this.iconPiece[3]);
						}
						if (piece.type() == "King") {
							this.board[row][col].setIcon(this.iconPiece[4]);
						}
					} else {
						if (piece.player() == Player.WHITE) {
							if (piece.type() == "Pawn") {
								this.board[row][col].setIcon(this.iconPiece[6]);
							}
							if (piece.type() == "Rook") {
								this.board[row][col].setIcon(this.iconPiece[7]);
							}
							if (piece.type() == "Knight") {
								this.board[row][col].setIcon(this.iconPiece[8]);
							}
							if (piece.type() == "Bishop") {
								this.board[row][col].setIcon(this.iconPiece[9]);
							}
							if (piece.type() == "Queen") {
								this.board[row][col].setIcon(this.iconPiece[10]);
							}
							if (piece.type() == "King") {
								this.board[row][col].setIcon(this.iconPiece[11]);
							}
						}
					}
				} else {
					this.board[row][col].setIcon(this.iconPiece[12]);
				}
			}
		}
	}

	/**
	 * Resets the board to create a new game
	 */
	private void resetBoard() {
		model = model.reset();
		this.displayBoard();
		this.firstClick = true;
	}

	/**
	 * Class reacts to buttons being pressed and changes the board accordingly
	 * 
	 * @author Andrew Olesak
	 * @version March 12, 2016
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * Reacts to the buttons on the board being pressed
		 */
		public void actionPerformed(ActionEvent e) {

			if (!firstClick) {
				for (int row = 0; row < 8; row++) {
					for (int col = 0; col < 8; col++) {
						if (e.getSource() == board[row][col]) {
							moves[2] = row;
							moves[3] = col;
							Move m = new Move(moves[0], moves[1], moves[2], moves[3]);
							if (model.isValidMove(m)) {
								model.move(m);
								piece = (ChessPiece) model.pieceAt(row, col);
								if (piece != null && model.currentPlayer() == Player.BLACK && piece.type() == "King") {
									if (model.inCheck(model.currentPlayer())) {
										m = new Move(moves[2], moves[3], moves[0], moves[1]);
										model.move(m);
										return;
									}
								}
								if (piece != null && model.currentPlayer() == Player.WHITE && piece.type() == "King") {
									if (model.inCheck(model.currentPlayer())) {
										m = new Move(moves[2], moves[3], moves[0], moves[1]);
										model.move(m);
										return;
									}
								}
								displayBoard();
								if (model.currentPlayer() == Player.BLACK) {
									if (model.inCheck(model.currentPlayer())) {
										JOptionPane.showMessageDialog(null, "Check Mate! White Wins!");
										resetBoard();
										return;
									}
								}
								if (model.currentPlayer() == Player.WHITE) {
									if (model.inCheck(model.currentPlayer())) {
										JOptionPane.showMessageDialog(null, "Check Mate! Black Wins!");
										resetBoard();
										return;
									}
								}
								model.changePlayer();
								if (model.isComplete() && model.currentPlayer() == Player.BLACK) {
									JOptionPane.showMessageDialog(null, "Check Mate! White Wins!");
									resetBoard();
									return;
								}
								if (model.isComplete() && model.currentPlayer() == Player.WHITE) {
									JOptionPane.showMessageDialog(null, "Check Mate! Black Wins!");
									resetBoard();
									return;
								}
								if (model.inCheck(model.currentPlayer()) && model.currentPlayer() == Player.BLACK) {
									JOptionPane.showMessageDialog(null, "Black is now in check");
								}
								if (model.inCheck(model.currentPlayer()) && model.currentPlayer() == Player.WHITE) {
									JOptionPane.showMessageDialog(null, "White is now in check");
								}
							}
							firstClick = true;
							return;

						}
					}
				}
			} else {
				for (int row = 0; row < 8; row++) {
					for (int col = 0; col < 8; col++) {
						if (e.getSource() == board[row][col]) {
							piece = (ChessPiece) model.pieceAt(row, col);
							if (piece == null || piece.player() != model.currentPlayer()) {
								return;
							} else {
								moves[0] = row;
								moves[1] = col;
								firstClick = false;
								return;
							}
						}
					}
				}
			}
		}
	}
}
