package io.concurrency.cas08.exam01;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadCasExam {
    private static AtomicInteger value = new AtomicInteger(0);

    private static int TREAD_NUM = 3;

    public static void main(String[] args) {
        Thread[] threads = new Thread[TREAD_NUM];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    int expectedValue, newValue;
                    do {
                        expectedValue = value.get();
                        newValue = expectedValue + 1;
                    } while (!value.compareAndSet(expectedValue, newValue));

                    System.out.println(Thread.currentThread().getName() + ": " + expectedValue + ":" + newValue);
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
        System.out.println("Final value: " + value.get());
    }
}
