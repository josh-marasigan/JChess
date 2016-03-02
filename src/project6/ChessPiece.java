/* CHESS <ChessPiece.java>
 * EE422C Project 6 submission by
 * Josh Marasigan
 * jvm555
 * 16350
 * Slip days used: <3> 
 * Stephen Tran
 * set896
 * 16340
 * Slip days used: <2> 
 * Fall 2015
 */
package project6;

import javafx.scene.image.Image;

/* -------------------------------- */
/* [MODEL COMPONENT: PIECE METHODS] */
/* -------------------------------- */
public class ChessPiece {
	// Local Fields
	public boolean isPlayerOne;
	public boolean isPlayerTwo;
	public int row;
	public int col;
	public String token;
	public String wat;
	
	// Constructor
	public ChessPiece(int x, int y, String type) {

		this.col = x;
		this.row = y;
		this.token = type;
	}
	
	// Getters
	public int get_x() {
		return this.col;
	}	
	
	public int get_y() {
		return this.row;
	}	
	
	public String toString() { return ""; }
	
	// Retrieve Image for the Piece
	public Image getImage() {
		// Private fields.
		Image returnToken = null;
		
		if(isPlayerOne) {
			// Cases to find which piece gets the P1 Tokens.
			switch(token) {
				case "Pawn":
					returnToken = new Image("file:WhitePawn.png");
					break;
				case "Rook":
					returnToken = new Image("file:WhiteRook.png");
					break;
				case "Bishop":
					returnToken = new Image("file:WhiteBishop.png");
					break;
				case "Knight":
					returnToken = new Image("file:WhiteKnight.png");
					break;
				case "King":
					returnToken = new Image("file:WhiteKing.png");
					break;
				case "Queen":
					returnToken = new Image("file:WhiteQueen.png");
					break;
				default:
					returnToken = null;
			}
		}
		else {
			// Cases to find which piece gets the P2 Tokens.
			switch(token) {
				case "Pawn":
					returnToken = new Image("file:BlackPawn.png");
					break;
				case "Rook":
					returnToken = new Image("file:BlackRook.png");
					break;
				case "Bishop":
					returnToken = new Image("file:BlackBishop.png");
					break;
				case "Knight":
					returnToken = new Image("file:BlackKnight.png");
					break;
				case "King":
					returnToken = new Image("file:BlackKing.png");
					break;
				case "Queen":
					returnToken = new Image("file:BlackQueen.png");
					break;
				default:
					returnToken = null;
			}
		}
		return returnToken;
	}
	
	public boolean isValidMove(int oldX, int oldY, int newX, int newY) {
		return false;
	}
	
	public boolean isValidMove2(int oldX, int oldY, int newX, int newY) {
		return false;
	}
	
	public boolean isValidLook(int oldX, int oldY, int newX, int newY) {
		return false;
	}
}
