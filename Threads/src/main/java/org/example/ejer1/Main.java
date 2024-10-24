package org.example.ejer1;

/*
DU2 - Exercise 1 - Java Threads - Runnable

Create a Java class that implements the Runnable interface.

The run() method of the class must do the following:

Display a welcome message with the name of the current thread.
Repeat five times:
Get a random number between 10 and 500 (use java.util.Random).
Pause the execution of the current thread for the number of miliseconds equal to the random number obtained above.
Display a goodbye message with the name of the current thread.
Create a Java executable class to launch two threads created using the previous class. Thie main thread waits for
 the other two threads to finish and then displays a message indicating that it has finished.
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ShowMessagesAndWaitThread tipoThread=new ShowMessagesAndWaitThread();
        Thread t1=new Thread(tipoThread);
        Thread t2=new Thread(tipoThread);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Programa finalizado");
    }
}
