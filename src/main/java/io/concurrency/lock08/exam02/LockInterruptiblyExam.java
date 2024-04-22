package io.concurrency.lock08.exam02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExam {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {

            try {
                lock.lockInterruptibly();
                System.out.println("스레드1 이 락을 획득");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("스레드1에 인터럽트 발생!");
            } finally {
                lock.unlock();
                System.out.println("스레드1 이 락을 해제");
            }
        });

        Thread thread2 = new Thread(() -> {

            try {
                lock.lockInterruptibly();
                System.out.println("스레드2 이 락을 획득");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("스레드2에 인터럽트 발생!");
            } finally {
                lock.unlock();
                System.out.println("스레드2 이 락을 해제");
            }
        });

        thread1.start();
        thread2.start();

        thread1.interrupt();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
