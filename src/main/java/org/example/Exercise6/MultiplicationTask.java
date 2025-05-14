package org.example.Exercise6;

public class MultiplicationTask extends Thread{
    private int number;

    public MultiplicationTask(int number){
        this.number=number;
    }

    @Override
    public void run(){
        for(int i=1;i<=3;i++){
            System.out.println("Thread-"+number+":"+(number+1)+"*"+i+"="+((number+1)*i));
        }
    }

    public static void main(String[] args) {
        Thread t1=new MultiplicationTask(0);
        Thread t2=new MultiplicationTask(1);
        Thread t3=new MultiplicationTask(2);

        t1.start();
        t2.start();
        t3.start();
    }
}