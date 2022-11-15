public class main {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 1) {
            System.out.println("i is " + i);
            for (int n = 1; n <= i; ++n){
                System.out.println("Started a client");
                clientWriter clientWriter = new clientWriter(n);
                Thread newPrinter = new Thread(clientWriter);
                newPrinter.start();
            }
            i = i * 2;

            try {
                System.out.println("Thread asleep");
                Thread.sleep(30000);
                System.out.println("Thread awakened");
            } catch (InterruptedException e){
                System.out.println("Couldn't sleep");
            }
        }
        System.out.println("DONE");
    }
}
