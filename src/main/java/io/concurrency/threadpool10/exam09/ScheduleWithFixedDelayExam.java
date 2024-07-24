package io.concurrency.threadpool10.exam09;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * <b> ScheduleWithFixedDelayExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-24
 */
public class ScheduleWithFixedDelayExam {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        Runnable task = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Thread Name : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        // 작업이 끝난후 대기했다가 다시 실행
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scheduledFuture.cancel(true);

        scheduledExecutorService.shutdown();

    }
}
