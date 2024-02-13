package io.concurrency.threadApi03.exam03;

public class InterruptedExceptionExam {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("thread interrupted 1 : "+ Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted....");
                System.out.println("thread interrupted 2 : "+ Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }
        });


        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread try to interrupt thread");
        thread.interrupt();
        try {
            thread.join();
            System.out.println("thread interrupted 3 : "+ thread.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
