package org.example.Exercise4;

public class ThreadRunnable {
    public static void main(String[] args) {
      Thread t=new Thread();
      t.start();
        System.out.println(t.getState());
    }
}
