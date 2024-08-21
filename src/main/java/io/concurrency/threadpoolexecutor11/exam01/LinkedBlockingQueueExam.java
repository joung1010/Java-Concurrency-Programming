package io.concurrency.threadpoolexecutor11.exam01;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <b> LinkedBlockingQueueExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-08-21
 */
public class LinkedBlockingQueueExam {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(5);

        // 생성자
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Produce : " + i);
                    queue.put(i); // 데이터를 큐에 넣기
                    Thread.sleep(1000); // 1초마다 데이터 생성
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        // 소비자
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Integer data = queue.take(); // 데이터를 큐에서 가져옴
                    System.out.println("Consume : " + data);
                    Thread.sleep(100); //소비자는 0.1 초 마다 데이터를 소비
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}
