package io.concurrency.lock08.exam03;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FairnessExam {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private static int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread writerThread = new Thread(() -> {
                try {
                    lock.writeLock().lock();
                    count++;
                    System.out.println("writer thread update count : " + count);
                } finally {
                    lock.writeLock().unlock();
                }
            });

            Thread readLock = new Thread(() -> {
                lock.readLock().lock();
                System.out.println("Reader Thread : " + count);
                lock.readLock().unlock();
            });

            writerThread.start();
            readLock.start();
        }
    }
}
