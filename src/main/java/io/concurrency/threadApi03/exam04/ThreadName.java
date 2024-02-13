package io.concurrency.threadApi03.exam04;

public class ThreadName {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            System.out.println("Thread Name (Constructor): "+ Thread.currentThread().getName());
        },"myThread");

        thread1.start();

        Thread thread2 = new Thread(()->{
            System.out.println("Thread Name (setName): "+ Thread.currentThread().getName());
        });
        thread2.setName("yourThread");
        thread2.start();

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                System.out.println("Thread Name (default): " + Thread.currentThread().getName());
            });
            thread.start();
        }
        Thread.sleep(2000);
    }
}
