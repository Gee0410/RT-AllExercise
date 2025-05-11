package org.Exercise8;

public class Counter {
    private int count=0;

    public void increment(){
        count++;
    }

    public synchronized void synchronizedIncrement(){
        count++;
    }

    public int getCount(){
        return count;
    }
}
