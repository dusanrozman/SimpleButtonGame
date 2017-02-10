//View Class
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

public class BoardPanel extends JPanel implements ActionListener{
	
	private ShinyButtons model = new ShinyButtons();
	private JButton[][] buttonArray = new JButton[8][8];
	private JButton newGameButton = new JButton("New Game");
	private JButton quitButton =  new JButton("Quit");
	private JTextField scoreField = new JTextField("" + getModel().getScore()); 
	private static int numberOfSelectedButtons = 0;
	
	public static ImageIcon[] icons = {new ImageIcon("./ButtonImages/RedButton.png"), 
										new ImageIcon("./ButtonImages/OrangeButton.png"), 
										new ImageIcon("./ButtonImages/YellowButton.png"), 
										new ImageIcon("./ButtonImages/GreenButton.png"), 
										new ImageIcon("./ButtonImages/BlueButton.png"), 
										new ImageIcon("./ButtonImages/LightGrayButton.png"), 
										new ImageIcon("./ButtonImages/DarkGrayButton.png")}; 
	
	public static ImageIcon[] selectedIcons = { new ImageIcon("./SelectedButtonImages/SelectedRedButton.png"), 
												new ImageIcon("./ButtonImages/SelectedOrangeButton.png"), 
												new ImageIcon("./ButtonImages/SelectedYellowButton.png"), 
												new ImageIcon("./ButtonImages/SelectedGreenButton.png"), 
												new ImageIcon("./ButtonImages/SelectedBlueButton.png"), 
												new ImageIcon("./ButtonImages/electedLightGrayButton.png"), 
												new ImageIcon("./ButtonImages/SelectedDarkGrayButton.png") 
                                              };
	public BoardPanel (){
				
		// Choose to lay out components manually 
		setLayout(null); 
		 
		// Make a border around the outside with the given title 
		setSize(578, 634); 
		
		// Creating Labels, TextBoxes and Buttons
		JLabel scoreLabel = new JLabel("Score:"); 
		scoreLabel.setLocation(10, 570); 
		scoreLabel.setSize(70,30); 
		add(scoreLabel);
		
		newGameButton.setLocation(330, 570);
		newGameButton.setSize(120, 30);
		newGameButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				model.newGame();
				update();
			}});
		
		add(newGameButton);
		quitButton.setLocation(460, 570);
		quitButton.setSize(102, 30);
		add(quitButton);
		
		scoreField.setLocation(80, 570); 
		scoreField.setSize(130, 30);
		scoreField.setHorizontalAlignment(JTextField.RIGHT); 
		add(scoreField);
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				
				byte bcolor = model.getButton(i, j);
				
				buttonArray[i][j] = new JButton(icons[bcolor]);
				buttonArray[i][j].setLocation(10+(j*69), 10+(i*69));
				buttonArray[i][j].setSize(69, 69);
				buttonArray[i][j].addActionListener(this); 
				add(buttonArray[i][j]);
			}
		}		
		update();
	}
	
	public void update(){
		ShinyButtons model = this.getModel();
		model.cleanTable();
		model.processTable();
		//update buttons
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				byte bcolor = model.getButton(i, j);
				if ((buttonArray[i][j].isSelected() == true) || (model.getSelectionTable()[i][j] == true)){
					buttonArray[i][j].setIcon(selectedIcons[bcolor]);
				}
				else{
				buttonArray[i][j].setIcon(icons[bcolor]);
				}
			}
		}
		//update score
		scoreField.setText("" + model.getScore());
	}
	
	//swap() is a method to check if there are any other selected buttons that can be swapped
	public int[] swap(int row, int col){
		//Will store the button that needs to be swapped wtih
		int[] valuesToSwap = new int[2];
		valuesToSwap[0] = 0;
		valuesToSwap[1] = 0;
		
		//Checks to which buttons are selected
		//Row 0 checks (special cases)
		if ((row == 0) && (col == 0)){
			if (buttonArray[row + 1][col].isSelected() == true) { valuesToSwap[0] = row+1; valuesToSwap[1] = col; return valuesToSwap;}
			if (buttonArray[row][col+1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col+1; return valuesToSwap;}
		}
		else if ((row == 0) && (col == 7)){
			if (buttonArray[row + 1][col].isSelected() == true) { valuesToSwap[0] = row + 1; valuesToSwap[1] = col; return valuesToSwap;}
			if (buttonArray[row][col - 1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col -1; return valuesToSwap;}
		}
		else if(row == 0){
			if (buttonArray[row + 1][col].isSelected() == true) { valuesToSwap[0] = row + 1; valuesToSwap[1] = col; return valuesToSwap;}
			if (buttonArray[row][col - 1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col -1; return valuesToSwap;}	
			if (buttonArray[row][col +1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col +1; return valuesToSwap;}
		}
		//Checks for Row 7 (special cases)
		else if ((row == 7) && (col == 0)){
			if (buttonArray[row - 1][col].isSelected() == true) { valuesToSwap[0] = row - 1; valuesToSwap[1] = col; return valuesToSwap;}
			if (buttonArray[row][col+1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col+1; return valuesToSwap;}
		}
		else if ((row == 7) && (col == 7)){
			if (buttonArray[row - 1][col].isSelected() == true) { valuesToSwap[0] = row - 1; valuesToSwap[1] = col; return valuesToSwap;}
			if (buttonArray[row][col - 1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col -1; return valuesToSwap;}
		}
		else if(row == 7){
			if (buttonArray[row - 1][col].isSelected() == true) { valuesToSwap[0] = row - 1; valuesToSwap[1] = col; return valuesToSwap;}
			if (buttonArray[row][col - 1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col -1; return valuesToSwap;}	
			if (buttonArray[row][col +1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col +1; return valuesToSwap;}
		}
		//Checks for Col 0 (special cases)
		else if(col == 0){
			if (buttonArray[row - 1][col].isSelected() == true) { valuesToSwap[0] = row - 1; valuesToSwap[1] = col; return valuesToSwap;}
			if (buttonArray[row+1][col].isSelected() == true) { valuesToSwap[0] = row +1; valuesToSwap[1] = col; return valuesToSwap;}	
			if (buttonArray[row][col+1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col+1; return valuesToSwap;}	
		}
		//Checks for Col 7 (special cases)
		else if(col == 7){
			if (buttonArray[row - 1][col].isSelected() == true) { valuesToSwap[0] = row - 1; valuesToSwap[1] = col; return valuesToSwap;}
			if (buttonArray[row+1][col].isSelected() == true) { valuesToSwap[0] = row +1; valuesToSwap[1] = col; return valuesToSwap;}	
			if (buttonArray[row][col - 1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col -1 ; return valuesToSwap;}	
		}
		//Everything else
		else{
			if (buttonArray[row - 1][col].isSelected() == true) { valuesToSwap[0] = row - 1; valuesToSwap[1] = col; return valuesToSwap;}
			if (buttonArray[row+1][col].isSelected() == true) { valuesToSwap[0] = row +1; valuesToSwap[1] = col; return valuesToSwap;}	
			if (buttonArray[row][col+1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col+1; return valuesToSwap;}
			if (buttonArray[row][col - 1].isSelected() == true) { valuesToSwap[0] = row; valuesToSwap[1] = col -1 ; return valuesToSwap;}	
			
		}	
		return valuesToSwap;
	}
	
	public void deSelectBoard(){
		for(int row = 0; row < 8; row ++){
			for (int col = 0; col < 8; col ++){
				buttonArray[row][col].setSelected(false);
			}
		}
		
	}
	
	//Get Methods
	public ShinyButtons getModel() { return model; }
	public JButton[][] getButton() { return buttonArray; }
	public JButton getNewGameButton() { return newGameButton; }
	public JButton getQuitButton() { return quitButton; }
	public JTextField getScoreField() { return scoreField; }
	
	// This is the single event handler for all the buttons 
	public void actionPerformed(ActionEvent e) { 
		for(int row=0; row<8; row++) { 
			for (int col=0; col < 8; col++) { 
				if (e.getSource() == buttonArray[row][col]) { 
					if (buttonArray[row][col].isSelected() == false){
						buttonArray[row][col].setSelected(true);		//Select button
						numberOfSelectedButtons++;						//increase value of numberOfSelectedButtons by 1
						
						if (numberOfSelectedButtons == 2) { 
							int[] buttonsToSwap = swap(row, col);
							
							if ((buttonsToSwap[0] == 0) && (buttonsToSwap[1] == 0)){
								deSelectBoard();
								numberOfSelectedButtons = 0;
							}
							else{					
								model.swapButtons(row, col,buttonsToSwap[0], buttonsToSwap[1]);		//Swaps been made, take selection off everything.
								buttonArray[row][col].setSelected(false);
								buttonArray[buttonsToSwap[0]][buttonsToSwap[1]].setSelected(false);
								numberOfSelectedButtons = 0;
							}
								
						}					
					}
					else{
						buttonArray[row][col].setSelected(false);
						numberOfSelectedButtons--;
					}		
				}
			}
		}
		update();
	} 	
}
