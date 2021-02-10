package Game;

import java.util.Arrays;

public class SudokuSolver {

  /*
	 * check if puzzle is correct is correct
	 * 
	 * @param grid -> rows[][] array
	 */
  public boolean checkSudokuStatus(int[][] grid) {
    //check each row
    for (int i = 0; i < 9; i++) {

      int[] row = new int[9];
      int[] square = new int[9];
      int[] column = grid[i].clone();

      for (int j = 0; j < 9; j++) {
        row[j] = grid[j][i];

        //create square from row array , see Puzzle Generator class for more detailed approach
        square[j] = grid[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3];
      }

      //check 		column				row				square
      if (! (validate(column) && validate(row) && validate(square))) return false;
    }
    return true;
  }

  // validate single array
  // @param check -> array to check
  private boolean validate(int[] check) {
    int i = 0;
    Arrays.sort(check);
    for (int number: check) {
      if (number != ++i) return false;
    }
    return true;
  }

  /*
	 * used to parse JTextField text string -> int[][]
	 * 
	 * @param in -> String[][] containing JTextField.text
	 */
  public int[][] convert_array(String[][] in ) {
    int[][] out = new int[ in .length][ in .length];
    int foo;

    for (int i = 0; i < in.length; i++) {
      for (int j = 0; j < in.length; j++) {
        try {
          foo = Integer.parseInt( in [i][j]);
          out[i][j] = foo;
        } catch(NumberFormatException e) {
          foo = 0;
          out[i][j] = foo;
        }
      }
    }
    return out;
  }

  /*
	 * Convert squares[][] to rows[][]
	 * 
	 * @param squares_array -> array of individual squares squares are stored like:
	 * --------------------------------- |0|1|2| |3|4|5| |6|7|8|
	 * 
	 */
  public int[][] SquaresToRows(int[][] squares_array) {
    int a,
    b,
    c,
    d;

    int row_indx;
    int row_counter = 0;

    int[][] out = new int[9][9];
    a = 0;
    b = 0;
    c = 0;
    d = 0;
    while (row_counter < 9) {
      row_indx = 0;

      for (int i = 0; i < 3; i++) {
        b = c;

        out[row_counter][row_indx++] = squares_array[a][b];
        b++;
        out[row_counter][row_indx++] = squares_array[a][b];
        b++;
        out[row_counter][row_indx++] = squares_array[a][b];
        a++;
      }

      c = c + 3;
      if (c == 9) {
        c = 0;
      }

      row_counter++;
      if (row_counter == 3) {
        d = 3;
      }

      if (row_counter == 6) {
        d = 6;
      }
      a = d;
    }
    return out;
  }
}