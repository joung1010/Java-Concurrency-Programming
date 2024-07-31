package io.concurrency.threadpool10.exam10;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * <b> ScheduledThreadPoolExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-31
 */
public class ScheduledThreadPoolExam {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Task is Running!!!!!");
        };

        int intialDelay = 0;
        int intialTimePeriod = 1;
        int updateTimePeriod = 3;


        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(task, intialDelay, intialTimePeriod, TimeUnit.SECONDS); // 초기딜레이 0초 1초주기로 반복


        try {
            Thread.sleep(5000); // 5초동안 실행
            scheduledFuture.cancel(true); // 스케줄링 취소

            scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(task, intialDelay,updateTimePeriod, TimeUnit.SECONDS); // 3초 주기로 반복
            Thread.sleep(10000); // 10초동안 실행 (변경된 주기로)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scheduledFuture.cancel(false);
        scheduledExecutorService.shutdown();

    }
}
