package io.concurrency.threadpool10.exam06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-03
 */
public class SubmitRunnableExam {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<?> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("비동기 작업 수행!!!!!!");
            }
        });

        try {
            Object result = future.get();
            System.out.println("result = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        Future<Integer> future2 = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("비동기 작업 수행!!!!!!");
            }
        }, 100);

        try {
            Integer result2 = future2.get();
            System.out.println("result2 = " + result2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }
}
