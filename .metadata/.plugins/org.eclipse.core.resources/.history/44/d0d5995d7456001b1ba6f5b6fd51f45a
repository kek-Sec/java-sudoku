package Game;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/* Class responsible for generating the game GUI using Swing */

@SuppressWarnings("serial")
public class SudokuFrame extends JFrame{
	
	private JPanel numbersPanel;	//panel for user input via buttons
	
	
	//constructor
	public SudokuFrame()
	{
		//Jframe properties
		this.setMinimumSize(new Dimension(800,600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("S U D O K U");
		
		//Menu items
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Game");
		JMenu newGame = new JMenu("New Game");
		menu1.add(newGame);
		menuBar.add(menu1);
		this.setJMenuBar(menuBar);
		
		//set frame visible
		this.setVisible(true);
		
	}

}
