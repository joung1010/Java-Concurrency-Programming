package io.concurrency.threadApi03.exam02;

public class InterruptedThreadJoin {
    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread(); // 현재 작업을 수행하는 스레드의 정보를 가져옴

        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("The thread will wait very long time");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                mainThread.interrupt();
                System.out.println("the thread has interrupted.");
            }
        });

        thread.start();

        Thread interruptingThread = new Thread(() -> {
            try {
                System.out.println("A specific thread interrupts another thread before 2 seconds");
                Thread.sleep(2000);
                thread.interrupt();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        interruptingThread.start();

        try {
            System.out.println("The main thread waits for the work of another threads");
            thread.join();
            System.out.println("The main thread is finished");
        } catch (InterruptedException e) {
            System.out.println("main thread has interrupted.");
            throw new RuntimeException(e);
        }
    }
}
