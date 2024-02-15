package io.concurrency.threadUtilization04.exam02;

public class InterruptedThreadStop {
    public static void main(String[] args) {
        Thread workThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println("workThread interrupted status : "+ Thread.currentThread().isInterrupted());
            }
            System.out.println("workThread is Done... " );
            System.out.println("workThread interrupted status1 : " + Thread.currentThread().isInterrupted() );
            Thread.currentThread().interrupt();
            System.out.println("workThread interrupted status2 : " + Thread.currentThread().isInterrupted() );
        });

        Thread stopThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            workThread.interrupt();
            System.out.println("stopThread try to interrupt workThread!");
        });
        workThread.start();
        stopThread.start();
    }
}
