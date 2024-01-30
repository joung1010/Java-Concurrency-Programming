package io.concurrency.thread02.exam01;

public class AnonymousThreadExam {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": Thread(anonymous) Running");
            }
        };
        thread.start();
    }
}
