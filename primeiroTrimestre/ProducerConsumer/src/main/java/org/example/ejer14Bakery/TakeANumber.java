package org.example.ejer14Bakery;

import java.util.Random;

public class TakeANumber {
    private static final int MAX_CLIENTS_TO_SERVE=100;
    private static final Random random=new Random();

    int currentNumber;
    int lastNumberTaken;
    int numberOfCustomersInLine;
    boolean breadOfCurrentCustomerIsReadyToTake;

    public TakeANumber() {
        this.currentNumber=0;
        this.lastNumberTaken=0;
        this.numberOfCustomersInLine=0;
        this.breadOfCurrentCustomerIsReadyToTake=false;
    }

    synchronized void takeNumberAndWait(Customer customer) throws InterruptedException{
        customer.setTicketNumber(++lastNumberTaken); //customer takes number
        if (numberOfCustomersInLine==0){
            System.out.println("Calling clerk");
            notifyAll(); //if no one was waiting to be served, we notify the clerk that someone entered the bakery
        }
        numberOfCustomersInLine++;

        System.out.println(String.format("Customer %d takes ticket %d",customer.getClientId(),customer.getTicketNumber()));

        //while the Customer number is not the current number to be served, or the clerk is not ready to give the bread, the Customer waits
        while (currentNumber!=customer.getTicketNumber() || !breadOfCurrentCustomerIsReadyToTake){
            wait();
        }

        //we update the number of customers waiting
        numberOfCustomersInLine--;
        breadOfCurrentCustomerIsReadyToTake=false;

        System.out.println(String.format("Customer %d with ticket %d served",customer.getClientId(),customer.getTicketNumber()));

        notifyAll(); //we notify the clerk so he attends next customer
    }

    synchronized void callCustomers() throws InterruptedException{
        for (int i=0;i<MAX_CLIENTS_TO_SERVE;i++){
            if (numberOfCustomersInLine==0){ //if there are no customers in the bakery, waits for them to come
                System.out.println("Clerk waiting (there are no clients to serve)");
                wait();
            }
            currentNumber++;
            System.out.println("Clerk serving ticket "+currentNumber);
            Thread.sleep(random.nextInt(1001));
            breadOfCurrentCustomerIsReadyToTake=true;
            notifyAll();
            wait();
        }
    }
}
