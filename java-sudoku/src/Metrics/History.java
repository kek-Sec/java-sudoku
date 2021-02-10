package Metrics;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class History {

	  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    static Date date = new Date();  
	
	public static void writeVictory()
	{
		String victory = "[" + date + "]" + "-- [VICTORY]";
		boolean ok = write_gamefile(victory);
	}
	
	public static void writeDefeat()
	{
		String defeat = "[" + date + "]" + "-- [DEFEAT]";
		boolean ok = write_gamefile(defeat);
	}
	
	public static void writeNewGame()
	{
		String new_game = "[" + date + "]" + "-- [NEW GAME]";
		boolean ok = write_gamefile(new_game);
	}
	
	/*
	 * 
	 */
	private static boolean write_gamefile(String to_write)
	{
		File local = new File("./history.sdk");
		try {
			FileWriter fr = new FileWriter(local, true);
			fr.write(to_write + '\n');
			fr.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
