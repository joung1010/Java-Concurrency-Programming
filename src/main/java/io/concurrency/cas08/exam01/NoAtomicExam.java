package io.concurrency.cas08.exam01;

public class NoAtomicExam {
    private static int value = 0;

    private static int TREAD_NUM = 3;

    public static void main(String[] args) {
        Thread[] threads = new Thread[TREAD_NUM];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    int expectedValue = value;
                    int newValue = expectedValue + 1;
                    value = newValue;
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
        System.out.println("Final value: " + value);
    }
}
