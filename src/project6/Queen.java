/* CHESS <Queen.java>
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

public class Queen extends ChessPiece {

	public String toString() { return "Queen"; }

	public Queen(int x, int y, String token) {
		super(x, y, token);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(int oldX, int oldY, int newX, int newY) {	

		if (newX < 0 || newX > 7 || newY < 0 || newY > 7) return false;
		if (newX == oldX && newY == oldY) return false;

		ArrayList<ChessPiece> p1_Pieces = Main.p1.getAll();
		ArrayList<ChessPiece> p2_Pieces = Main.p2.getAll();
		if(newX - oldX != 0 && newY - oldY != 0){
			if (isPlayerOne) {
				// return false if illegal in ANY case
				// Bishops must move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) == 0 || Math.abs(newY - oldY) == 0)) return false;
				if ((Math.abs(newX - oldX) != Math.abs(newY - oldY))) return false;

				// in case there are collisions...
				for (ChessPiece piece : p1_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
				}
				for (ChessPiece piece : p2_Pieces) {
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						p2_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}

			else {
				// return false if illegal in ANY case
				// Bishops must move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) == 0 || Math.abs(newY - oldY) == 0)) return false;
				if ((Math.abs(newX - oldX) != Math.abs(newY - oldY))) return false;

				// in case there are collisions...
				for (ChessPiece piece : p2_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
				}
				for (ChessPiece piece : p1_Pieces) {
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						p1_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}
			//return false;
		}
		else if ((newX - oldX == 0 && newY - oldY != 0) || (newX - oldX != 0 && newY - oldY == 0)) {
			if (isPlayerOne) {
				// return false if illegal in ANY case
				// Rooks cannot move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) != 0)) return false;

				// in case there are collisions...
				for (ChessPiece piece : p1_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					else if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
				}
				for (ChessPiece piece : p2_Pieces) {
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					else if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						p2_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}

			else {
				// return false if illegal in ANY case
				// Rooks cannot move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) != 0)) return false;

				// in case there are collisions...
				for (ChessPiece piece : p2_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
				}
				for (ChessPiece piece : p1_Pieces) {
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						p1_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isValidMove2(int oldX, int oldY, int newX, int newY) {	

		if (newX < 0 || newX > 7 || newY < 0 || newY > 7) return false;
		if (newX == oldX && newY == oldY) return false;

		ArrayList<ChessPiece> p1_Pieces = Main.p1_temp.getAll();
		ArrayList<ChessPiece> p2_Pieces = Main.p2_temp.getAll();
		if(newX - oldX != 0 && newY - oldY != 0){
			if (isPlayerOne) {
				// return false if illegal in ANY case
				// Bishops must move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) == 0 || Math.abs(newY - oldY) == 0)) return false;
				if ((Math.abs(newX - oldX) != Math.abs(newY - oldY))) return false;

				// in case there are collisions...
				for (ChessPiece piece : p1_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
				}
				for (ChessPiece piece : p2_Pieces) {
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						p2_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}

			else {
				// return false if illegal in ANY case
				// Bishops must move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) == 0 || Math.abs(newY - oldY) == 0)) return false;
				if ((Math.abs(newX - oldX) != Math.abs(newY - oldY))) return false;

				// in case there are collisions...
				for (ChessPiece piece : p2_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
				}
				for (ChessPiece piece : p1_Pieces) {
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						p1_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}
			//return false;
		}
		else if ((newX - oldX == 0 && newY - oldY != 0) || (newX - oldX != 0 && newY - oldY == 0)) {
			if (isPlayerOne) {
				// return false if illegal in ANY case
				// Rooks cannot move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) != 0)) return false;

				// in case there are collisions...
				for (ChessPiece piece : p1_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					else if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
				}
				for (ChessPiece piece : p2_Pieces) {
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					else if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						p2_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}

			else {
				// return false if illegal in ANY case
				// Rooks cannot move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) != 0)) return false;

				// in case there are collisions...
				for (ChessPiece piece : p2_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
				}
				for (ChessPiece piece : p1_Pieces) {
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						p1_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isValidLook(int oldX, int oldY, int newX, int newY) {	

		if (newX < 0 || newX > 7 || newY < 0 || newY > 7) return false;
		if (newX == oldX && newY == oldY) return false;

		ArrayList<ChessPiece> p1_Pieces = Main.p1.getAll();
		ArrayList<ChessPiece> p2_Pieces = Main.p2.getAll();
		if(newX - oldX != 0 && newY - oldY != 0){
			if (isPlayerOne) {
				// return false if illegal in ANY case
				// Bishops must move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) == 0 || Math.abs(newY - oldY) == 0)) return false;
				if ((Math.abs(newX - oldX) != Math.abs(newY - oldY))) return false;

				// in case there are collisions...
				for (ChessPiece piece : p1_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
				}
				for (ChessPiece piece : p2_Pieces) {
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						//p2_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}

			else {
				// return false if illegal in ANY case
				// Bishops must move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) == 0 || Math.abs(newY - oldY) == 0)) return false;
				if ((Math.abs(newX - oldX) != Math.abs(newY - oldY))) return false;

				// in case there are collisions...
				for (ChessPiece piece : p2_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
				}
				for (ChessPiece piece : p1_Pieces) {
					// must check for pieces in-between current and new location
					if (newX < oldX && newY < oldY) { // up left
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == oldX - piece.get_x()) && (piece.get_y() > newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY < oldY) { // up right
						if ((oldY - piece.get_y() > 0) && (oldY - piece.get_y() == piece.get_x() - oldX) && (piece.get_y() > newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (newX < oldX && newY > oldY) { // down left
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == oldX - piece.get_x()) && (piece.get_y() < newY && piece.get_x() > newX)) {
							return false;
						}
					}
					if (newX > oldX && newY > oldY) { // down right
						if ((piece.get_y() - oldY > 0) && (piece.get_y() - oldY == piece.get_x() - oldX) && (piece.get_y() < newY && piece.get_x() < newX)) {
							return false;
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						//p1_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}
			//return false;
		}
		else if ((newX - oldX == 0 && newY - oldY != 0) || (newX - oldX != 0 && newY - oldY == 0)) {
			if (isPlayerOne) {
				// return false if illegal in ANY case
				// Rooks cannot move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) != 0)) return false;

				// in case there are collisions...
				for (ChessPiece piece : p1_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					else if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
				}
				for (ChessPiece piece : p2_Pieces) {
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					else if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						//p2_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}

			else {
				// return false if illegal in ANY case
				// Rooks cannot move horizontally and vertically at the same time
				if ((Math.abs(newX - oldX) != 0 && Math.abs(newY - oldY) != 0)) return false;

				// in case there are collisions...
				for (ChessPiece piece : p2_Pieces) {
					if (this == piece) continue;
					if (piece.get_x() == newX && piece.get_y() == newY) return false;
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
				}
				for (ChessPiece piece : p1_Pieces) {
					// must check for pieces in-between current and new location
					if (Math.abs(newX - oldX) != 0) {
						if (newX > oldX) {
							if ((piece.get_x() < newX) && (piece.get_x() > oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
						else {
							if ((piece.get_x() > newX) && (piece.get_x() < oldX) && (piece.get_y() == oldY)) {
								return false;
							}
						}
					}
					if (Math.abs(newY - oldY) != 0){
						if (newY > oldY) {
							if ((piece.get_y() < newY) && (piece.get_y() > oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
						else {
							if ((piece.get_y() > newY) && (piece.get_y() < oldY) && (piece.get_x() == oldX)) {
								return false;
							}
						}
					}
					if (piece.get_x() == newX && piece.get_y() == newY) {
						//p1_Pieces.remove(piece);
						return true;
					}
				}
				// has checked for all possible illegal moves and collisions, therefore valid move
				return true;
			}
		}
		return false;
	}
}
