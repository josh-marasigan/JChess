package project6;

import static org.junit.Assert.*;

import org.junit.Test;

public class project6Test {

	// Turning the String input for coordinates to usable grid coordinates check
	@Test
	public void testTurnTimeStep_StringToCoordLogic() {
		// Player tester = new Player(true, false);
		String oldCoord = "A1";
		char[] oldArray = oldCoord.toCharArray();
		int oldX = oldArray[0] - 65;
		assertEquals(0, oldX);
		int oldY = oldArray[1] - 49;
		assertEquals(0, oldY);	
	}

	// Creating a new piece and checking if it is created correctly in the right coords
	@Test
	public void testCreation() {
		Player testPlayer = new Player(true, false);
		ChessPiece obj = new Pawn(0, 6, "Pawn");
		testPlayer.totalPieces.add(obj);
		assertEquals(0, obj.get_x());
		assertEquals(6, obj.get_y());
	}
	
}
