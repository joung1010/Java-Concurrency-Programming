package io.concurrency.threadApi03.exam03;

public class InterruptedExam02 {
    public static void main(String[] args) {
        Thread thread2 = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println("Thread2 is Working!");
            }
            System.out.println("Thread2 is interrupted");
            System.out.println("Thread2 isInterrupted : " + Thread.currentThread().isInterrupted());
        });

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread1 is working!");
                if (i == 2) {
                    System.out.println("Thread1 try to interrupt Thread2");
                    thread2.interrupt();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}
