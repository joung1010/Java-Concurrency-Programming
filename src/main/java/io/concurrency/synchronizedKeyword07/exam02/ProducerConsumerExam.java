package io.concurrency.synchronizedKeyword07.exam02;

import java.util.LinkedList;
import java.util.Queue;

class SharedQueue {

    private Queue<Integer> queue = new LinkedList<>();

    private final int CPACITY = 5;
    private final Object lock = new Object();

    public void produce(int item) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() >= CPACITY) {
                System.out.println("아이템이 가득 찼다!!! 생산 중지!!");
                lock.wait();
            }
            queue.add(item);
            System.out.println("생산: "+ item);
            lock.notifyAll();
        }
    }
    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                System.out.println("아이템이 없음!!! 소비 중지!!");
                lock.wait();
            }
            Integer item = queue.poll();
            System.out.println("소비한 아이템: "+ item);
            lock.notifyAll();
        }
    }
}


public class ProducerConsumerExam {
    public static void main(String[] args) {
        SharedQueue share = new SharedQueue();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    share.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"생산자").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    share.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"소비자").start();
    }
}
