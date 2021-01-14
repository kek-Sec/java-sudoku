package Game;

import Interfaces.SolverInterface;

public class SudokuSolver implements SolverInterface{

	// the final boolean we return
	public boolean isCorrect;
	
	//number of squares in the puzzle
	int total_squares;
	
	//square length in arrays
	int square_length;
	
	//array for each individual array and its contents
	int[][] squares_array;
	
	/*
	 *  How squares are stored
	 *  	example in 3x3 square
	 * -----------------------------
	 * 			|0|1|2|
	 * 			|3|4|5|
	 * 			|6|7|8|
	 * 
	 * ------------------------------
	 * So the array looks like: [NumberOfSquares][NumberOfIndexes] 
	 */
	
	/*
	 * @param rows_array -> array of rows in the puzzle
	 * @param col array -> array of columns in the puzzle
	 */
	public SudokuSolver(int[][] rows_array,int [][] col_array,int square_height,int square_width,int total_numbers)
	{
		square_length = square_height * square_width; //gets number of indexes ^
		total_squares = total_numbers / square_length; //calculate total_squares
		squares_array = createSquares(square_width,square_height,rows_array);	//fill squares array
		isCorrect = getResult(rows_array,col_array); //get final result!
	}

	@Override
	public int[][] createSquares(int width, int height, int[][] rows_array) {
		
		//initialize squares array
		int[][] squares = new int[total_squares][square_length];
		
		
		
		int vc = 0; //vertical counter
		int hc = 0; //horizontal counter
		int sc = 0; //squares_counter
		
		//fill squares array
		while (sc != total_squares)
		{
			for(int i=0;i<square_length;i++)
			{
				for(int j=vc;j<(vc+width-1);j++)
				{
					for(int k=hc;k<(k+height-1);k++)
					{
						squares[sc][i] = rows_array[j][k];
					}
				}
				sc++; //increment created squares counter
				vc = vc + width;	//increment vertical counter
				
				if(vc == rows_array[0].length)
				{	//if we hit the right wall go deeper!
					hc = hc + height;
				}
			}
		}

		
		return squares;
	}

	@Override
	public boolean checkSquares(int[][] squares_array) {

		int[] wanted = new int[square_length + 1];
		
		for(int i=0;i<total_squares;i++)
		{
			//initialize wanted numbers array
			for(int k=0;k<square_length;k++)
			{
				wanted[k] = k;
			}
			//if we find a number we set its wanted value to 0, if we find it twice return false
			for(int j=0;j<square_length;j++)
			{
				int val = squares_array[i][j];
				if(wanted[val] != -1)
				{
					wanted[val] = 0;
				}
				else
				{
					return false;
				}
			}
			//check square
			for(int l=0;l<wanted.length;l++)
			{
				if(wanted[l] == 1)
				{
					return false;
				}
				else
				{
					continue;
				}
			}
		}
		return true;
	}

	@Override
	public boolean checkRows(int[][] rows_array) {
		
		int[] wanted = new int[square_length];
		
		for(int j=0;j<rows_array.length;j++)
		{
			//initialize wanted array
			for(int i=0;i<wanted.length;i++)
			{
				wanted[i] = 1;
			}
			
			//check wanted numbers
			for(int k=0;k<square_length;k++)
			{
				int val = rows_array[j][k];
				if(wanted[val] == 0)
				{
					return false;
				}
				else
				{
					wanted[val] = 0;
				}
			}
			
			//check that all numbers where found
			for(int l=0;l<wanted.length;l++)
			{
				if(wanted[l] == 1)
				{
					return false;
				}
				else
				{
					continue;
				}
			}
		}
		
		return true;
	}

	@Override
	public boolean checkColumns(int[][] columns_array) {
		int[] wanted = new int[square_length];
		
		for(int j=0;j<columns_array.length;j++)
		{
			//initialize wanted array
			for(int i=0;i<wanted.length;i++)
			{
				wanted[i] = 1;
			}
			
			//check wanted numbers
			for(int k=0;k<square_length;k++)
			{
				int val = columns_array[j][k];
				if(wanted[val] == 0)
				{
					return false;
				}
				else
				{
					wanted[val] = 0;
				}
			}
			
			//check that all numbers where found
			for(int l=0;l<wanted.length;l++)
			{
				if(wanted[l] == 1)
				{
					return false;
				}
				else
				{
					continue;
				}
			}
		}
		
		return true;
	}

	@Override
	public boolean getResult(int [][] rows_array,int[][] col_array) {
		if(checkSquares(squares_array))
		{
			//if the squares are good check rows
			if(checkRows(rows_array))
			{
				//if the rows are good check columns
				if(checkColumns(col_array))
				{
					return true;
				}
			}
		}
		return false;
	}
	
}
