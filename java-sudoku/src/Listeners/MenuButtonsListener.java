package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Game.GameController;
import Game.SudokuSolver;
import Metrics.History;
import SudokuGenerator.Debug_Controller;
import SudokuGenerator.SudokuGrid;

public class MenuButtonsListener implements ActionListener {

	// buttons that we are gonna listen for
	JButton solve_button;
	JButton newGame_button;
	JButton hint_button;
	JButton reset_button;
	JButton history_button;

	public MenuButtonsListener(JButton solve, JButton newGame, JButton hint, JButton reset, JButton history) {
		solve_button = solve;
		newGame_button = newGame;
		hint_button = hint;
		reset_button = reset;
		history_button = history;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == solve_button) {
			SudokuSolver sl = new SudokuSolver();
			int[][] squares_array = sl.convert_array(SudokuGrid.temp_array);
			int[][] rows = sl.SquaresToRows(squares_array);
			if (Debug_Controller.enabled()) {
				System.out.println("Clicked -> solve");
			}
			boolean is_correct = sl.checkSudokuStatus(rows);

			if (is_correct) {
				History.writeVictory();
				
				int res = JOptionPane.showOptionDialog(new JFrame(), "Good job! You solved it! \n\nNew Game?",
						"S U D O K U", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					
					
					
					
				} else if (res == JOptionPane.NO_OPTION) {
					System.exit(0);
				} else if (res == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
			}
			else
			{
				History.writeDefeat();
				int res = JOptionPane.showOptionDialog(new JFrame(), "Sorry but,You failed! \n\n New Game?",
						"S U D O K U", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null,
						new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
				
				if (res == 0)
				{
					GameController.NewGame();
				}
				else
				{
					System.exit(0);
				}
			}

		} else if (e.getSource() == newGame_button) {
			
			GameController.NewGame();
			
			if (Debug_Controller.enabled()) {
				System.out.println("Clicked -> new Game");
			}
		} else if (e.getSource() == hint_button) {
			if (Debug_Controller.enabled()) {
				System.out.println("Clicked -> hint");
			}
		} else if (e.getSource() == reset_button) {
			if (Debug_Controller.enabled()) {
				System.out.println("Clicked -> reset");
			}
		} else if (e.getSource() == history_button) {
			if (Debug_Controller.enabled()) {
				System.out.println("Clicked -> history");
			}
		} else {
			return;
		}
	}

}
