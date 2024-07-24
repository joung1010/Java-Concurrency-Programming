package io.concurrency.threadpool10.exam09;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * <b> ScheduleAtFixedRateExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-24
 */
public class ScheduleAtFixedRateExam {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        Runnable task = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("Thread Name : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        // 작업보다 주기가 잛으면 일단 작업이 끝날때 까지 대기한 후에 실행한다.
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture2 = scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture3 = scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture4 = scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        // 작업이 여러번 제출되면 스레드 풀에 있는 스레드 개수에 맞게 제출된 작업을 가져간다.


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scheduledFuture.cancel(true);

        scheduledExecutorService.shutdown();

    }
}
