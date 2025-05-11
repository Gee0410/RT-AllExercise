package org.Exercise6;

import java.io.IOException;

public class MyVolatile {
    public static void main(String[]args) throws IOException {
        MyThread myThread=new MyThread();
        myThread.start();

        System.out.println("Press ENTER to stop the thread...");
        System.in.read();

        myThread.shutdown();

    }
}