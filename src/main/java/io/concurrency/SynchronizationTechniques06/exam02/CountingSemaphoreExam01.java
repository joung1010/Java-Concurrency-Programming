package io.concurrency.SynchronizationTechniques06.exam02;

import java.util.stream.IntStream;

public class CountingSemaphoreExam01 {
    public static void main(String[] args) {
        int permits = 10;
        CommonSemaphore semaphore = new CountingSemaphore(permits);
        SharedResource resource = new SharedResource(semaphore);

        int threadCount = 30;
        Thread[] threads = new Thread[threadCount];
        IntStream.range(0,threadCount)
                .forEach(i -> {
                    threads[i] =  new Thread(() -> {
                        synchronized (CountingSemaphoreExam01.class) {
                            resource.sum();
                        }
                    });
                    threads[i].start();
                });
        IntStream.range(0,threadCount)
                .forEach(i -> {
                    try {
                        threads[i].join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        System.out.println("total Sum = " + resource.getSum());
    }
}
