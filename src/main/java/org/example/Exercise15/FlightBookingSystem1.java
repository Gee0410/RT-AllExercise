package org.example.Exercise15;

import java.util.concurrent.*;
import java.util.Random;

public class FlightBookingSystem1{

    private static final int NUM_AGENCIES = 3;
    private static CyclicBarrier barrier;

    public static void main(String[] args) {
        // Define the barrier action (runs once all threads reach the barrier)
        Runnable barrierAction = () -> {
            System.out.println("\n[System] All agencies have selected seats. Confirming bookings...");
            try {
                Thread.sleep(1000); // Simulate confirmation delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[System] Bookings have been successfully confirmed.\n");
        };

        // Initialize the barrier with the number of parties and the barrier action
        barrier = new CyclicBarrier(NUM_AGENCIES, barrierAction);

        // Create and start threads for each booking agency
        for (int i = 1; i <= NUM_AGENCIES; i++) {
            Thread agencyThread = new Thread(new BookingAgency("Agency-" + i));
            agencyThread.start();
        }
    }

    // Inner class representing a booking agency
    static class BookingAgency implements Runnable {
        private final String name;
        private final Random random = new Random();

        public BookingAgency(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + ": Selecting seats...");
                Thread.sleep(random.nextInt(3000)); // Simulate seat selection time
                System.out.println(name + ": Finished selection, waiting for others...");

                // Wait at the barrier
                barrier.await();

                // After confirmation
                System.out.println(name + ": Proceeding with next steps.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

