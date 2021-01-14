package SudokuGenerator;

import java.util.ArrayList;
import java.util.Collections;

public class GeneratorUtilities {
	
	//create list of unique numbers
	/*
	 * @param limit the max number
	 * @param how_many the ammount of numbers
	 */
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
