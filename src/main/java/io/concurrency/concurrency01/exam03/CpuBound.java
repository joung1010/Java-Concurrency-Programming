package io.concurrency.concurrency01.exam03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CpuBound {
    public static void main(String[] args) {
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threads); // 스레드를 만드는 생성기

        long str = System.currentTimeMillis();
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < threads; i++) {
            Future<?> future = executorService.submit(() -> {

                //CPU 연산 작업
                long res = 0;
                for (long j = 0; j < 1000000000L; j++) {
                    res += j;
                }

                // 잠깐 대기
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("thread:"+ Thread.currentThread().getName() + ", "+ res);

            });
            futures.add(future);
        }

        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("process time : "+ (end - str)+"ms");
        executorService.shutdown();
    }
}
