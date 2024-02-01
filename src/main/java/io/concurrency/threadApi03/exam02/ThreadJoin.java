package io.concurrency.threadApi03.exam02;

public class ThreadJoin {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("The thread will start after 3 seconds.");
                Thread.sleep(3000);
                System.out.println("thread is working~");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } );

        thread.start();

        System.out.println("The main thread waits for the work of another thread");
        try {
            thread.join(); // main 스레드가 thread의 작업이 끝날때까지 대기한다.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The main thread is working now~~~");
    }
}
