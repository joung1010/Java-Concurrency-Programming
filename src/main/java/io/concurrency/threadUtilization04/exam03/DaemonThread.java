package io.concurrency.threadUtilization04.exam03;

public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread userThread = new Thread(() -> {
            new Thread(() -> {
                System.out.println("User Thread's child daemon status: " + Thread.currentThread().isDaemon());
            }).start();
            System.out.println("User Thread daemon status: " + Thread.currentThread().isDaemon());
        });

        Thread daemonThread = new Thread(() -> {
            new Thread(() -> {
                System.out.println("Daemon Thread's child daemon status: " + Thread.currentThread().isDaemon());
            }).start();
            System.out.println("Daemon Thread daemon status: " + Thread.currentThread().isDaemon());
        });
        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();
    }
}
