import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

//        long[] data = new long[6665000];
        long[] data = new long[2666000];

        for (int i = 0; i < 2666000; i++) {
            data[i] = 0L;
        }

        ArrayList<long[]> dataArray = new ArrayList<>();
        dataArray.add(data);
        dataArray.add(data);
        dataArray.add(data);
        dataArray.add(data);
        dataArray.add(data);

        int j = 1;
        while (j <= 8) {
            ArrayList<Thread> allWriters = new ArrayList<>();
            System.out.println("j is " + j);

            for (int n = 1; n <= j; n++){
//                System.out.println("n is " + n);
//                System.out.println("Started a client");
                ClientWriter clientWriter = new ClientWriter(n, dataArray);
                Thread newPrinter = new Thread(clientWriter);
                newPrinter.start();
                allWriters.add(newPrinter);
//                System.out.println("writer began: " + newPrinter.getId());
            }

            allWriters.forEach(writer -> {
                try {
                    writer.join();
//                    System.out.println("writer finished: " + writer.getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            try {
                System.out.println("Thread asleep");
               Thread.sleep(300000);
                System.out.println("Thread awakened");
            } catch (InterruptedException e){
                System.out.println("Couldn't sleep");
            }

            j = j * 2;
        }
        System.out.println("DONE");
    }
}
