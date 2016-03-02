/* CHESS <Knight.java>
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

import java.util.ArrayList;

public class Knight extends ChessPiece {

	public String toString() { return "Knight"; }
	
	public Knight(int x, int y, String token) {
		super(x, y, token);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isValidMove(int oldX, int oldY, int newX, int newY) {
		
		if (newX < 0 || newX > 7 || newY < 0 || newY > 7) return false;
		if (newX == oldX && newY == oldY) return false;
		
		ArrayList<ChessPiece> p1_Pieces = Main.p1.getAll();
		ArrayList<ChessPiece> p2_Pieces = Main.p2.getAll();
		
		if(isPlayerOne) {
			
			// Check if movement within bounds of Knight.	
			if((Math.abs(newX - oldX) == 1) && (Math.abs(newY - oldY) == 2) 
					|| (Math.abs(newX - oldX) == 2) && (Math.abs(newY - oldY) == 1)) {
				
				// Collision Handling
				for (ChessPiece piece : p1_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) { return false; }
				}
				for (ChessPiece piece : p2_Pieces) {
					if (piece.get_x() == newX && piece.get_y() == newY) { 
						p2_Pieces.remove(piece);
						break;
					}
				}
				return true;
			}
			return false;
		}
		else {
			
			// Check if movement within bounds of Knight.	
			if((Math.abs(newX - oldX) == 1) && (Math.abs(newY - oldY) == 2) 
					|| (Math.abs(newX - oldX) == 2) && (Math.abs(newY - oldY) == 1)) {
				
				// Collision Handling
				for (ChessPiece piece : p2_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) { return false; }
				}
				for (ChessPiece piece : p1_Pieces) {
					if (piece.get_x() == newX && piece.get_y() == newY) { 
						p1_Pieces.remove(piece);
						break;
					}
				}
				return true;
			}
			return false;
		}
		
	}
	
	@Override
	public boolean isValidMove2(int oldX, int oldY, int newX, int newY) {
		
		if (newX < 0 || newX > 7 || newY < 0 || newY > 7) return false;
		if (newX == oldX && newY == oldY) return false;
		
		ArrayList<ChessPiece> p1_Pieces = Main.p1_temp.getAll();
		ArrayList<ChessPiece> p2_Pieces = Main.p2_temp.getAll();
		
		if(isPlayerOne) {
			
			// Check if movement within bounds of Knight.	
			if((Math.abs(newX - oldX) == 1) && (Math.abs(newY - oldY) == 2) 
					|| (Math.abs(newX - oldX) == 2) && (Math.abs(newY - oldY) == 1)) {
				
				// Collision Handling
				for (ChessPiece piece : p1_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) { return false; }
				}
				for (ChessPiece piece : p2_Pieces) {
					if (piece.get_x() == newX && piece.get_y() == newY) { 
						p2_Pieces.remove(piece);
						break;
					}
				}
				return true;
			}
			return false;
		}
		else {
			
			// Check if movement within bounds of Knight.	
			if((Math.abs(newX - oldX) == 1) && (Math.abs(newY - oldY) == 2) 
					|| (Math.abs(newX - oldX) == 2) && (Math.abs(newY - oldY) == 1)) {
				
				// Collision Handling
				for (ChessPiece piece : p2_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) { return false; }
				}
				for (ChessPiece piece : p1_Pieces) {
					if (piece.get_x() == newX && piece.get_y() == newY) { 
						p1_Pieces.remove(piece);
						break;
					}
				}
				return true;
			}
			return false;
		}
		
	}
	
	@Override
	public boolean isValidLook(int oldX, int oldY, int newX, int newY) {
		
		if (newX < 0 || newX > 7 || newY < 0 || newY > 7) return false;
		if (newX == oldX && newY == oldY) return false;
		
		ArrayList<ChessPiece> p1_Pieces = Main.p1.getAll();
		ArrayList<ChessPiece> p2_Pieces = Main.p2.getAll();
		
		if(isPlayerOne) {
			
			// Check if movement within bounds of Knight.	
			if((Math.abs(newX - oldX) == 1) && (Math.abs(newY - oldY) == 2) 
					|| (Math.abs(newX - oldX) == 2) && (Math.abs(newY - oldY) == 1)) {
				
				// Collision Handling
				for (ChessPiece piece : p1_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) { return false; }
				}
				for (ChessPiece piece : p2_Pieces) {
					if (piece.get_x() == newX && piece.get_y() == newY) { 
						//p2_Pieces.remove(piece);
						break;
					}
				}
				return true;
			}
			return false;
		}
		else {
			
			// Check if movement within bounds of Knight.	
			if((Math.abs(newX - oldX) == 1) && (Math.abs(newY - oldY) == 2) 
					|| (Math.abs(newX - oldX) == 2) && (Math.abs(newY - oldY) == 1)) {
				
				// Collision Handling
				for (ChessPiece piece : p2_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) { return false; }
				}
				for (ChessPiece piece : p1_Pieces) {
					if (piece.get_x() == newX && piece.get_y() == newY) { 
						//p1_Pieces.remove(piece);
						break;
					}
				}
				return true;
			}
			return false;
		}
		
	}
}
