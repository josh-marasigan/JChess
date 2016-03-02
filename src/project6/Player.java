/* CHESS <Player.java>
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

/* --------------------------------- */
/* [MODEL COMPONENT: PLAYER METHODS] */
/* --------------------------------- */
public class Player {

	// Local Fields for Player class.
	public boolean isPlayer1;
	public boolean isPlayer2;
	public boolean isComputer;
	public boolean isInCheck;
	
	// Constructor for class Player
	public Player(boolean isPlayerOne, boolean Computer) {

		// Establish which side the player plays, and if it's a computer.
		this.isPlayer1 = isPlayerOne;
		this.isPlayer2 = !isPlayerOne;
		this.isComputer = Computer;
		this.initPieces();
		this.isInCheck  = false;
	}

	// Initialize amount of pieces player has.
	public ArrayList<ChessPiece> totalPieces = new ArrayList<ChessPiece>();
	
	public void setAllNull() {
		totalPieces = null;
	}
	
	// Getters
	public ArrayList<ChessPiece> getAll() {
		return this.totalPieces;
	}
	
	public void setAll(ArrayList<ChessPiece> temp){
		totalPieces = temp;
	}
	
	// Initialize actual pieces. Create Objects.
	public void initPieces() {
		int row_val;
		int row_val2;

		// Initialize pieces on Bottom if player 1.
		if(!isPlayer1) {
			row_val = 1;
			row_val2 = 0;
		}
		else {
			row_val = 6;
			row_val2 = 7;
		}

		// Make Pawns
		for (int i = 0; i < 8; ++i) {
			totalPieces.add(new Pawn(i, row_val, "Pawn"));
		}

		// Make Rooks
		totalPieces.add(new Rook(0, row_val2, "Rook"));
		totalPieces.add(new Rook(7, row_val2, "Rook"));

		// Make Bishops
		totalPieces.add(new Bishop(2, row_val2, "Bishop"));
		totalPieces.add(new Bishop(5, row_val2, "Bishop"));

		// Make Knights
		totalPieces.add(new Knight(1, row_val2, "Knight"));
		totalPieces.add(new Knight(6, row_val2, "Knight"));

		// Make Queen/King
		totalPieces.add(new Queen(3, row_val2, "Queen"));
		totalPieces.add(new King(4, row_val2, "King"));

		// Establish which Player has which piece
		for (ChessPiece piece: totalPieces) {
			piece.isPlayerOne = this.isPlayer1;
			piece.isPlayerTwo = this.isPlayer2;
		}
	}

	/* --------------------------------------------------- */
	/* [MODEL COMPONENT: DICTATES THE ACTIONS IN ONE TURN] */
	/* --------------------------------------------------- */
	public void turnTimeStep(String token, String oldCoord, String newCoord) throws Exception {
		/* ASSUMING oldCoord IS ALREADY PARSED TO BE SOMETHING LIKE A1 */
		// make all capitals
		oldCoord = oldCoord.toUpperCase();
		newCoord = newCoord.toUpperCase();

		char[] oldArray = oldCoord.toCharArray();
		char[] newArray = newCoord.toCharArray();
		int oldX = oldArray[0] - 65;
		int oldY = oldArray[1] - 49;
		int newX = newArray[0] - 65;
		int newY = newArray[1] - 49;

		// 1. Allow player to select piece and move it.
		if (isPlayer1) {
			ArrayList<ChessPiece> pieceList = Main.p1.getAll();
			switch(token.toUpperCase()) {
			case "PAWN":
				boolean isPiece = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "Pawn");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							isPiece = true;
							piece.col = newX;
							piece.row = newY;

							if (piece.row == 0) {
								// pawn becomes queen
								pieceList.remove(piece);
								ChessPiece Q = new Queen(newX, 0, "Queen");
								Q.isPlayerOne = true;
								pieceList.add(Q);
							}
						}
						else {
							throw new Exception();
						}
						break;
					}
				}
				
				if (!isPiece) {
					throw new Exception();
				}
				break;
			case "ROOK":
				boolean isPiecer = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "Rook");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isPiecer = true;
						}
						else {
							throw new Exception();
						}
						break;
					}
				}
				
				if (!isPiecer) {
					throw new Exception();
				}
				break;
			case "BISHOP":
				boolean isPieceb = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "Bishop");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isPieceb = true;
						}
						else {
							throw new Exception();
						}
						break;
					}
				}
				if (!isPieceb) {
					throw new Exception();
				}
				break;
			case "KNIGHT":
				boolean isPiecek = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "Knight");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isPiecek = true;
						}
						else {
							throw new Exception();
						}

						break;
					}
				}
				if (!isPiecek) {
					throw new Exception();
				}
				break;
			case "KING":
				boolean isPieceg = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "King");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isPieceg = true;
						}
						else {
							throw new Exception();
						}
						
						break;
					}
				}
				if (!isPieceg) {
					throw new Exception();
				}
				break;
			case "QUEEN":
				boolean isPieceq = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "Queen");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isPieceq = true;
						}
						else {
							throw new Exception();
						}
						break;
					}
				}
				if (!isPieceq) {
					throw new Exception();
				}
				break;
			default:
			}
		}
		else {
			ArrayList<ChessPiece> pieceList = Main.p2.getAll();
			switch(token.toUpperCase()) {
			case "PAWN":
				boolean isP1 = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "Pawn");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isP1 = true;
							
							if (piece.row == 7) {
								// pawn becomes queen
								pieceList.remove(piece);
								ChessPiece Q = new Queen(newX, 7, "Queen");
								Q.isPlayerOne = false;
								pieceList.add(Q);
							}
						}
						else {
							throw new Exception();
						}
						break;
					}
				}
				if (!isP1) {
					throw new Exception();
				}
				break;
			case "ROOK":
				boolean isP2 = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "Rook");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isP2 = true;
						}
						else {
							throw new Exception();
						}
						break;
					}
				}
				if (!isP2) {
					throw new Exception();
				}
				break;
			case "BISHOP":
				boolean isP3 = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "Bishop");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isP3 = true;
						}
						else {
							throw new Exception();
						}
						break;
					}
				}
				if (!isP3) {
					throw new Exception();
				}
				break;
			case "KNIGHT":
				boolean isP4 = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "Knight");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isP4 = true;
						}
						else {
							throw new Exception();
						}
						break;
					}
				}
				if (!isP4) {
					throw new Exception();
				}
				break;
			case "KING":
				boolean isP5 = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "King");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isP5 = true;
						}
						else {
							throw new Exception();
						}
						break;
					}
				}
				if (!isP5) {
					throw new Exception();
				}
				break;
			case "QUEEN":
				boolean isP6 = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						pieceList.remove(piece);
						ChessPiece temp = new Pawn(newX, newY, "Queen");
						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1.isEnemyInCheck()) {
							pieceList.remove(temp);
							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new Exception();
						}
