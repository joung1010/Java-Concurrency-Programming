package io.concurrency.threadApi03.exam05;

public class ThreadPriorityExam01 {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("Default Thread Priority : " + thread.getPriority());

        thread.start();

        Thread minThread = new Thread(() -> {
            System.out.println("Min Thread Priority: " + Thread.currentThread().getPriority());
        });
        minThread.setPriority(Thread.MIN_PRIORITY);
        minThread.start();

        Thread norThread = new Thread(() -> {
            System.out.println("Normal Thread Priority: " + Thread.currentThread().getPriority());
        });
        norThread.setPriority(Thread.NORM_PRIORITY);
        norThread.start();

        Thread maxThread = new Thread(() -> {
            System.out.println("MAX Thread Priority: " + Thread.currentThread().getPriority());
        });
        maxThread.setPriority(Thread.MAX_PRIORITY);
        maxThread.start();

    }
}
