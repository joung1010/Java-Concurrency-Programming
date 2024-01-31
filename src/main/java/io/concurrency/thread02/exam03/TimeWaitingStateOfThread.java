package io.concurrency.thread02.exam03;

public class TimeWaitingStateOfThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread.sleep(100);
        System.out.println("Thread State: " + thread.getState()); //Thread State: TIMED_WAITING
    }
}
