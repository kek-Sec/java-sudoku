package SudokuGenerator;

import java.util.Random;

public class PuzzleGenerator {

	int[][] rows_array;
	int[][] columns_array;
	int rows;
	int columns;
	int width;
	int height;
	
	/*		HOW A PUZZLE IS STORED IN ROWS_ARRAY
	 * 				3x3 example
	 * 		[0] -> [x,x,x,x,x,x,x,x,x]
	 * 		[1] -> [x,x,x,x,x,x,x,x,x]
	 * 
	 */
	
	
	public PuzzleGenerator()
	{

	}
	
	public void generate3x3()
	{
		int random;
		rows_array = new int[9][9];
		
		boolean is_ready = false;
		while(!is_ready)
		{
			random = new Random().nextInt(10);
			
		}
		

	}
	
	public void generate12x12()
	{
		
	}
	
	public void generate16x16()
	{
		
	}
	
	public void generate25x25()
	{
		
	}
	
	
}