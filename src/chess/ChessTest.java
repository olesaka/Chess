package chess;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for the functionality of the chess game
 * 
 * @author Andrew Olesak
 * @version March 12, 2016
 */
public class ChessTest {
	private ChessModel model;

	/**
	 * Constructor sets an instance variable to use with the ChessModel class
	 */
	public ChessTest() {
		this.model = new ChessModel();
	}

	@Test
	public void testPawns() {
		Move m = new Move(1, 3, 3, 3);
		assertTrue("Should be true", model.isValidMove(m));
		// second move doesn't allow two spaces ahead
		assertFalse("Should be false", model.isValidMove(m));
		m = new Move(1, 6, 2, 6);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(6, 4, 5, 3);
		assertFalse("Should be false", model.isValidMove(m));
		m = new Move(1, 1, 5, 4);
		model.move(m);
		m = new Move(6, 3, 5, 4);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(6, 4, 4, 4);
		assertFalse("Should be false", model.isValidMove(m));
		m = new Move(6, 6, 2, 5);
		model.move(m);
		m = new Move(1, 4, 2, 5);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(1, 5, 3, 5);
		assertFalse("Should be false", model.isValidMove(m));
		m = new Move(1, 0, 4, 0);
		model.move(m);
		m = new Move(6, 0, 4, 0);
		assertFalse("Should be false", model.isValidMove(m));
		m = new Move(6, 7, 5, 5);
		assertFalse("Should be false", model.isValidMove(m));
	}

	@Test
	public void testKnights() {
		Move m = new Move(7, 1, 5, 2);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(7, 6, 5, 6);
		assertFalse("Should be false", model.isValidMove(m));
		m = new Move(0, 1, 2, 2);
		assertTrue("Should be true", model.isValidMove(m));
		model.move(m);
		m = new Move(7, 1, 5, 0);
		model.move(m);
		m = new Move(2, 2, 3, 0);
		assertTrue("Should be true", model.isValidMove(m));
		model.move(m);
		m = new Move(5, 0, 4, 2);
		model.move(m);
		m = new Move(3, 0, 4, 2);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(0, 6, 1, 4);
		assertFalse("Shoudl be false", model.isValidMove(m));
	}

	@Test
	public void testRooks() {
		Move m = new Move(7, 0, 4, 0);
		assertFalse("Should be false", model.isValidMove(m));
		m = new Move(6, 7, 5, 5);
		model.move(m);
		m = new Move(7, 7, 3, 7);
		assertTrue("Shoudld be true", model.isValidMove(m));
		model.move(m);
		m = new Move(3, 7, 3, 3);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(1, 0, 2, 2);
		model.move(m);
		m = new Move(0, 0, 0, 2);
		assertFalse("Should be false", model.isValidMove(m));
		m = new Move(0, 0, 3, 0);
		assertTrue("Should be true", model.isValidMove(m));
		model.move(m);
		m = new Move(3, 0, 3, 2);
		model.move(m);
		m = new Move(3, 2, 6, 2);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(3, 2, 7, 2);
		assertFalse("Should be false", model.isValidMove(m));
	}

	@Test
	public void testKings() {
		Move m = new Move(7, 4, 6, 4);
		assertFalse("Should be false", model.isValidMove(m));
		m = new Move(6, 4, 5, 3);
		model.move(m);
		m = new Move(7, 4, 6, 4);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(1, 5, 2, 6);
		model.move(m);
		m = new Move(5, 3, 1, 5);
		model.move(m);
		m = new Move(0, 4, 1, 5);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(1, 5, 1, 6);
		assertFalse("Should be false", model.isValidMove(m));
	}

	@Test
	public void testBishops() {
		Move m = new Move(7, 2, 5, 4);
		assertFalse("Should be flase", model.isValidMove(m));
		m = new Move(6, 3, 6, 0);
		model.move(m);
		m = new Move(7, 2, 5, 4);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(1, 2, 1, 0);
		model.move(m);
		m = new Move(1, 3, 1, 1);
		model.move(m);
		m = new Move(0, 2, 3, 3);
		assertFalse("Should be false", model.isValidMove(m));
		m = new Move(0, 2, 4, 6);
		assertTrue("Should be true", model.isValidMove(m));
		model.move(m);
		m = new Move(4, 6, 6, 4);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(6, 0, 5, 4);
		model.move(m);
		m = new Move(7, 2, 4, 5);
		assertFalse("Should be False", model.isValidMove(m));
	}

	@Test
	public void testQueen() {
		Move m = new Move(6, 3, 5, 0);
		model.move(m);
		m = new Move(7, 3, 3, 3);
		assertTrue("Should be True", model.isValidMove(m));
		model.move(m);
		m = new Move(3, 3, 1, 5);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(3, 3, 1, 3);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(1, 3, 2, 0);
		model.move(m);
		m = new Move(0, 3, 2, 3);
		assertTrue("Should be true", model.isValidMove(m));
		model.move(m);
		m = new Move(6, 7, 4, 5);
		model.move(m);
		m = new Move(2, 3, 5, 6);
		assertFalse("Should be false", model.isValidMove(m));
		m = new Move(2, 3, 4, 5);
		assertTrue("Should be true", model.isValidMove(m));
		m = new Move(3, 3, 3, 0);
		model.move(m);
		m = new Move(3, 0, 2, 0);
		assertTrue("Should be true", model.isValidMove(m));
	}

	@Test
	public void testInCheck() {
		Move m = new Move(1, 5, 2, 6);
		model.move(m);
		m = new Move(7, 5, 3, 7);
		model.move(m);
		assertFalse("Should be false", model.inCheck(Player.BLACK));
		m = new Move(2, 6, 2, 7);
		model.move(m);
		assertTrue("should be true", model.inCheck(Player.BLACK));
		m = new Move(7, 4, 2, 0);
		model.move(m);
		assertTrue("Should be true", model.inCheck(Player.WHITE));
		m = new Move(3, 7, 7, 5);
		model.move(m);
		m = new Move(2, 0, 3, 4);
		model.move(m);
		m = new Move(0, 0, 2, 2);
		model.move(m);
		m = new Move(0, 7, 4, 7);
		model.move(m);
		m = new Move(0, 2, 4, 2);
		model.move(m);
		m = new Move(0, 6, 2, 7);
		model.move(m);
		assertFalse("Should be false", model.inCheck(Player.WHITE));
	}

	@Test
	public void testIsComplete() {
		Move m = new Move(1, 4, 2, 0);
		model.move(m);
		m = new Move(1, 5, 3, 0);
		model.move(m);
		m = new Move(7, 3, 4, 4);
		model.move(m);
		m = new Move(7, 5, 3, 7);
		model.move(m);
		model.changePlayer();
		assertTrue("Should be true", model.isComplete());
		m = new Move(6, 3, 2, 1);
		model.move(m);
		m = new Move(7, 4, 6, 3);
		model.move(m);
		m = new Move(0, 3, 3, 3);
		model.move(m);
		m = new Move(0, 1, 4, 0);
		model.move(m);
		model.changePlayer();
		assertFalse("Should be false", model.isComplete());
		m = new Move(0, 5, 2, 7);
		model.move(m);
		assertFalse("Should be false", model.isComplete());
		m = new Move(6, 7, 7, 4);
		model.move(m);
		assertFalse("Should be false", model.isComplete());
		m = new Move(4, 4, 5, 7);
		model.move(m);
		assertTrue("Should be true", model.isComplete());
	}

}
