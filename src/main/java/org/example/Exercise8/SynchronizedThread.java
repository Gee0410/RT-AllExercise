package org.example.Exercise8;

public class SynchronizedThread extends Thread{
    private Counter counter;
    private int increments;

    public SynchronizedThread(Counter counter,int increments){
        this.counter=counter;
        this.increments=increments;
    }

    @Override
    public void run(){
        for(int i=0;i<increments;i++){
            counter.synchronizedIncrement();
        }
    }
}
