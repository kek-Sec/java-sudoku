package Levels;

import Interfaces.LevelInterface;

public class Easy implements LevelInterface{

	public int squares_to_fill,rows,columns,total_numbers,square_height,square_width;
	public int[][] rowsArray;
	public int[][] columnsArray;
	
	public Easy()
	{
		//squares to fill on game start
		squares_to_fill = 35;
		//height of individual square
		square_height = 3;
		//width of individual square
		square_width = 3;
		//number of rows
		rows = 9;
		//number of columns
		columns = 9;
		//total numbers in the puzzle
		total_numbers = rows*columns;
		//each row has its own array
		rowsArray = new int[rows][rows];
		//each column has its own array
		columnsArray = new int[columns][columns];
		
	}

	@Override
	public int[][] generateRowsArray() {
		
		return null;
		
	}

	@Override
	public int[][] generateColumnsArray() {
		
		return null;
		
	}
	
	
}