package io.concurrency.thread02.exam03;

public class WaitingStateOfThread {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        thread.sleep(100);
        System.out.println("Thread State: " + thread.getState()); //Thread State: WAITING
    }
}
