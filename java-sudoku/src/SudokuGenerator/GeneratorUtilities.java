package SudokuGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GeneratorUtilities {
	
	//create list of unique numbers
	/*
	 * @param limit the max number
	 * @param how_many the amount of numbers
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
        	//System.out.println(list.get(i));
        	to_return[i] = list.get(i);
        }
		return to_return;
		
	}
	
	/*
	 * @param arr ->  array
	 * @param to_check -> int to check if exists
	 */
	public static boolean ExistsInArr(int[] arr,int to_check)
	{
		return Arrays.stream(arr).anyMatch(i -> i == to_check);
	}
}
