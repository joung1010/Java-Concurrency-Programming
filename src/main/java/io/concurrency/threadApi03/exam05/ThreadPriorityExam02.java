package io.concurrency.threadApi03.exam05;

public class ThreadPriorityExam02 {
    public static void main(String[] args) throws InterruptedException {

        CountingThread highPriorityThread = new CountingThread("high priority thread", Thread.MAX_PRIORITY);
        CountingThread normPriorityThread = new CountingThread("normal priority thread", Thread.NORM_PRIORITY);
        CountingThread minPriorityThread = new CountingThread("min priority thread", Thread.MIN_PRIORITY);

        highPriorityThread.start();
        normPriorityThread.start();
        minPriorityThread.start();

        highPriorityThread.join();
        normPriorityThread.join();
        minPriorityThread.join();

        System.out.println("Job finished...");
    }

    static class CountingThread extends Thread {
        private final String threadName;
        private int count = 0;
        public CountingThread(String threadName, int priority) {
            this.threadName = threadName;
            setPriority(priority);
        }

        @Override
        public void run() {
            while (count < 10000000) {
                count++;
            }
            System.out.println(threadName+" : "+ count);
        }
    }
}
