package org.example.proba1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //non podemos controlar a orden na que se execu

        InfoRunnable ir=new InfoRunnable();
        Thread t1=new Thread(ir);
        t1.start();
        Thread t2=new Thread(ir);
        t2.start();

        //con nome
        Thread t3=new Thread(ir,"T3");
        t3.run(); //aqui ejecuta o codigo de InfoRunnable, pero no thread do main, non nun novo
        t3.start(); //aqui si se ejecuta nun novo thread

        Thread t4=new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Anonimo");
                    }
                }
        );
        t4.start();

        //con lambda, para non crear objetos
        Thread t5=new Thread(()->{
            System.out.println("ola");
        });
        t5.start();

        //con subclase de Thread
        SubclaseDeTread t6=new SubclaseDeTread();
        t6.start();
        t6.join(); //espera NON ACTIVA do thread, é dicir que o main non consume recursos mentras espera polo thread

        //espera ACTIVA, o main consume mentras espera. NON FACER ESTOOOOO!!!!
        while (t6.isAlive()){
            Thread.sleep(200);
        }
    }
}