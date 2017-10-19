package chess;

import javax.swing.JFrame;

/**
 * Class contains the main method that creates and displays the chess game GUI
 * 
 * @author Andrew Olesak
 * @version February 23, 2016
 */
public class ChessGUI extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Chess Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ChessPanel panel = new ChessPanel();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
