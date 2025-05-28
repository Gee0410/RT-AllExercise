package org.example.Exercise12;

//No visible different between notify() & notifyAll()
public class ProducerConsumerDemo {
    static class SharedData{
        private boolean dataReady=false;
        private String data;

        //Producer thread
        public synchronized void produce(){
            try{
                System.out.println("Producer: Preparing data...");
                Thread.sleep(1000); //Simulate time to produce data
                data="Hello from producer";
                dataReady=true;
                System.out.println("Producer: Data is ready.");
                notifyAll(); //Notify waiting consumer
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        //Consumer thread
        public synchronized void consume(){
            try{
                while (!dataReady){
                    System.out.println("Consumer:Waiting for data...");
                    wait();
                }
                System.out.println("Consumer:Received -> "+data);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        // Producer thread
        Thread producer = new Thread(() -> sharedData.produce());

        // Consumer thread
        Thread consumer = new Thread(() -> sharedData.consume());

        // Start threads
        consumer.start();
        producer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main: Producer and Consumer have finished execution.");
    }
}
