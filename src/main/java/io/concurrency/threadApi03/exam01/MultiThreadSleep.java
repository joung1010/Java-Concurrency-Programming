package io.concurrency.threadApi03.exam01;

public class MultiThreadSleep {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("The message will be displayed after 1 seconds.");
                Thread.sleep(1000);
                System.out.println("The thread1 has awakened.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("The message will be displayed after 2 seconds.");
                Thread.sleep(2000);
                System.out.println("The thread2 has awakened.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        System.out.println("This is Main Thead");
    }
}
