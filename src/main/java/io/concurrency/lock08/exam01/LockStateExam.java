package io.concurrency.lock08.exam01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStateExam {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("스레드1가 락을  1번 획득");
                lock.lock();
                try {
                    System.out.println("스레드1가 락을  2번 획득");

                    lock.lock();
                    try {
                        System.out.println("스레드1가 락을  3번 획득");
                    } finally {
                        lock.unlock();
                        System.out.println("스레드1가 락을  1번 해제");
                    }
                } finally {
                    lock.unlock();
                    System.out.println("스레드1가 락을  2번 해제");
                }
            } finally {
                lock.unlock();
                System.out.println("스레드1가 락을 3번 락 해제");
            }
        }).start();

        new Thread(() -> {
            lock.lock(); //스레드1이 락을 모두 해제할때가지 획득 못함
            try {
                System.out.println("스레드2가 락을 획득");
            }finally {
                lock.unlock();
                System.out.println("스레드2가 락을 해제");
            }

        }).start();
    }
}
