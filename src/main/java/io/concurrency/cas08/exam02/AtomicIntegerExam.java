package io.concurrency.cas08.exam02;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExam {
    private static AtomicInteger counter = new AtomicInteger(0);

    private static int TREAD_NUM = 5;

    private static int NUM_INCREMENTS = 10000;

    public static void main(String[] args) {
        Thread[] threads = new Thread[TREAD_NUM];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_INCREMENTS; j++) {
                    counter.incrementAndGet();
                    System.out.println("counter = " + counter.get());
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Final value: " + counter.get());
    }
}