//						piece.col = oldX;
//						piece.row = oldY;
						pieceList.remove(temp);
						pieceList.add(piece);
						
						if (piece.isValidMove(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isP6 = true;
						}
						else {
							throw new Exception();
						}
						break;
					}
				}
				if (!isP6) {
					throw new Exception();
				}
				break;
			default:
			}
		}

	}
	
	public void turnTimeStep2(String token, String oldCoord, String newCoord) throws CheckInvalidException {
		/* ASSUMING oldCoord IS ALREADY PARSED TO BE SOMETHING LIKE A1 */
		// make all capitals
		oldCoord = oldCoord.toUpperCase();
		newCoord = newCoord.toUpperCase();

		char[] oldArray = oldCoord.toCharArray();
		char[] newArray = newCoord.toCharArray();
		int oldX = oldArray[0] - 65;
		int oldY = oldArray[1] - 49;
		int newX = newArray[0] - 65;
		int newY = newArray[1] - 49;

		// 1. Allow player to select piece and move it.
		if (isPlayer1) {
			// !!! MAKE IT A COPY OF P1
			ArrayList<ChessPiece> pieceList = Main.p1_temp.getAll();
			switch(token.toUpperCase()) {
			case "PAWN":
				boolean isPiece = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							isPiece = true;
							piece.col = newX;
							piece.row = newY;

							if (piece.row == 0) {
								// pawn becomes queen
								pieceList.remove(piece);
								ChessPiece Q = new Queen(newX, 0, "Queen");
								Q.isPlayerOne = true;
								pieceList.add(Q);
							}
						}
						else {
							throw new CheckInvalidException();
						}
						
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						
						break;
					}
				}
				
				if (!isPiece) {
					throw new CheckInvalidException();
				}
				break;
			case "ROOK":
				boolean isPiecer = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isPiecer = true;
						}
						else {
							throw new CheckInvalidException();
						}
						
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						break;
					}
				}
				
				if (!isPiecer) {
					throw new CheckInvalidException();
				}
				break;
			case "BISHOP":
				boolean isPieceb = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isPieceb = true;
						}
						else {
							throw new CheckInvalidException();
						}
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						break;
					}
				}
				if (!isPieceb) {
					throw new CheckInvalidException();
				}
				break;
			case "KNIGHT":
				boolean isPiecek = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isPiecek = true;
						}
						else {
							throw new CheckInvalidException();
						}
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						break;
					}
				}
				if (!isPiecek) {
					throw new CheckInvalidException();
				}
				break;
			case "KING":
				boolean isPieceg = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isPieceg = true;
						}
						else {
							throw new CheckInvalidException();
						}
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						break;
					}
				}
				if (!isPieceg) {
					throw new CheckInvalidException();
				}
				break;
			case "QUEEN":
				boolean isPieceq = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isPieceq = true;
						}
						else {
							throw new CheckInvalidException();
						}
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p2_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						break;
					}
				}
				if (!isPieceq) {
					throw new CheckInvalidException();
				}
				break;
			default:
			}
		}
		else {
			// !!! MAKE IT A COPY OF P2
			ArrayList<ChessPiece> pieceList = Main.p2_temp.getAll();
			switch(token.toUpperCase()) {
			case "PAWN":
				boolean isPiece = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							isPiece = true;
							piece.col = newX;
							piece.row = newY;

							if (piece.row == 0) {
								// pawn becomes queen
								pieceList.remove(piece);
								ChessPiece Q = new Queen(newX, 0, "Queen");
								Q.isPlayerOne = true;
								pieceList.add(Q);
							}
						}
						else {
							throw new CheckInvalidException();
						}
						
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						
						break;
					}
				}
				
				if (!isPiece) {
					throw new CheckInvalidException();
				}
				break;
			case "ROOK":
				boolean isP2 = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isP2 = true;
						}
						else {
							throw new CheckInvalidException();
						}
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						break;
					}
				}
				if (!isP2) {
					throw new CheckInvalidException();
				}
				break;
			case "BISHOP":
				boolean isP3 = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isP3 = true;
						}
						else {
							throw new CheckInvalidException();
						}
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						break;
					}
				}
				if (!isP3) {
					throw new CheckInvalidException();
				}
				break;
			case "KNIGHT":
				boolean isP4 = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isP4 = true;
						}
						else {
							throw new CheckInvalidException();
						}
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						break;
					}
				}
				if (!isP4) {
					throw new CheckInvalidException();
				}
				break;
			case "KING":
				boolean isP5 = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isP5 = true;
						}
						else {
							throw new CheckInvalidException();
						}
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						break;
					}
				}
				if (!isP5) {
					throw new CheckInvalidException();
				}
				break;
			case "QUEEN":
				boolean isP6 = false;
				for (ChessPiece piece : pieceList) {
					int check_x = piece.get_x();
					int check_y = piece.get_y();
					if ((token.toUpperCase().compareTo(piece.toString().toUpperCase()) == 0) && check_x == oldX && check_y == oldY) {
						// piece exists (so correct player, type, and location to move FROM)
						if (piece.isValidMove2(oldX, oldY, newX, newY)) {
							piece.col = newX;
							piece.row = newY;
							isP6 = true;
						}
						else {
							throw new CheckInvalidException();
						}
//						pieceList.remove(piece);
//						ChessPiece temp = new Pawn(newX, newY, "Pawn");
//						pieceList.add(temp);
//						piece.col = newX;
//						piece.row = newY;
						if (Main.p1_temp.isEnemyInCheck()) {
//							pieceList.remove(temp);
//							pieceList.add(piece);
//							piece.col = oldX;
//							piece.row = oldY;
							throw new CheckInvalidException();
						}
//						piece.col = oldX;
//						piece.row = oldY;
//						pieceList.remove(temp);
//						pieceList.add(piece);
						break;
					}
				}
				if (!isP6) {
					throw new CheckInvalidException();
				}
				break;
			default:
			}
		}

	}
	
	/* Method to indicate whether a check has occurred. */
	public boolean isEnemyInCheck() {
		int ch_x = 0;
		int ch_y = 0;
		if (isPlayer1) {
			for (ChessPiece token : Main.p2.getAll()) { // get enemy king coordinates
				if (((token.toString().toUpperCase()).compareTo("KING") == 0)) {
					ch_x = token.get_x();
					ch_y = token.get_y();
					break;
				}
			}
			
			for(ChessPiece myToken : this.getAll()) {
				// A piece is now capable of taking enemy King. "Check"
				if (myToken.isValidLook(myToken.get_x(), myToken.get_y(), ch_x, ch_y)) {
					Main.p2.isInCheck = true;
					return true;
				}
			}
			Main.p2.isInCheck = false;
			return false;
		}
		else {
			for (ChessPiece token : Main.p1.getAll()) { // get enemy king coordinates
				if (((token.toString().toUpperCase()).compareTo("KING") == 0)) {
					ch_x = token.get_x();
					ch_y = token.get_y();
					break;
				}
			}
			
			for(ChessPiece myToken : this.getAll()) {
				// A piece is now capable of taking enemy King. "Check"
				if (myToken.isValidLook(myToken.get_x(), myToken.get_y(), ch_x, ch_y)) {
					Main.p1.isInCheck = true;
					return true;
				}
			}
			Main.p1.isInCheck = false;
			return false;
		}
	}
	
}

