package io.concurrency.threadApi03.exam01;

public class LoopSleep {
    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            try {
                System.out.println("Loop Count "+ i);
                Thread.sleep(1000);
                System.out.println("Hello Thread~");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
