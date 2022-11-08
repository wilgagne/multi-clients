import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("WELCOME TO GUESS THE THREADS");
        boolean play = true;
        while (play) {
            System.out.println("How many threads would you like to create?");
            int n = Integer.parseInt(reader.readLine());
            System.out.println("Guess the order of the threads\n" +
                    "Enter your guess in the following format: 12435\n" +
                    "__________________________________________________");
            String guess = reader.readLine();
            int[] arr = new int[n];
            Thread[] threadArr = new Thread[n];
            for (int i = 1; i < n + 1; i++) {
                int finalI = i;
                int[] finalArr = arr;
                Thread sub = new Thread(() -> {
                    // Thread safety, execute together, -lock
                    synchronized (finalArr){
                    finalArr[0] = finalArr[0] + 1;
                    System.out.println(finalArr[0]);
                }});

                Thread sub2 = new Thread(() -> {
                    // Thread safety, execute together, -lock
                    synchronized (finalArr){
                        finalArr[0] = finalArr[0] + 1;
                        System.out.println(finalArr[0]);
                    }});
                threadArr[i - 1] = sub;
                sub.start();
            }
            for (int i = 1; i < n + 1; i++) {
                threadArr[i - 1].join();
            }
            System.out.println("The order is " + Arrays.toString(arr) + "\n");

            int k = 0;
            for (int i : arr) {
                k = 10 * k + i;
            }

            if (guess.equals(String.valueOf(k))) {
                System.out.println("You guessed it right :D\n");
            } else {
                System.out.println("Your guess was wrong :(");
            }
            System.out.println("Play again? answer Y or N");
            String answer = reader.readLine();
            if (!answer.equals("Y")) {
                play = false;
            }
        }
    }
}
