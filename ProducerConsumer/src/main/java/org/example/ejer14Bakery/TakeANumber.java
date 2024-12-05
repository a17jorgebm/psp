package org.example.ejer14Bakery;

public class TakeANumber {
    private static final int MAX_CLIENTS_TO_SERVE=100;

    int currentNumber;
    int lastNumberTaken;

    public TakeANumber() {
        this.currentNumber=1;
        this.lastNumberTaken=0;
    }

    synchronized void takeNumberAndWait(Customer customer) throws InterruptedException{

        customer.setTicketNumber(++lastNumberTaken);
        notifyAll(); //avisa al panadero de que alguien entró
        System.out.println(String.format("Customer %d takes ticket %d",customer.getClientId(),customer.getTicketNumber()));

        while (currentNumber!=customer.getTicketNumber()){
            wait();
        }

        System.out.println(String.format("Customer %d with ticker %d served",customer.getClientId(),customer.getTicketNumber()));
    }

    synchronized void callCustomer() throws InterruptedException{
        while (currentNumber>lastNumberTaken){
            System.out.println("Clerk waiting (there are no clients to serve)");
            wait();
        }
        System.out.println("Clerk serving ticket "+currentNumber);
        currentNumber++;
        notifyAll();
    }
}
