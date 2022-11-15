import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class clientWriter implements Runnable {
  private static String dir = null;
  int clientNumber;

  public clientWriter(int n){
    clientNumber = n;
    dir = String.format("/pathfs/%s.txt", n);
  }

  public void run() {
    try (
        FileWriter fw = new FileWriter(dir, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)) {
      // Need to write 100MB here
      System.out.println("BEFORE CLIENT #" + clientNumber + " - Time: "
          + java.time.LocalTime.now() + " MilleSec: " + System.currentTimeMillis());
      ArrayList<Long> data = new ArrayList<>();
      for (int i = 0; i < 8335000; i++){
        data.add(0L);
      }
      for (int i = 0; i < 4; i++) {
        out.print(data);
      }
      System.out.println("AFTER CLIENT #" + clientNumber + " - Time: "
          + java.time.LocalTime.now() + " MilleSec: " + System.currentTimeMillis());
    } catch (IOException exception) {
      System.out.println("Could not write to : " + dir);
    }
  }
}


