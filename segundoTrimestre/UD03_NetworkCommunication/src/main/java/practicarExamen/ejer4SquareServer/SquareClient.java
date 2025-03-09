package practicarExamen.ejer4SquareServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SquareClient {
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) {
        try(
                ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        ){
            for (int i=0;i<1000;i++){
                threadPool.execute(new ClientWorker());
            }

            threadPool.shutdown();
        }
    }
}
