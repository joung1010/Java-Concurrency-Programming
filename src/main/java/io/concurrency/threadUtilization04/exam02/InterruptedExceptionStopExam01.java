package io.concurrency.threadUtilization04.exam02;

public class InterruptedExceptionStopExam01 {
    public static void main(String[] args) {
        Thread workThread = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("workThread interrupted status : " + Thread.currentThread().isInterrupted());
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException occurred! : " + e);
                System.out.println("current workThread interrupted status : " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }

            System.out.println("workThread finished!");
            System.out.println("finished workThread interrupted status : " + Thread.currentThread().isInterrupted());
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
