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
public class InvokeAnyExam {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = new ArrayList<>();

        tasks.add(() -> {
            TimeUnit.SECONDS.sleep(3);
            return "Task 1";
        });

        tasks.add(() -> {
            TimeUnit.SECONDS.sleep(2);
            return "Task 2";
        });

        tasks.add(() -> {
            TimeUnit.SECONDS.sleep(1);
            throw new RuntimeException("예외 발생!!");
        });

        Instant start = Instant.now();
        String result;
        try {
            // 예외는 건너 뛰고 성공한 작업중에 가장 빨리 끝난 값을 return
            result = executorService.invokeAny(tasks);
            System.out.println("result = " + result);
        } catch (InterruptedException  | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        Instant end = Instant.now();

        System.out.println("걸린 시간 : " + Duration.between(start,end).toMillis());
    }
}
