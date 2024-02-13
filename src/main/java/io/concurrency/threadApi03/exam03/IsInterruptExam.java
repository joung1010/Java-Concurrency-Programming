package io.concurrency.threadApi03.exam03;

public class IsInterruptExam {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread is Working!");
            }
            System.out.println("Thread is interrupted");
            System.out.println("Thread isInterrupted : " + Thread.currentThread().isInterrupted()); //Thread isInterrupted : true
        });
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }
}
