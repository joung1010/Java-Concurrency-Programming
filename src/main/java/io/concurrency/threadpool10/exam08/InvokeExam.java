package io.concurrency.threadpool10.exam08;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-09
 */
public class InvokeExam {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = new ArrayList<>();

        tasks.add(() -> {
            TimeUnit.SECONDS.sleep(3);
            return "OK";
        });

        tasks.add(() -> {
            TimeUnit.SECONDS.sleep(2);
            return "MASON";
        });

        tasks.add(() -> {
            TimeUnit.SECONDS.sleep(1);
            throw new RuntimeException("예외 발생!!");
        });

        Instant start = Instant.now();
        List<Future<String>> futures;
        try {
            futures = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Future<String> future : futures) {
            try {
                String result = future.get();
                System.out.println("result = " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                System.out.println("예외 : " + e.getMessage());
            }
        }
        Instant end = Instant.now();

        System.out.println("걸린 시간 : " + Duration.between(start,end).toMillis());

    }
}
