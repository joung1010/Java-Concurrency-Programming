package io.concurrency.lock08.exam05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDownGradeExam {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();
        SharedData sharedData = new SharedData();

        new Thread(() -> {
            writeLock.lock();
            try {
                System.out.println("쓰기 락 획득: " + Thread.currentThread().getName());
                // 임계영역
                sharedData.setData((int)(Math.random() * 1000));
                System.out.println("데이터 업데이트");

                // 다운 그레이드
                readLock.lock();
                System.out.println("쓰기 락에서 읽기 락으로 전환");

                writeLock.unlock();
                System.out.println("쓰기락 해제");

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readLock.unlock();
            }
        }).start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println("읽기 락 획득 : "+ Thread.currentThread().getName());
                readLock.lock();
                int data = sharedData.getData();
                System.out.println("데이터 읽기("+Thread.currentThread().getName()+") : "+ data);
                try {

                }finally {
                    readLock.unlock();
                    System.out.println("읽기 락 해제 : "+ Thread.currentThread().getName());
                }
            }).start();

        }
    }

    static class SharedData {
        private int data = 0;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
