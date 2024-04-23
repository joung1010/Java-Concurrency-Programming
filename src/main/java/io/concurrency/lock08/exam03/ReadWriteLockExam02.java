package io.concurrency.lock08.exam03;

import io.concurrency.SynchronizationTechniques06.exam01.ShareData;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExam02 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ShareData shareData = new ShareData();

        Thread readThread1 = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("Read Thread1 read data! " + shareData.getData());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread readThread2 = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("Read Thread2 read data! " + shareData.getData());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread writeThread = new Thread(() -> {
            readWriteLock.writeLock().lock();
            try {
                System.out.println("write Thread write data! ");
                shareData.setData(40);
                System.out.println("write Thread update data! " + shareData.getData());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        });

        writeThread.start();
        readThread1.start(); // readLock은 동시에 읽기 락을 획득 가능
        readThread2.start();
    }

    static class ShareData {
        private int data = 0;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
