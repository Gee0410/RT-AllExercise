package org.example.Exercise12;

public class VolatileFlagExample {
    //Shared flag between threads
    private static boolean running=true;

    public synchronized static boolean isRunning() {
        return running;
    }

    public synchronized static void stopRunning() {
        running = false;
    }

    public static void main(String[] args) {
        //Thread that runs continuously until 'running' becomes false
        Thread worker=new Thread(() ->{
            System.out.println("Worker thread started...");
            while (isRunning()){
                //simulate work
            }
            System.out.println("Worker thread stopped.");
        });

        worker.start();

        //Main thread sleeps for a bit then signals stop
        try{
            Thread.sleep(2000);

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        stopRunning();
    }
}
