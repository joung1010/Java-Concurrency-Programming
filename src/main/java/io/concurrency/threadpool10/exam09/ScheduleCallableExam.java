package io.concurrency.threadpool10.exam09;

import java.util.concurrent.*;

/**
 * <b> ScheduleCallableExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-24
 */
public class ScheduleCallableExam {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Callable<String> task = () -> {
            return "작업 한번만 실행 할꺼임!";
        };

        ScheduledFuture<String> future = scheduledExecutorService.schedule(task, 3, TimeUnit.SECONDS);
        try {
            String result = future.get();
            System.out.println("result = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }finally {
            scheduledExecutorService.shutdown();
        }
    }
}
