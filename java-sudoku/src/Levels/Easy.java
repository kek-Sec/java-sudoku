package Levels;

import Interfaces.LevelInterface;

public class Easy implements LevelInterface{

	public int rows,columns,total_squares;
	public int[] rowsArray;
	public int[] columnsArray;
	
	public Easy()
	{
		rows = 9;
		columns = 9;
		total_squares = rows*columns;
		rowsArray = new int[rows];
		columnsArray = new int[columns];
		
	}

	@Override
	public void generateRowsArray() {
		
		
	}

	@Override
	public void generateColumns() {
		
		
	}
	
	
}
