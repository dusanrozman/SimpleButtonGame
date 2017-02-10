import javax.swing.*;

public class ShinyButtonsView extends JPanel {
	JButton[][] buttonArray;

	public static ImageIcon[] icons = {new ImageIcon("./ButtonImages/RedButton.png"), 
										new ImageIcon("./ButtonImages/OrangeButton.png"), 
										new ImageIcon("./ButtonImages/YellowButton.png"), 
										new ImageIcon("./ButtonImages/GreenButton.png"), 
										new ImageIcon("./ButtonImages/BlueButton.png"), 
										new ImageIcon("./ButtonImages/LightGrayButton.png"), 
										new ImageIcon("./ButtonImages/DarkGrayButton.png")}; 
	//Constructor
	public ShinyButtonsView (ShinyButtons model){
		 
		setLayout(null);  
		setSize(578, 562); 
		
		JButton[][] buttonArray = new JButton[8][8];

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				
				byte bcolor = model.getButton(i, j);
				
				buttonArray[i][j] = new JButton(icons[bcolor]);
				buttonArray[i][j].setLocation(10+(j*69), 10+(i*69));
				buttonArray[i][j].setSize(69, 69);
				add(buttonArray[i][j]);
			}
		}
		
		// Creating Labels, TextBoxes and Buttons
		JLabel scoreLabel = new JLabel("Score:"); 
		scoreLabel.setLocation(10, 570); 
		scoreLabel.setSize(70,30); 
		
		JTextField scoreField = new JTextField("" + model.getScore()); 
		scoreField.setLocation(80, 570); 
		scoreField.setSize(130, 30);
		scoreField.setHorizontalAlignment(JTextField.RIGHT); 
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.setLocation(330, 570);
		newGameButton.setSize(120, 30);
		
		
		JButton quitButton = new JButton("Quit");
		quitButton.setLocation(460, 570);
		quitButton.setSize(102, 30);		
	}		
}
