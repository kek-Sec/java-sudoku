package Levels;

import java.util.ArrayList;
import java.util.Collections;

public class LevelsUtilities {

	//get unique random numbers in range
	//used to fill sudoku on new game
	@SuppressWarnings("deprecation")
	public static int[] getUniqueRandomNumbers(int limit,int how_many)
	{
		int[] to_return = new int[how_many];
		ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<limit; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<how_many; i++) {
        	to_return[i] = list.get(i);
        }
		return to_return;
		
	}
	
}