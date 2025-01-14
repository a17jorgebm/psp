package org.example.proba1;

//mellor implementar a interfaz Runnable, que ao final é o que fai a clase Thread tamen
public class InfoRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("hola " + Thread.currentThread().toString());
    }
}
