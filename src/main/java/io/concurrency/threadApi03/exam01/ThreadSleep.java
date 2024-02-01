package io.concurrency.threadApi03.exam01;

public class ThreadSleep {
    public static void main(String[] args) {
        try {
            System.out.println("The message will be displayed after 2 seconds.");
            Thread.sleep(2000);
            System.out.println("Hello Thread~");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
