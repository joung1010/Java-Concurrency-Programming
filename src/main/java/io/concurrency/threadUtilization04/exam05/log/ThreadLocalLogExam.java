package io.concurrency.threadUtilization04.exam05.log;

public class ThreadLocalLogExam {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new LogWorker(), "Thread-1");
        Thread thread2 = new Thread(new LogWorker(), "Thread-2");
        Thread thread3 = new Thread(new LogWorker(), "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
