package io.concurrency.thread02.exam01;

public class LambdaThreadExam {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": Thread(lambda) Running");
        }).start();
    }
}
