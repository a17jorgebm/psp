package org.example.proba1;

//podese facer extendendo a clase Thread, pero claro se queremos heredar doutra clase pai non podemos
public class SubclaseDeTread extends Thread{
    @Override
    public void run() {
        System.out.println("Thread da clase pai: "+this.toString());
        System.out.println("Prioridade so thread: "+this.getPriority());
    }
}