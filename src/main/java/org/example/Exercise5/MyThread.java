package org.Exercise5;

public class MyThread extends Thread {
    private int threadNumber;

    public MyThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        printMessages();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printMessages() {
        System.out.println("One        from thread "+threadNumber);
        System.out.println("Two        from thread "+threadNumber);
        System.out.println("Three      from thread "+threadNumber);
        System.out.println("XXXXXXXXXX from thread "+threadNumber);
    }
}
