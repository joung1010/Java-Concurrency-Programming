package io.concurrency.lock08.exam03;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockReadLockExam {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        BankAccount account = new BankAccount(lock, 10000);


        // 여러 읽기 스레드가 잔액을 조회
        // 읽기 락은 여러 스레드가 동시에 락을 획득
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int balance = account.getBalance();
                System.out.println(Thread.currentThread().getName() + " -현재 잔액: " + balance);
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int intVal = (int) (Math.random() * 1000);
                account.deposit(intVal);
                System.out.println(Thread.currentThread().getName() + " -입금: " + intVal);
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int balance = account.getBalance();
                System.out.println(Thread.currentThread().getName() + " -현재 잔액: " + balance);
            }).start();
        }
    }
}
