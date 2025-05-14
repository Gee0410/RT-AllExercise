package org.example.Exercise8;

public class ThreadComparison {
    private static final int NUM_THREADS=10;
    private static final int INCREMENTS_PER_THREAD=1000;

    public static void main(String[] args) throws InterruptedException{
        Counter normalCounter=new Counter();
        Thread[] normalThreads=new Thread[NUM_THREADS];

        long startTime=System.nanoTime();

        for(int i=0;i<NUM_THREADS;i++){
            normalThreads[i]=new NormalThread(normalCounter,INCREMENTS_PER_THREAD);
            normalThreads[i].start();
        }

        long endTime = System.nanoTime();
        double normalDuration = (endTime - startTime) / 1_000_000_000.0;

        System.out.println("Execution time:");
        System.out.printf("Normal Threads: %.8f seconds\n", normalDuration);

        Counter syncCounter = new Counter();
        Thread[] syncThreads = new Thread[NUM_THREADS];

        startTime = System.nanoTime();

        for (int i = 0; i < NUM_THREADS; i++) {
            syncThreads[i] = new SynchronizedThread(syncCounter,INCREMENTS_PER_THREAD);
            syncThreads[i].start();
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            syncThreads[i].join();
        }

        endTime = System.nanoTime();
        double syncDuration = (endTime-startTime)/1_000_000_000.0;

        System.out.printf("Synchronized Threads: %.8f seconds\n",syncDuration);
    }
}
