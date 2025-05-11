package org.Exercise8;

public class NormalThread extends Thread{
    private Counter counter;
    private int increments;

    public NormalThread(Counter counter,int increments){
        this.counter=counter;
        this.increments=increments;
    }

    @Override
    public void run(){
        for(int i=0;i<increments;i++){
            counter.increment();
        }
    }
}
