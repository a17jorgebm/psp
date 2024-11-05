package org.example.poolDeThreads;

public class RunnableGreet implements Runnable{
    private String nome;

    public RunnableGreet(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {
        System.out.println("Welcome "+nome+"!");
    }
}
