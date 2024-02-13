package io.concurrency.threadApi03.exam03;

public class InterruptedExam03 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Thread is Working!");
                if (Thread.interrupted()) {
                    System.out.println("Thread interrupt status initialization..");
                    break;
                }
            }
            System.out.println("Thread isInterrupted : " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt(); // 상태값 원복
            System.out.println("Thread isInterrupted : " + Thread.currentThread().isInterrupted());
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
