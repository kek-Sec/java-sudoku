package Metrics;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class History {

  //Get date in eu format
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  static Date date = new Date();

  //write victory to text file
  public static void writeVictory() {
    String victory = "[" + date + "]" + "-- [VICTORY]";
    boolean ok = write_gamefile(victory);
  }

  //write defeat to text file
  public static void writeDefeat() {
    String defeat = "[" + date + "]" + "-- [DEFEAT]";
    boolean ok = write_gamefile(defeat);
  }
  //write newGame to text file
  public static void writeNewGame() {
    String new_game = "[" + date + "]" + "-- [NEW GAME]";
    boolean ok = write_gamefile(new_game);
  }

  /*
	 Writes to a simple text file
	 @param to_write -> String to append to file as new line
	 */
  private static boolean write_gamefile(String to_write) {
    File local = new File("./history.sdk");
    try {
      FileWriter fr = new FileWriter(local, true);
      fr.write(to_write + '\n');
      fr.close();
      return true;
    } catch(Exception e) {
      return false;
    }
  }
}