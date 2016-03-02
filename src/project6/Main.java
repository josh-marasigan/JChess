/* CHESS <Main.java>
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

import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	
	/* -------------------------------- */
	/* [VIEW COMPONENT: USER INTERFACE] */
	/* -------------------------------- */
	/* Initialize each Player and it's properties. */
	public static Player p1;
	public static Player p2;
	public static GridPane primaryGameBoard = new GridPane();
	public boolean isPlayer1;
	public String p1_command;
	public String p2_command;
	public ArrayList<String> moveLog = new ArrayList<String>();
	public MediaPlayer music;
	public MediaPlayer introM;
	public int TurnNumber;
	public String errorMessage1;
	public String errorMessage2;
	public Label p1Err;
	public Label p2Err;
	
	public ArrayList<ChessPiece> temp_A;
	public ArrayList<ChessPiece> temp_B;
	public static Player p1_temp;
	public static Player p2_temp;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// White goes first.
			TurnNumber = 0;
			
			// SOUND FX
			File mov = new File("C:\\Users\\Josh M\\Documents\\EE422C Workspace\\Project6\\Moved.mp3");
			String moved = mov.toURI().toString();
			AudioClip MovedFX = new AudioClip(moved);
			
			File blk = new File("C:\\Users\\Josh M\\Documents\\EE422C Workspace\\Project6\\Block.mp3");
			String blkd = blk.toURI().toString();
			AudioClip BlockFX = new AudioClip(blkd);
			
			// Initialize Intro Screen Music
			File fileIn = new File("C:\\Users\\Josh M\\Documents\\EE422C Workspace\\Project6\\Intro.mp3");
			String Intr = fileIn.toURI().toString();
			Media songInt = new Media(Intr);
			introM = new MediaPlayer(songInt);
			introM.play();
			
			// Initialize Main Scree Music
			File file = new File("C:\\Users\\Josh M\\Documents\\EE422C Workspace\\Project6\\Final.mp3");
			String MEDIA_URL = file.toURI().toString();
			Media song = new Media(MEDIA_URL);
			music = new MediaPlayer(song);
			
			// Misc.
	        Image com = new Image("file:Comp.png");
	        ImageView comP = new ImageView(com);
			
			// Play Song till end of the Game.
			music.setOnEndOfMedia(new Runnable() {
				public void run() {
					music.seek(Duration.ZERO);
				}
			});
			
			/* Initialize Fields and Board for the Interface */
			primaryStage.setTitle("Java Chess IV: A Rook And A Hard Place");	
			
			/* --------------- */
			/* Starting Screen */
			/* --------------- */
			
			// Buttons and Command Container
			VBox commandButtons = new VBox(10);
			
			// Button Choose Banner
			Image introP = new Image("file:Logo.png");
			ImageView IntroS = new ImageView(introP);
			
			// Choose Side Button
			Image b1_img = new Image("file:P1Button.png");
			Image b2_img = new Image("file:P2Button.png");
			Image rob = new Image("file:Robot.png");
			
			ToggleButton compBtn = new ToggleButton();
			compBtn.setOpacity(.6);
			
			ToggleButton button_p1 = new ToggleButton();
			button_p1.setOpacity(.6);
			ToggleButton button_p2 = new ToggleButton();
			button_p2.setOpacity(.6);
			
			button_p1.setStyle("-fx-background-color: transparent; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
			button_p1.setGraphic(new ImageView(b1_img));
			button_p1.setOnMouseMoved(e -> {
				button_p1.setOpacity(1);
				button_p2.setOpacity(.6);
				compBtn.setOpacity(.6);
				// FX
				BlockFX.play(0.5);
			});
			
			button_p2.setStyle("-fx-background-color: transparent; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
			button_p2.setGraphic(new ImageView(b2_img));
			button_p2.setOnMouseMoved(e -> {
				button_p2.setOpacity(1);
				button_p1.setOpacity(.6);
				compBtn.setOpacity(.6);
				// FX
				BlockFX.play(0.5);
			});
			
			compBtn.setStyle("-fx-background-color: transparent; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );");
			compBtn.setGraphic(new ImageView(rob));
			compBtn.setOnMouseMoved(e -> {
				p2.isComputer = true;
				
				compBtn.setOpacity(1);
				button_p1.setOpacity(.6);
				button_p2.setOpacity(.6);
				// FX
				BlockFX.play(0.5);
			});
			
			// Button Set for Choosing
			ToggleGroup group = new ToggleGroup();
			button_p1.setToggleGroup(group);
			button_p2.setToggleGroup(group);
			
			HBox btns = new HBox(10);
			btns.getChildren().addAll(button_p1, compBtn, button_p2);
			btns.setAlignment(Pos.CENTER);
			
			// Align in one Box.
			commandButtons.getChildren().addAll(IntroS, btns);
			commandButtons.setAlignment(Pos.CENTER);
			commandButtons.setPadding(new Insets(20, 20, 20, 20));
			
			Scene start = new Scene(commandButtons);
			primaryStage.setScene(start);
			primaryStage.show();
			
			/* --------------------------- */
			/* Separate Scene / Main Scene */
			/* --------------------------- */
			
			p1 = new Player(true, false);
			p2 = new Player(false, false);
			establishBoard();
			placePieces();
			
			// Banner Image
			Image ban = new Image("file:Banner.png");
			ImageView wrap = new ImageView(ban);
			
			// Main Container to add Banner with Board.
			VBox GameScreenContainer = new VBox();
			GameScreenContainer.setPadding(new Insets(20, 20, 20, 20));
			GameScreenContainer.getChildren().addAll(wrap, primaryGameBoard);
	        
	        /* ------------------------- */
	        /* MOVE LOG / PLAYER BUTTONS */
	        /* ------------------------- */
	        VBox rightSide = new VBox();
	                
	        // Player Button Containers.
	        HBox playerInterface = new HBox();
	        playerInterface.setPadding(new Insets(20, 20, 20, 20));
	        
	        // Player's Buttons
	        VBox p1btn = new VBox(15);
	        p1btn.setPadding(new Insets(5, 5, 5, 5));
	        VBox p2btn = new VBox(15);
	        p2btn.setPadding(new Insets(5, 5, 5, 5));
	        
	        // Player 1 Interface
	        Image p1ban = new Image("file:P1Banner.png");
	        Image p2ban = new Image("file:P2Banner.png");
	        ImageView p1banner = new ImageView(p1ban);
	        ImageView p2banner = new ImageView(p2ban);
	                
	        // Labels and Textfield for Player1 Commands
	        Label p1Name = new Label("Choose Piece (e.g. Knight, Pawn, etc.):");
	        // Knight, King, etc.
	        TextField p1Input = new TextField();
	        
	        // Labels and Textfield for Player1 Commands
	        Label p1from = new Label("Indicate Where FROM (e.g. A1, D3, etc.):");
	        // A1, A2, etc.
	        TextField p1fro = new TextField();
	        
	        // Labels and Textfield for Player1 Commands
	        Label p1tow = new Label("Indicate Where TO (e.g. A1, D3, etc.):");
	        // A1, A2, etc.
	        TextField p1to = new TextField();
	        
	        // Bottom Part of player controls
	        HBox invalMov1 = new HBox(5);
	        	        
			Button p1Move = new Button("Execute Move");
			if (TurnNumber % 2 == 0) {
				errorMessage1 = "Player 1's Turn!";
			}
			else {
				errorMessage1 = "";
			}
			
			p1Err = new Label(errorMessage1);
			p1Err.setTextFill(Color.web("#01DF3A"));
			invalMov1.getChildren().addAll(p1Move, p1Err);
			
	        // Labels and Textfield for Player2 Commands
	        Label p2Name = new Label("Choose Piece (e.g. Knight, Pawn, etc.):");
	        // Knight, King, etc.
	        TextField p2Input = new TextField();
	        
	        // Labels and Textfield for Player2 Commands
	        Label p2from = new Label("Indicate Where FROM (e.g. A1, D3, etc.):");
	        // A1, A2, etc.
	        TextField p2fro = new TextField();
	        
	        // Labels and Textfield for Player2 Commands
	        Label p2tow = new Label("Indicate Where TO (e.g. A1, D3, etc.):");
	        // A1, A2, etc.
	        TextField p2to = new TextField();
	        
	        // Bottom Part of player controls
	        HBox invalMov2 = new HBox(5);
	        
			Button p2Move = new Button("Execute Move");
			if (TurnNumber % 2 != 0) {
				errorMessage2 = "Player 2's Turn!";
			}
			else {
				errorMessage2 = "";
			}
			p2Err = new Label(errorMessage2);
			p2Err.setTextFill(Color.web("#01DF3A"));
			invalMov2.getChildren().addAll(p2Move, p2Err);
			
	        // Add both sides to Player Box
	        p1btn.getChildren().addAll(p1banner, p1Name, p1Input, p1from, p1fro, p1tow, p1to, invalMov1);
	        p2btn.getChildren().addAll(p2banner, p2Name, p2Input, p2from, p2fro, p2tow, p2to, invalMov2);
	       
	        playerInterface.getChildren().addAll(p1btn, p2btn);
	        
	        /* Move Log */
	        Image log = new Image("file:MoveLog.png");
	        ImageView logGraph = new ImageView(log);
	        
	        VBox LogContain = new VBox(10);
	        LogContain.setAlignment(Pos.CENTER);
	        
	        VBox LogScreen = new VBox(2);
	        LogScreen.setAlignment(Pos.CENTER);
	        
	        ScrollPane scrll = new ScrollPane();
	        scrll.setContent(LogScreen);
	        scrll.setMaxHeight(100);
	        
	        // Add everything to Log Container.
	        LogContain.getChildren().addAll(logGraph, scrll);
	        
	        // Place all Right side components to one Box.
	        rightSide.getChildren().addAll(playerInterface, LogContain);
	        
	        // Show Everything
	        HBox everything = new HBox();
	        everything.getChildren().addAll(GameScreenContainer, rightSide);
			Scene get = new Scene(everything);
	        			
	        /* --------------------------------- */
	        /* [MODEL COMPONENT: USER INTERFACE] */
	        /* --------------------------------- */
	        
	        /* Button Methods */
			// Establish Player Loyalties
			button_p1.setOnAction(e -> {
				introM.stop();
				MovedFX.play(1.0);
				isPlayer1 = true;
				primaryStage.setScene(get);
				music.play();
			});
			
			button_p2.setOnAction(e -> {
				introM.stop();
				MovedFX.play(1.0);
				isPlayer1 = false;
				primaryStage.setScene(get);
				music.play();
			});
			
			compBtn.setOnAction(e -> {
				introM.stop();
				MovedFX.play(1.0);
				isPlayer1 = true;
				p2.isComputer = true;
				playerInterface.getChildren().remove(p2btn);
				playerInterface.getChildren().add(comP);
				primaryStage.setScene(get);
				music.play();
			});
			
			p1Move.setOnAction(e -> {
				// Indicate it is P1's Turn
				
				if (TurnNumber % 2 == 0) {
					try {
						
						// Check Test Fields
						p1_temp = new Player(true, false);
						p1_temp = p1;
						p2_temp = new Player(false, false);
						p2_temp = p2;
						
						p1_temp.totalPieces = new ArrayList<ChessPiece>(p1.totalPieces);
						p2_temp.totalPieces = new ArrayList<ChessPiece>(p2.totalPieces);
						
						invalMov1.getChildren().remove(p1Err);
						errorMessage1 = "";
						p1Err = new Label(errorMessage1);
						p1Err.setTextFill(Color.web("#01DF3A"));
						invalMov1.getChildren().addAll(p1Err);
						
						invalMov2.getChildren().remove(p2Err);
						errorMessage2 = "";
						p2Err = new Label(errorMessage2);
						p2Err.setTextFill(Color.web("#DF0101"));
						invalMov2.getChildren().addAll(p2Err);
						
						p1_command = "Turn " + TurnNumber
						+ "               "
						+ "               "
						+ "Player 1 Moves: " 
						+ p1Input.getText().toUpperCase()
						+ " from " 
						+ p1fro.getText().toUpperCase() 
						+ " to " + p1to.getText().toUpperCase();
						
						// Check Case
						if (p1.isInCheck) {
							p1_temp.turnTimeStep2(p1Input.getText(), p1fro.getText(), p1to.getText());
							p1.totalPieces = p1_temp.totalPieces;
							p2.totalPieces = p2_temp.totalPieces;
							// p1.turnTimeStep(p1Input.getText(), p1fro.getText(), p1to.getText());
						}
						else {
							p1.turnTimeStep(p1Input.getText(), p1fro.getText(), p1to.getText());
						}
						
						// Add to MoveLog
						LogScreen.getChildren().addAll((new Text(p1_command)));
						
						// Update the Board with new Movement
						establishBoard();
						placePieces();
						
						++TurnNumber;
						
						if(p1.isEnemyInCheck()) {
							
							// Check Message
							invalMov2.getChildren().remove(p2Err);
							errorMessage2 = "CHECK";
							p2Err = new Label(errorMessage2);
							p2Err.setTextFill(Color.web("#DF0101"));
							invalMov2.getChildren().addAll(p2Err);
						}
						else {
							// Display Opponent Message
							invalMov2.getChildren().remove(p2Err);
							errorMessage2 = "Player 2's Turn!";
							p2Err = new Label(errorMessage2);
							p2Err.setTextFill(Color.web("#01DF3A"));
							invalMov2.getChildren().addAll(p2Err);
						}
						
						invalMov1.getChildren().remove(p1Err);
						errorMessage1 = "";
						p1Err = new Label(errorMessage1);
						p1Err.setTextFill(Color.web("#DF0101"));
						invalMov1.getChildren().addAll(p1Err);
						
						p1Input.clear();
						p1to.clear();
						p1fro.clear();
						
						// SoundFx
						MovedFX.play(1.0);
						
					}
					catch (CheckInvalidException exception) {
						
						// Display Error Message
						invalMov1.getChildren().remove(p1Err);
						errorMessage1 = " STILL IN CHECK ";
						p1Err = new Label(errorMessage1);
						p1Err.setTextFill(Color.web("#DF0101"));
						invalMov1.getChildren().addAll(p1Err);
						
						// FX
						BlockFX.play(1.0);
					}
					catch (Exception exception) {
						
						// Display Error Message
						invalMov1.getChildren().remove(p1Err);
						errorMessage1 = " Invalid Move! ";
						p1Err = new Label(errorMessage1);
						p1Err.setTextFill(Color.web("#DF0101"));
						invalMov1.getChildren().addAll(p1Err);
						
						// FX
						BlockFX.play(1.0);
						
					}
				}
				else {
					// Display Error Message
					invalMov1.getChildren().remove(p1Err);
					errorMessage1 = " Error: Not Your Turn! ";
					p1Err = new Label(errorMessage1);
					p1Err.setTextFill(Color.web("#DF0101"));
					invalMov1.getChildren().addAll(p1Err);
					
					// FX
					BlockFX.play(1.0);
				}
			});
			
			p2Move.setOnAction(e -> {
				if (TurnNumber % 2 != 0) {
					try {
						// Check Test Fields
						p1_temp = new Player(false, false);
						p1_temp = p1;
						p2_temp = new Player(true, false);
						p2_temp = p2;
						
						p1_temp.totalPieces = new ArrayList<ChessPiece>(p1.totalPieces);
						p2_temp.totalPieces = new ArrayList<ChessPiece>(p2.totalPieces);
						
						// Remove Display Error Message
						invalMov2.getChildren().remove(p2Err);
						errorMessage2 = "Player 2's Turn!";
						p2Err = new Label(errorMessage2);
						p2Err.setTextFill(Color.web("#01DF3A"));
						invalMov2.getChildren().addAll(p2Err);
							
						invalMov1.getChildren().remove(p1Err);
						errorMessage1 = "";
						p1Err = new Label(errorMessage1);
						p1Err.setTextFill(Color.web("#DF0101"));
						invalMov1.getChildren().addAll(p1Err);
						
						p2_command = "Turn " + TurnNumber 
						+ "               " 
						+ "               "
						+ "Player 2 Moves: " 
						+ p2Input.getText().toUpperCase() 
						+ " from " + p2fro.getText().toUpperCase() 
						+ " to " + p2to.getText().toUpperCase();
						
						// Check Case
						if (p2.isInCheck) {

							p2_temp.turnTimeStep2(p2Input.getText(), p2fro.getText(), p2to.getText());
							p1.totalPieces = p1_temp.totalPieces;
							p2.totalPieces = p2_temp.totalPieces;
							// p2.turnTimeStep(p2Input.getText(), p2fro.getText(), p2to.getText());
							
						}
						else {
							p2.turnTimeStep(p2Input.getText(), p2fro.getText(), p2to.getText());
						}
						
						// Add to MoveLog
						LogScreen.getChildren().addAll((new Text(p2_command)));
						
						// Update the Board with new Movement
						establishBoard();
						placePieces();
						
						++TurnNumber;
						
						if(p2.isEnemyInCheck()) {
							
							// Check Message
							invalMov1.getChildren().remove(p1Err);
							errorMessage1 = "CHECK";
							p1Err = new Label(errorMessage1);
							p1Err.setTextFill(Color.web("#DF0101"));
							invalMov1.getChildren().addAll(p1Err);
						}
						else {
							invalMov1.getChildren().remove(p1Err);
							errorMessage1 = "Player 1's Turn!";
							p1Err = new Label(errorMessage1);
							p1Err.setTextFill(Color.web("#01DF3A"));
							invalMov1.getChildren().addAll(p1Err);
						}
						
						invalMov2.getChildren().remove(p2Err);
						errorMessage2 = "";
						p2Err = new Label(errorMessage2);
						p2Err.setTextFill(Color.web("#DF0101"));
						invalMov2.getChildren().addAll(p2Err);
						
						p2Input.clear();
						p2to.clear();
						p2fro.clear();
						
						// SoundFX
						MovedFX.play(1.0);
					}
					catch (CheckInvalidException exception) {
						// Display Error Message
						invalMov2.getChildren().remove(p2Err);
						errorMessage2 = " STILL IN CHECK ";
						p2Err = new Label(errorMessage2);
						p2Err.setTextFill(Color.web("#DF0101"));
						invalMov2.getChildren().addAll(p2Err);
						
						// FX
						BlockFX.play(1.0);
					}
					catch (Exception exception) {
						// Display Error Message
						invalMov2.getChildren().remove(p2Err);
						errorMessage2 = " Invalid Move! ";
						p2Err = new Label(errorMessage2);
						p2Err.setTextFill(Color.web("#DF0101"));
						invalMov2.getChildren().addAll(p2Err);
						
						// FX
						BlockFX.play(1.0);
					}
				}
				else {
					// Display Error Message
					invalMov2.getChildren().remove(p2Err);
					errorMessage2 = " Error: Not Your Turn! ";
					p2Err = new Label(errorMessage2);
					p2Err.setTextFill(Color.web("#DF0101"));
					invalMov2.getChildren().addAll(p2Err);
					
					// FX
					BlockFX.play(1.0);
				}
			});
			
	        primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	/* Method to populate pieces to board. */
	private static void placePieces() {
		ArrayList<ChessPiece> p1_pieces = p1.getAll();
		ArrayList<ChessPiece> p2_pieces = p2.getAll();
		
		// Place Player 1's pieces on board.
		for(ChessPiece temp : p1_pieces) {
			Image icon = temp.getImage();
			ImageView token = new ImageView(icon);
			HBox hold = new HBox();
			hold.getChildren().addAll(token);
			hold.setAlignment(Pos.CENTER);
			primaryGameBoard.add(hold, temp.get_x() + 1, temp.get_y() + 1);
		}
		
		// Place Player 2's pieces on board.
		for(ChessPiece temp : p2_pieces) {
			Image icon = temp.getImage();
			ImageView token = new ImageView(icon);
			HBox hold = new HBox();
			hold.getChildren().addAll(token);
			hold.setAlignment(Pos.CENTER);
			primaryGameBoard.add(hold, temp.get_x() + 1, temp.get_y() + 1);
		}
	}
	
	/* Method to populate the current state of the Board. */
	private static void establishBoard() {
		
		// Create Top Coordinates.
		char topChar = 'a';
		for(int x = 0; x < 8; ++x) {
			// Fields for the Border Coodrdinates (a1, b2 etc.)
			StackPane tile = new StackPane();			

			Label fill = new Label(topChar + "");
			fill.setStyle("-fx-background-color: none;");
			
			// Place text to Board's border.
			tile.getChildren().addAll(fill);
			
			// Move ot next letter (a to b)
			++topChar;
			
			// Place Coordinate Position.
			primaryGameBoard.add(tile, x + 1, 0);
		}
		
		// Populate Board with Sections.
		char sideCoord = '1';
		for(int x = 0; x < 10; ++x) {
			for(int y = 0; y < 10; ++y) {
				
				// Save Borders for Coordinate Text
				if((x != 0) && (x != 9) && (y != 0) && (y != 9)) {
				
					// Local Fields for each position.
					StackPane tile = new StackPane();
					Rectangle square;
					
					// Make a pattern for the Board.
					if (((x + y) % 2) == 0) {
						// Moccasin colored even tiles, cause 'white' is too boring.
						square = new Rectangle(50,50,Color.IVORY);
					}
					else {
						// SaddleBrown odd tiles for character.
						square = new Rectangle(50,50,Color.DARKCYAN);
					}
								
					tile.getChildren().addAll(square);
					
					// Place Coordinate Position.
					primaryGameBoard.add(tile, x, y);
				}
				// Create Side Coordinates
				else if(((x == 0) || (x == 9)) && ((y != 0) && (y != 9))) {
					
					// Reset Char
					if(sideCoord > '8') {
						sideCoord = '1';
					}
					
					// Fields for the Border Coodrdinates (a1, b2 etc.)
					StackPane tile = new StackPane();			
					
					Label fill = new Label(sideCoord + "");
					fill.setStyle("-fx-background-color: none;");
					
					// Place text to Board's border.
					tile.getChildren().addAll(fill);
					
					// Move ot next letter (a to b)
					++sideCoord;
					
					// Place Coordinate Position.
					primaryGameBoard.add(tile, x, y);
					
				}
			}
		}
		
		// Create Bottom Coordinates.
		char bottomChar = 'a';
		for(int x = 0; x < 8; ++x) {
			// Fields for the Border Coodrdinates (a1, b2 etc.)
			StackPane tile = new StackPane();			

			Label fill = new Label(bottomChar + "");
			fill.setStyle("-fx-background-color: none;");
			
			// Place text to Board's border.
			tile.getChildren().addAll(fill);
			
			// Move ot next letter (a to b)
			++bottomChar;
			
			// Place Coordinate Position.
			primaryGameBoard.add(tile, x + 1, 9);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
















