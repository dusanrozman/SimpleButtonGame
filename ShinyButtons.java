//ShinyButtons is the MODEL for the game
public class ShinyButtons { 
	
	//Class Attributes
	public static byte RED = 0; 
	public static byte ORANGE = 1; 
	public static byte YELLOW = 2; 
	public static byte GREEN = 3; 
	public static byte BLUE = 4; 
	public static byte LIGHT_GRAY = 5; 
	public static byte DARK_GRAY = 6; 
	
	private boolean[][] selectionTable = new boolean[8][8];
 
	public static byte ROWS = 8; 
	private int score = 0; 
	private byte[][] buttonTable; 
	
	//Shiny Buttons Constructor
	public ShinyButtons() { 
		buttonTable = new byte[ROWS][ROWS]; 
		resetButtons(); 
	} 
 
	//Method - resets board colors - used for new game
	private void resetButtons() { 
		for (int r=0; r<ROWS; r++) 
			for (int c=0; c<ROWS; c++) 
				buttonTable[r][c] = (byte)(Math.random()*7); 
	}
	
	//Method - this method restarts the game/sets up a new game
	public void newGame() {
		resetButtons();
		processTable();
		score = 0;
	}
	
	//Method for swapping 2 buttons in the model
	public void swapButtons(int row1, int col1, int row2, int col2){
		byte temp;
		
		temp = buttonTable[row1][col1];
		buttonTable[row1][col1] = buttonTable[row2][col2];
		buttonTable[row2][col2] = temp;			
	}
	
	//Method used for determining which rows have matches. 
	public void processTable(){
		//sets all of selectionTable to false
		for (int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				selectionTable[row][col] = false;
			}
		}
		
		for (int r = 0; r < 8; r++){
			int match = 0;
			for(int c = 1; c < 8; c++){
				if (buttonTable[r][c] == buttonTable[r][c-1]){
					match++;
					if(match >= 2){
						selectionTable[r][c] = true;
						selectionTable[r][c-1] = true;
						selectionTable[r][c-2] = true;
					
						if (match == 2)								//update score
							score = score + 30;
						else { score = score + 30 + 30*match; }
					}
				}
				else { match = 0; }
			}
		}
			
		for(int c1 = 0; c1 < 8; c1++){
			int match = 0;
			for(int r1 = 1; r1 < 8; r1++){
				if (buttonTable[r1][c1] == buttonTable[r1-1][c1]){
					match++;
					if(match >= 2){
							selectionTable[r1][c1] = true;
							selectionTable[r1-1][c1] = true;
							selectionTable[r1-2][c1] = true;
							if (match == 2)							//update score
								score = score + 30;
							else { score = score + 30 + 30*match; }
					}
				}
				else { match = 0; }
			}	
		}	
	}
	
	//Method used for cleaning up the rows once they have been selected. Based off of Pseudo-code given by Mark Lanthier
	public void cleanTable(){
		boolean newPieceAdded = false;
		int r = 7;
		
		while(r >= 0){
			newPieceAdded = false;
			
			for (int c = 0; c < 8; c++){
				if (selectionTable[r][c] == true){
					//move pieces down..
					for(int r2 = r; r2 > 0; r2--){
						selectionTable[r2][c] = selectionTable[r2 -1][c];
						buttonTable[r2][c] = buttonTable[r2 - 1][c];
					}
					// Add new piece
					buttonTable[0][c] = (byte)(Math.random()*7);
					selectionTable[0][c] = false;
					newPieceAdded = true;			
				}
			}
			if(newPieceAdded == false){
				r--;
			}
		}
	}
	
	//Get Methods
	public byte getButton(int r, int c) { return buttonTable[r][c]; } 
	public int getScore() { return score; }
	public boolean[][] getSelectionTable() { return selectionTable; }
}