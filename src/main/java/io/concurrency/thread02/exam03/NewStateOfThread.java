package io.concurrency.thread02.exam03;

public class NewStateOfThread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread Running");
        });
        System.out.println("Thread State: " + thread.getState()); //Thread State: NEW
    }
}
