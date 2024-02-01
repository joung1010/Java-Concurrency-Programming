package io.concurrency.threadApi03.exam01;

public class InterruptSleep {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Sleeps for 20 seconds");
                Thread.sleep(20000);
                System.out.println("The thread has awakened.");
            } catch (InterruptedException e) {
                System.out.println("the thread has interrupted.");
            }
        });
        thread.start();
        Thread.sleep(1000);

        thread.interrupt();
    }
}
