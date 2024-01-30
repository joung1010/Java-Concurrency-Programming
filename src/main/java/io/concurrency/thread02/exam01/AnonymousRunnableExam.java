package io.concurrency.thread02.exam01;

public class AnonymousRunnableExam {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": Thread(anonymousRunnable) Running");
            }
        }).start();
    }
}
