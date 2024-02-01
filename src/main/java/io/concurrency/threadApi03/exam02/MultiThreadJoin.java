package io.concurrency.threadApi03.exam02;

public class MultiThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("The thread1 will start after 2 seconds.");
                Thread.sleep(2000);
                System.out.println("thread1 is working~");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("The thread2 will start after 3 seconds.");
                Thread.sleep(3000);
                System.out.println("thread2 is working~");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();
        thread2.start();
        System.out.println("The main thread waits for the work of another threads");

        thread1.join();
        thread2.join();

        System.out.println("The main thread is working now~~~");
    }
}
