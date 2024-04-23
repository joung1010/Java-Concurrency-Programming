package io.concurrency.lock08.exam03;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExam {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static String data = "Initial Data";
    public static void main(String[] args) {
        new Thread(() -> {
            lock.writeLock().lock();
            try {
                data = "Updated data";
            }finally {
                lock.writeLock().unlock();
            }
        }).start();

        new Thread(() -> {
            lock.readLock().lock();
            System.out.println("Record thread1 : " + data);
            lock.readLock().unlock();
        }).start();

        new Thread(() -> {
            lock.readLock().lock();
            System.out.println("Record thread2 : " + data);
            lock.readLock().unlock();
        }).start();
    }
}
