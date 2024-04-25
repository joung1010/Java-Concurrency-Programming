package io.concurrency.lock08.exam06;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExam {
    private Lock lock = new ReentrantLock();
    private Condition con = lock.newCondition();

    private boolean flag = false;

    public void awaiting() {
        lock.lock();
        try {
            while (!flag) {
                System.out.println("조건 충족하지 못해서 대기함");
                con.await();
            }
            System.out.println("임계영역 작업 수행");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void signaling() {
        lock.lock();
        try {
            flag = true;
            System.out.println("조건을 만족 시키고 깨움");
            con.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionExam conditionExam = new ConditionExam();

        Thread thread1 = new Thread(() -> {
            conditionExam.awaiting();
        });

        Thread thread2 = new Thread(() -> {
            conditionExam.signaling();
        });

        thread1.start();
        Thread.sleep(2000);
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
