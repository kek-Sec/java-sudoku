package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Game.GameController;
import Game.SudokuSolver;
import Metrics.History;
import SudokuGenerator.Debug_Controller;
import SudokuGenerator.SudokuGrid;

public class MenuButtonsListener implements ActionListener {

  // buttons that we are going to listen for
  JButton solve_button;
  JButton newGame_button;
  JButton hint_button;
  JButton history_button;

  //constructor
  public MenuButtonsListener(JButton solve, JButton newGame, JButton hint, JButton history) {

    //initialize button references
    solve_button = solve;
    newGame_button = newGame;
    hint_button = hint;

    history_button = history;
  }

  //ActionHandler for all the menu buttons
  @Override
  public void actionPerformed(ActionEvent e) {

    //Solve button
    if (e.getSource() == solve_button) {

      //initialize solver
      SudokuSolver sl = new SudokuSolver();
      int[][] squares_array = sl.convert_array(SudokuGrid.temp_array);
      int[][] rows = sl.SquaresToRows(squares_array);

      //Check if the puzzle is correct
      boolean is_correct = sl.checkSudokuStatus(rows);

      if (is_correct) {
        //log victory
        History.writeVictory();
        //Show menu
        int res = JOptionPane.showOptionDialog(new JFrame(), "Good job! You solved it! \n\nNew Game?", "S U D O K U", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {
          "Yes",
          "No"
        },
        JOptionPane.YES_OPTION);

        //handle menu options
        if (res == JOptionPane.YES_OPTION) {

          GameController.NewGame();

        } else if (res == JOptionPane.NO_OPTION) {
          System.exit(0);
        } else if (res == JOptionPane.CLOSED_OPTION) {
          System.exit(0);
        }
      } else {
        //log defeat
        History.writeDefeat();

        //Show menu
        int res = JOptionPane.showOptionDialog(new JFrame(), "Sorry but,You failed! \n\n New Game?", "S U D O K U", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] {
          "Yes",
          "No"
        },
        JOptionPane.YES_OPTION);

        //handle options
        if (res == 0) {
          GameController.NewGame();
        } else {
          System.exit(0);
        }
      }

      //debug
      if (Debug_Controller.enabled()) {
        System.out.println("Clicked -> solve");
      }
    } else if (e.getSource() == newGame_button) {

      //new Game button
      GameController.NewGame();

      if (Debug_Controller.enabled()) {
        System.out.println("Clicked -> new Game");
      }
    } else if (e.getSource() == hint_button) {
    	
    	int res = JOptionPane.showOptionDialog(new JFrame(), "I believe in you!", "S U D O K U", JOptionPane.INFORMATION_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, new Object[] {
    	          "Thanks"
    	        },
    	        JOptionPane.YES_OPTION);
    	
      if (Debug_Controller.enabled()) {
        System.out.println("Clicked -> hint");
      }
    } else if (e.getSource() == history_button) {
      File local = new File("./history.sdk");
      try {
        //Doesn't seem to be working on linux while it works on win 10.
        java.awt.Desktop.getDesktop().edit(local);
      } catch(IOException e1) {

}

      if (Debug_Controller.enabled()) {
        System.out.println("Clicked -> history");
      }
    } else {
      return;
    }
  }

}