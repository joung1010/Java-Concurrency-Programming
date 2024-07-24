package io.concurrency.threadpool10.exam09;

import java.util.concurrent.*;

/**
 * <b> ScheduleCallableExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-24
 */
public class ScheduleRunnableExam {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("작업 한번만 실행 할꺼임! 반환은 아쉽게도 없음");
        };

        scheduledExecutorService.schedule(task, 3, TimeUnit.SECONDS);
       // 비동기
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scheduledExecutorService.shutdown();
    }
}
