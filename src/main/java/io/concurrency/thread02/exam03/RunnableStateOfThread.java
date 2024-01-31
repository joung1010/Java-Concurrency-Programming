package io.concurrency.thread02.exam03;

public class RunnableStateOfThread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                for (int i = 0; i < 1000000000; i++) {
                    if (i % 1000000000 == 0) {
                        System.out.println("Thread State: " + Thread.currentThread().getState()); // RUNNABLE
                    }
                }
            }
        });
        thread.start();
    }
}
