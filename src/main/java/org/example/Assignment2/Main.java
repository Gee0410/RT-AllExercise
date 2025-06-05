package org.example.Exercise11;

public class Main {
    public static void main(String[] args) {
        BankAccountWithLock account=new BankAccountWithLock(1000.0);

        //Thread for reading balance
        Runnable readTask=()->{
            for(int i=0;i<5;i++){
                account.getBalance();
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        };

        //Thread for depositing money
        Runnable depositTask=()->{
            for(int i=0;i<3;i++){
                account.deposit(200.0);
                try{
                    Thread.sleep(150);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        };

        //Thread for withdrawing money
        Runnable withdrawTask=()->{
            for(int i=0;i<4;i++){
                account.withdraw(150.0);
                try{
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1=new Thread(readTask,"Reader-1");
        Thread t2=new Thread(readTask,"Reader-2");
        Thread t3=new Thread(depositTask,"Depositor");
        Thread t4=new Thread(withdrawTask,"Withdrawer");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Final Balance: "+account.getBalance());
    }
}
