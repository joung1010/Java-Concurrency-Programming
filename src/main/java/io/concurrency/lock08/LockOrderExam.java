package io.concurrency.lock08;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockOrderExam {

    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock1.lock();
            try {
                System.out.println("스레드가 1번 락 획득");
                lock2.lock();
                try {
                    System.out.println("스레드가 2번 락 획득");
                } finally {
                    lock1.unlock(); // 락에 순서에 상관이 없다. lock을 획득하고 해제하는 그 사이의 영역이 임계 영역이 되고 객체단위로 lock을 관리한다.
                    System.out.println("스레드가 1번 락 해제");
                }

            } finally {
                lock2.unlock();
                System.out.println("스레드가 2번 락 해제");
            }
        }).start();

        new Thread(() -> {
            lock1.lock();
            try {
                System.out.println("스레드가 1번 락 획득");
                lock2.lock();
                try {
                    System.out.println("스레드가 2번 락 획득");
                } finally {
                    lock2.unlock();
                    System.out.println("스레드가 2번 락 해제");
                }

            } finally {
                lock1.unlock();
                System.out.println("스레드가 1번 락 해제");
            }
        }).start();
    }

}
