package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import SudokuGenerator.Debug_Controller;

public class MenuButtonsListener implements ActionListener{
	
	//buttons that we are gonna listen for
    JButton solve_button;
    JButton newGame_button;
    JButton hint_button;
    JButton reset_button;
    JButton history_button;
    
    public MenuButtonsListener(JButton solve,JButton newGame,JButton hint,JButton reset,JButton history)
    {
    	solve_button = solve;
    	newGame_button = newGame;
    	hint_button = hint;
    	reset_button = reset;
    	history_button = history;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == solve_button)
		{
			if(Debug_Controller.enabled())
			{
				System.out.println("Clicked -> solve");
			}
		}
		else if(e.getSource() == newGame_button)
		{
			if(Debug_Controller.enabled())
			{
				System.out.println("Clicked -> new Game");
			}
		}
		else if(e.getSource() == hint_button)
		{
			if(Debug_Controller.enabled())
			{
				System.out.println("Clicked -> hint");
			}
		}
		else if(e.getSource() == reset_button)
		{
			if(Debug_Controller.enabled())
			{
				System.out.println("Clicked -> reset");
			}
		}
		else if(e.getSource() == history_button)
		{
			if(Debug_Controller.enabled())
			{
				System.out.println("Clicked -> history");
			}
		}
		else
		{
			return;
		}
	}

}
