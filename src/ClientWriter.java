import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientWriter implements Runnable {
  int clientNumber;
  ArrayList<long[]> text;

  public ClientWriter(int n, ArrayList<long[]> data){
    clientNumber = n;
    text = data;
  }

  public void run() {
    String dir = String.format("/Users/williamgagne/Desktop/%s.txt", clientNumber);
    File file = new File(dir);

    try (
        FileWriter fw = new FileWriter(file);
        PrintWriter out = new PrintWriter(fw)) {
      System.out.println("BEFORE CLIENT #" + clientNumber + " - Time: "
          + java.time.LocalTime.now() + " MilleSec: " + System.currentTimeMillis());

      while (file.length() == 0 || file.length() == 0L) {
        for (long[] longs : text) {
          out.print(Arrays.toString(longs));
          out.flush();
        }
      }
      out.close();

//      System.out.println("CLIENT #" + clientNumber + " has size " + file.length() + " and path "
//          + "is " + file.getAbsolutePath());

      System.out.println("AFTER CLIENT #" + clientNumber + " - Time: "
          + java.time.LocalTime.now() + " MilleSec: " + System.currentTimeMillis());

    } catch (IOException exception) {
      System.out.println("Could not write to : " + dir);
    }
  }
}


