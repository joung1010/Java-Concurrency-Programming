package io.concurrency.thread02.exam03;

public class TerminatedStateOfThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Thread Running");
        });
        thread.start();
        thread.join();
        System.out.println("Thread State: " + thread.getState()); //Thread State: TERMINATED
    }
}
