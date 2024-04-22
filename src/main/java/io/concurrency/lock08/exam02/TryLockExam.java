package io.concurrency.lock08.exam02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExam {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            boolean aquired = false;
            while (!aquired) {
                aquired = lock.tryLock();
                if (aquired) {
                    System.out.println("스레드1 이 락을 획득");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                        System.out.println("스레드1 이 락을 해제");
                    }
                } else {
                    System.out.println("스레드 1이 락을 획득하지 못함!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            boolean aquired = false;
            while (!aquired) {
                aquired = lock.tryLock();
                if (aquired) {
                    try {
                        System.out.println("스레드2 가 락을 획득");
                    } finally {
                        System.out.println("스레드2 가 락을 해제");
                        lock.unlock();
                    }
                } else {
                    System.out.println("스레드2 가 락을 획득하지 못함!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}
