package io.concurrency.lock08.exam01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExam {

    private int count = 0;

    private Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }

    public int getCount() {
        // 스레드가 쓰기 작업을 수행하는 동안에는 읽기작업을 못할 수도 있다.
        lock.lock();
        try {
            return count;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockExam lockExam = new LockExam();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                lockExam.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                lockExam.increment();
            }
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Count : "+lockExam.getCount());
    }
}
