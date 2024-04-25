package io.concurrency.lock08.exam06;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducerExam {
    private static final int CAPACITY = 5;
    private final Queue<Integer> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            lock.lock();
            try {
                while (queue.size() == CAPACITY) {
                    System.out.println("큐가 가득 차서 대기함");
                    notFull.await();
                }
                queue.offer(value);
                System.out.println("생산: " + value + ", 큐 크기: " + queue.size());
                value++;

                notEmpty.signal();
            } finally {
                lock.unlock();
            }

            Thread.sleep(500);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    System.out.println("큐가 비어 있어 대기함");
                    notEmpty.await();
                }
                int value = queue.poll();
                System.out.println("소비: " + value + ", 큐 크기: " + queue.size());

                notFull.signal();
            } finally {
                lock.unlock();
            }

            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        ConsumerProducerExam example = new ConsumerProducerExam();

        Thread producerThread = new Thread(() -> {
            try {
                example.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 소비자 스레드
        Thread consumerThread = new Thread(() -> {
            try {
                example.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
