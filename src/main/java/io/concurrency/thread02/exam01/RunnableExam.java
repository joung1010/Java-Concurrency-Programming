package io.concurrency.thread02.exam01;

public class RunnableExam {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Thread(runnable) Running");
    }
}
