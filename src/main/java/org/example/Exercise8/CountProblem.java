package org.example.Exercise8;

import java.util.concurrent.atomic.AtomicInteger;

public class CountProblem implements Runnable{
    private AtomicInteger count=new AtomicInteger(0); // New modification
    @Override
    public void run(){
        for(int i=1;i<=5;i++){
            processSomething(i);
            count.incrementAndGet(); // New modification
        }
    }
    public int getCount(){
        return this.count.get(); // New modification
    }

    private void processSomething(int i){
        try{
            Thread.sleep(i*200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
