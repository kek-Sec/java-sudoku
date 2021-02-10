package Game;

import java.util.Arrays;

public class SudokuSolver{

	public boolean checkSudokuStatus(int[][] grid) {
	    for (int i = 0; i < 9; i++) {

	        int[] row = new int[9];
	        int[] square = new int[9];
	        int[] column = grid[i].clone();

	        for (int j = 0; j < 9; j ++) {
	            row[j] = grid[j][i];
	            square[j] = grid[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3];
	        }
	        if (!(validate(column) && validate(row) && validate(square)))
	            return false;
	    }
	    return true;
	}

	private boolean validate(int[] check) {
	    int i = 0;
	    Arrays.sort(check);
	    for (int number : check) {
	        if (number != ++i)
	            return false;
	    }
	    return true;
	}

	
}
