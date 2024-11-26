package org.example;

public class Producer implements Runnable{
    private Resource resource;

    public Producer(Resource resource){
        this.resource=resource;
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            try{
                int val=resource.get();
                System.out.println("The consumer got "+val);
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
        }
    }
}
