package ejer4MultithreadClientServerAppSquareNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SquareClient {
    private static final Random RANDOM=new Random();
    private static final int NUMBER_OF_CLIENTS = 10;

    private ArrayList<String> options;

    public SquareClient() {
        completeArray();
    }

    public static void main(String[] args) {
        SquareClient client = new SquareClient();

        try(
                ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_CLIENTS)
                ){
            for (int i=0;i<NUMBER_OF_CLIENTS;i++){
                String value = client.options.get(RANDOM.nextInt(0, client.options.size()));
                threadPool.execute(new SquareClientWorker(value));
            }
        }
    }

    private void completeArray(){
        options = new ArrayList<>();

        // Add 30% words (9 elements)
        options.add("apple");
        options.add("banana");
        options.add("cherry");
        options.add("dog");
        options.add("elephant");
        options.add("fish");
        options.add("grape");
        options.add("hat");
        options.add("ice");

        // Add 70% integers (21 elements)
        for (int i = 1; i <= 21; i++) {
            options.add(String.valueOf(i));
        }
    }
}
