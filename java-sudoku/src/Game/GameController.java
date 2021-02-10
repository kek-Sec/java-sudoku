package Game;

import javax.swing.JOptionPane;

import SudokuGenerator.PuzzleGenerator;
import SudokuGenerator.SudokuGrid;

public class GameController {

	public static SudokuGrid sk;
	public static SudokuGrid[] sk_array = new SudokuGrid[10];
	public static int counter = 0;

	public static void main(String[] args) {
		GameController.NewGame();
	}

	/*
	 * Function to start a new Game
	 * 
	 * @param hidden_pieces -> number of hidden values on puzzle
	 */
	public static void NewGame() {
		
		int hidden = 0;
		//select difficulty
		String[] buttons = { "Easy", "Medium", "Hard" };
		int ret = JOptionPane.showOptionDialog(null, "Select difficulty", "S U D O K U",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);

		if(ret == 0)
		{
			hidden = 15;
		}
		else if(ret == 1)
		{
			hidden = 23;
		}
		else if(ret == 2)
		{
			hidden = 30;
		}
		
		if (counter != 0) {
			sk_array[counter - 1].is_Visible = false;
		}
		PuzzleGenerator.init(hidden);
		sk_array[counter++] = new SudokuGrid(3, 3, 3, 3);

	}

}
