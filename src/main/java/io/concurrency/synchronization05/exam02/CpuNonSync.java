package io.concurrency.synchronization05.exam02;

public class CpuNonSync {
    private static int count = 0;
    private final static int ITERATIONS = 100000;
    public static void main(String[] args) throws InterruptedException { // 원자성 보장 X
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Expected Result : " + (2 * ITERATIONS));
        System.out.println("Result : " + count);
    }
}
