package SudokuGenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Game.SudokuSolver;
import Listeners.MenuButtonsListener;

public class SudokuGrid {

  // temporary array for holding JTextFields content
  public static String[][] temp_array;

  // row array
  public int[][] rows_array;

  // array containing each square
  public int[][] squares_array;

  // Array of subBoards - contains individual arrays of JTextField
  public SubBoard[] subBoards;
  // Menu buttons action listener
  public MenuButtonsListener mbl;

  // each field is one square in the puzzle
  public JTextField[] fields;

  // set font to be used inside squares
  Font font1 = new Font("SansSerif", Font.BOLD, 23);

  // number of squares
  int horizontal_squares,
  vertical_squares;

  // number of rows and columns
  int inner_rows,
  inner_cols;

  public boolean is_Visible = true;

  public SudokuGrid(int horizontal_squares, int inner_rows, int vertical_squares, int inner_cols) {

    // set base variables
    // needed to construct grid
    this.horizontal_squares = horizontal_squares;
    this.vertical_squares = vertical_squares;
    this.inner_rows = inner_rows;
    this.inner_cols = inner_cols;

    // max index
    int max = inner_rows * inner_cols;

    // initialize rows and columns array
    this.rows_array = new int[max][max];
    this.rows_array = PuzzleGenerator.getRowsArray();

    // initialize temp array
    SudokuGrid.temp_array = new String[max][max];

    // initialize squares array
    this.squares_array = new int[max][max];
    this.squares_array = PuzzleGenerator.createSquaresArray();

    // invoke swing runnable
    EventQueue.invokeLater(new Runnable() {@Override
      public void run() {
        // try to set default look and feel
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(ClassNotFoundException ex) {} catch(InstantiationException ex) {} catch(IllegalAccessException ex) {} catch(UnsupportedLookAndFeelException ex) {}
        // create base frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // add game board
        frame.add(new SudokuBoard());

        // add menu grid
        frame.add(new MenuPane(), BorderLayout.AFTER_LINE_ENDS);

        // pack components on frame
        frame.pack();

        // frame properties
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setTitle("S U D O K U");
        frame.setLocationRelativeTo(null);

        // show frame
        frame.setVisible(is_Visible);

      }
    });
  }

  public class MenuPane extends JPanel {

    // options menu
    public MenuPane() {
      setBorder(new EmptyBorder(5, 5, 5, 5));
      setLayout(new GridBagLayout());

      // gridbagconstraints is the easiest way to stack buttons in swing
      GridBagConstraints gbc = new GridBagConstraints();

      // GBC properties
      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.weightx = 1;
      gbc.fill = GridBagConstraints.HORIZONTAL;

      // create buttons
      JButton solve_button = new JButton("Solve");
      JButton newGame_button = new JButton("New Game");
      JButton hint_button = new JButton("Hint");
      JButton history_button = new JButton("History");

      // initialize menu button listener
      mbl = new MenuButtonsListener(solve_button, newGame_button, hint_button, history_button);

      // Add event listeners
      solve_button.addActionListener(mbl);
      newGame_button.addActionListener(mbl);
      hint_button.addActionListener(mbl);
      history_button.addActionListener(mbl);

      // adding buttons to menu on the y axis
      add(solve_button, gbc);
      gbc.gridy++;
      add(newGame_button, gbc);
      gbc.gridy++;
      add(hint_button, gbc);
      gbc.gridy++;
      add(history_button, gbc);
    }
  }

  @SuppressWarnings("serial")
  public class SudokuBoard extends JPanel {

    // outer board panel
    public int ROWS = horizontal_squares;
    public int COLUMNS = vertical_squares;

    public SudokuBoard() {
    	
      setBorder(new EmptyBorder(4, 4, 4, 4));
      subBoards = new SubBoard[ROWS * COLUMNS];
      setLayout(new GridLayout(ROWS, COLUMNS, 2, 2));
      for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLUMNS; col++) {
          int index = (row * ROWS) + col;

          SubBoard board = new SubBoard(index);

          
          //debug
          if (Debug_Controller.enabled()) {
            System.out.println("row -> " + row + " col -> " + col);
            System.out.println("generating board -> " + index);
          }

          //important to make it aesthetic
          board.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 3), new EmptyBorder(4, 4, 4, 4)));
          subBoards[index] = board;
          add(board);
        }
      }
    }
  }

  public class SubBoard extends JPanel {

    // inner board panel
    public int ROWS = inner_rows;
    public int COLUMNS = inner_cols;
    public String text;

    public SubBoard(int indx) {

      setLayout(new GridLayout(ROWS, COLUMNS, 2, 2));
      fields = new JTextField[ROWS * COLUMNS];

      for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLUMNS; col++) {

          int index = (row * COLUMNS) + col;

          //initialize a textfield
          JTextField field = new JTextField(4);

          //set field properties
          field.setHorizontalAlignment(JTextField.CENTER);
          field.setFont(font1);

          //get current value from square array
          int current_val = squares_array[indx][index];

          //add value to temp array
          temp_array[indx][index] = String.valueOf(current_val);

          fields[index] = field;
          text = String.valueOf(current_val);

          // empty squares marked with 0
          if (current_val == 0) {
            text = " ";
          }
          field.setText(text);
          add(field);

          // listen to focus event
          // update temp array
          field.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
              temp_array[indx][index] = ((JTextField) e.getSource()).getText();

            }

            @Override
            public void focusLost(FocusEvent e) {
              temp_array[indx][index] = ((JTextField) e.getSource()).getText();

            }
          });
        }
      }
    }
  }
}