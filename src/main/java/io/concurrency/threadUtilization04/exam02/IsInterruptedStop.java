package io.concurrency.threadUtilization04.exam02;

public class IsInterruptedStop {
    public static void main(String[] args) throws InterruptedException {
        Thread workThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("workThread interrupted status : "+ Thread.currentThread().isInterrupted());
            }
            System.out.println("workThread is Done... :"+ Thread.currentThread().isInterrupted() );
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

        workThread.join();
        stopThread.join();
    }
}
