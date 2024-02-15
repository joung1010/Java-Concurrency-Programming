package io.concurrency.threadUtilization04.exam02;

import java.util.concurrent.atomic.AtomicBoolean;

public class FlagThreadInterruptExam02 {
    private static AtomicBoolean running  = new AtomicBoolean(true);
    public static void main(String[] args) {

        new Thread(() -> {
            int count = 0;
            while (running.get()) {
                count ++;
            }
            System.out.println("Thread 1 is Done.. : "+ count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 2 is Done..  ");
            running.set(false);
        }).start();

    }
}
