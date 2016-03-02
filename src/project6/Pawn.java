/* CHESS <Pawn.java>
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

public class Pawn extends ChessPiece {

	public String toString() {
		return "Pawn";
	}

	public Pawn(int x, int y, String token) {
		super(x, y, token);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(int oldX, int oldY, int newX, int newY) {

		if (newX < 0 || newX > 7 || newY < 0 || newY > 7)
			return false;
		if (newX == oldX && newY == oldY)
			return false;

		ArrayList<ChessPiece> p1_Pieces = Main.p1.getAll();
		ArrayList<ChessPiece> p2_Pieces = Main.p2.getAll();

		if (isPlayerOne) {
			// return false if illegal in ANY case
			if ((Math.abs(newX - oldX) > 1))
				return false;
			// p1 must move pawns forward (so to a lesser y coordinate)
			if (newY >= oldY)
				return false;
			// Pawns cannot move more than 2 spaces EVER
			if ((Math.abs(newY - oldY) > 2))
				return false;
			// Pawns can only move forward twice when it is making its first
			// move
			if ((Math.abs(newY - oldY) == 2 && oldY != 6))
				return false;
			// Pawns cannot move horizontally and forward twice at the same time
			if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) == 2))
				return false;

			// in case there are collisions...
			for (ChessPiece piece : p1_Pieces) {
				if (this == piece)
					continue;
				if (piece.get_x() == newX && piece.get_y() == newY)
					return false;
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() + 1 == oldY && piece.get_x() == oldX)
					return false;
			}
			for (ChessPiece piece : p2_Pieces) {
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() + 1 == oldY && piece.get_x() == oldX)
					return false;
				if (piece.get_x() == newX && piece.get_y() == newY) {
					// Pawns cannot take enemy pieces in front of it (must move
					// diagonally)
					if (oldX == newX)
						return false;
					else {
						p2_Pieces.remove(piece);
						return true;
					}
				}
			}
			// has checked for all possible illegal moves and collisions,
			// therefore valid move
			return true;
		}

		else {
			// return false if illegal in ANY case
			if ((Math.abs(newX - oldX) > 1))
				return false;
			// p2 must move pawns forward (so to a greater y coordinate)
			if (oldY >= newY)
				return false;
			// Pawns cannot move more than 2 spaces EVER
			if ((Math.abs(newY - oldY) > 2))
				return false;
			// Pawns can only move forward twice when it is making its first
			// move
			if ((Math.abs(newY - oldY) == 2 && oldY != 1))
				return false;
			// Pawns cannot move horizontally and forward twice at the same time
			if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) == 2))
				return false;

			// in case there are collisions...
			for (ChessPiece piece : p2_Pieces) {
				if (this == piece)
					continue;
				if (piece.get_x() == newX && piece.get_y() == newY)
					return false;
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() - 1 == oldY && piece.get_x() == oldX)
					return false;
			}
			for (ChessPiece piece : p1_Pieces) {
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() - 1 == oldY && piece.get_x() == oldX)
					return false;
				if (piece.get_x() == newX && piece.get_y() == newY) {
					// Pawns cannot take enemy pieces in front of it (must move
					// diagonally)
					if (oldX == newX)
						return false;
					else {
						p1_Pieces.remove(piece);
						return true;
					}
				}
			}
			// has checked for all possible illegal moves and collisions,
			// therefore valid move
			return true;
		}
		// return false;
	}

	@Override
	public boolean isValidMove2(int oldX, int oldY, int newX, int newY) {

		if (newX < 0 || newX > 7 || newY < 0 || newY > 7)
			return false;
		if (newX == oldX && newY == oldY)
			return false;

		ArrayList<ChessPiece> p1_Pieces = Main.p1_temp.getAll();
		ArrayList<ChessPiece> p2_Pieces = Main.p2_temp.getAll();

		if (isPlayerOne) {
			// return false if illegal in ANY case
			if ((Math.abs(newX - oldX) > 1))
				return false;
			// p1 must move pawns forward (so to a lesser y coordinate)
			if (newY >= oldY)
				return false;
			// Pawns cannot move more than 2 spaces EVER
			if ((Math.abs(newY - oldY) > 2))
				return false;
			// Pawns can only move forward twice when it is making its first
			// move
			if ((Math.abs(newY - oldY) == 2 && oldY != 6))
				return false;
			// Pawns cannot move horizontally and forward twice at the same time
			if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) == 2))
				return false;

			// in case there are collisions...
			for (ChessPiece piece : p1_Pieces) {
				if (this == piece)
					continue;
				if (piece.get_x() == newX && piece.get_y() == newY)
					return false;
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() + 1 == oldY && piece.get_x() == oldX)
					return false;
			}
			for (ChessPiece piece : p2_Pieces) {
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() + 1 == oldY && piece.get_x() == oldX)
					return false;
				if (piece.get_x() == newX && piece.get_y() == newY) {
					// Pawns cannot take enemy pieces in front of it (must move
					// diagonally)
					if (oldX == newX)
						return false;
					else {
						p2_Pieces.remove(piece);
						return true;
					}
				}
			}
			// has checked for all possible illegal moves and collisions,
			// therefore valid move
			return true;
		}

		else {
			// return false if illegal in ANY case
			if ((Math.abs(newX - oldX) > 1))
				return false;
			// p2 must move pawns forward (so to a greater y coordinate)
			if (oldY >= newY)
				return false;
			// Pawns cannot move more than 2 spaces EVER
			if ((Math.abs(newY - oldY) > 2))
				return false;
			// Pawns can only move forward twice when it is making its first
			// move
			if ((Math.abs(newY - oldY) == 2 && oldY != 1))
				return false;
			// Pawns cannot move horizontally and forward twice at the same time
			if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) == 2))
				return false;

			// in case there are collisions...
			for (ChessPiece piece : p2_Pieces) {
				if (this == piece)
					continue;
				if (piece.get_x() == newX && piece.get_y() == newY)
					return false;
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() - 1 == oldY && piece.get_x() == oldX)
					return false;
			}
			for (ChessPiece piece : p1_Pieces) {
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() - 1 == oldY && piece.get_x() == oldX)
					return false;
				if (piece.get_x() == newX && piece.get_y() == newY) {
					// Pawns cannot take enemy pieces in front of it (must move
					// diagonally)
					if (oldX == newX)
						return false;
					else {
						p1_Pieces.remove(piece);
						return true;
					}
				}
			}
			// has checked for all possible illegal moves and collisions,
			// therefore valid move
			return true;
		}
		// return false;
	}
	
	@Override
	public boolean isValidLook(int oldX, int oldY, int newX, int newY) {

		if (newX < 0 || newX > 7 || newY < 0 || newY > 7)
			return false;
		if (newX == oldX && newY == oldY)
			return false;

		ArrayList<ChessPiece> p1_Pieces = Main.p1.getAll();
		ArrayList<ChessPiece> p2_Pieces = Main.p2.getAll();

		if (isPlayerOne) {
			// return false if illegal in ANY case
			if ((Math.abs(newX - oldX) > 1))
				return false;
			// p1 must move pawns forward (so to a lesser y coordinate)
			if (newY >= oldY)
				return false;
			// Pawns cannot move more than 2 spaces EVER
			if ((Math.abs(newY - oldY) > 2))
				return false;
			// Pawns can only move forward twice when it is making its first
			// move
			if ((Math.abs(newY - oldY) == 2 && oldY != 6))
				return false;
			// Pawns cannot move horizontally and forward twice at the same time
			if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) == 2))
				return false;

			// in case there are collisions...
			for (ChessPiece piece : p1_Pieces) {
				if (this == piece)
					continue;
				if (piece.get_x() == newX && piece.get_y() == newY)
					return false;
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() + 1 == oldY && piece.get_x() == oldX)
					return false;
			}
			for (ChessPiece piece : p2_Pieces) {
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() + 1 == oldY && piece.get_x() == oldX)
					return false;
				if (piece.get_x() == newX && piece.get_y() == newY) {
					// Pawns cannot take enemy pieces in front of it (must move
					// diagonally)
					if (oldX == newX)
						return false;
					else {
						// p2_Pieces.remove(piece);
						return true;
					}
				}
			}
			// has checked for all possible illegal moves and collisions,
			// therefore valid move
			return true;
		}

		else {
			// return false if illegal in ANY case
			if ((Math.abs(newX - oldX) > 1))
				return false;
			// p2 must move pawns forward (so to a greater y coordinate)
			if (oldY >= newY)
				return false;
			// Pawns cannot move more than 2 spaces EVER
			if ((Math.abs(newY - oldY) > 2))
				return false;
			// Pawns can only move forward twice when it is making its first
			// move
			if ((Math.abs(newY - oldY) == 2 && oldY != 1))
				return false;
			// Pawns cannot move horizontally and forward twice at the same time
			if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) == 2))
				return false;

			// in case there are collisions...
			for (ChessPiece piece : p2_Pieces) {
				if (this == piece)
					continue;
				if (piece.get_x() == newX && piece.get_y() == newY)
					return false;
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() - 1 == oldY && piece.get_x() == oldX)
					return false;
			}
			for (ChessPiece piece : p1_Pieces) {
				if ((Math.abs(newY - oldY)) == 2 && piece.get_y() - 1 == oldY && piece.get_x() == oldX)
					return false;
				if (piece.get_x() == newX && piece.get_y() == newY) {
					// Pawns cannot take enemy pieces in front of it (must move
					// diagonally)
					if (oldX == newX)
						return false;
					else {
						// p1_Pieces.remove(piece);
						return true;
					}
				}
			}
			// has checked for all possible illegal moves and collisions,
			// therefore valid move
			return true;
		}
		// return false;
	}
}
