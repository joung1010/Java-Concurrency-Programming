package io.concurrency.threadpool10.exam03;

import java.util.concurrent.*;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-01
 */
public class CallableExam {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callable = () -> {
            System.out.println("Callable 작업 중....");
            System.out.println("Callable 작업 완료....");

            return 10;
        };

        Future<Integer> future = executorService.submit(callable);
        try {
            Integer result = future.get();
            System.out.println("callable result = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }finally {
            executorService.shutdown();
        }

    }
}
