//Controller
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ShinyButtonsApp extends JFrame implements ActionListener {
	BoardPanel view = new BoardPanel();
	ShinyButtons model = new ShinyButtons();
	
	//Constructor
	 public ShinyButtonsApp(String title) { 
		 super(title); // Set title of window 
		 setDefaultCloseOperation(EXIT_ON_CLOSE); // allow window to close 
		 setSize(578, 634); // Set size of window 
		 setResizable(false); 
		 setLayout(null); 
		 
		 add(view);
         getContentPane().add(view);
         
         Timer timer = new Timer(500,new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
			 // Write code here to clean the table, process it, and then update 
				 model.cleanTable();
				 model.processTable();
				 view.update();
			 }}); 
         timer.start(); // This line starts the timer which will happen every 0.5 seconds 
	 } 

	public static void main(String[] args) { 

		final ShinyButtonsApp frame = new ShinyButtonsApp("Shiny Buttons");
		JButton newGame = frame.view.getNewGameButton();
				
		//Quit Button Actions
		frame.view.getQuitButton().addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) { 
				 frame.dispose();
				 }});
        
		frame.setVisible(true);	
	}
	
	// This is the single event handler for all the buttons 
	public void actionPerformed(ActionEvent e) { 
	} 	
}