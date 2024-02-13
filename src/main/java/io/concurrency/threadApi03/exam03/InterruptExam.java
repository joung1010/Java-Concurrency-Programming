package io.concurrency.threadApi03.exam03;

public class InterruptExam {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread1 is start");
            System.out.println("Thread1 isInterrupted: " + Thread.currentThread().isInterrupted());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread2 try to interrupt Thread1");
            thread1.interrupt();
            System.out.println("Thread2 isInterrupted: " + Thread.currentThread().isInterrupted());

        });
        thread2.start();
        Thread.sleep(1000);
        thread1.start();

        thread1.join();
        thread2.join();

        System.out.println("All Thread is Done");
    }
}
