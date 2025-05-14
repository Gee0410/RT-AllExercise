package org.example.Exercise6;

public class MyThread extends Thread{
    private volatile boolean running=true;
    public void run(){
        while(running){
            System.out.println("Thread is running...");
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                System.out.println("Thread interrupted");
            }
        }
        System.out.println("Thread has stopped.");
    }

    public void shutdown(){
        running=false;
    }
}

