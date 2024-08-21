package io.concurrency.threadpoolexecutor11.exam02;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <b> DiscardOldestPolicyExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-08-21
 */
public class DiscardOldestPolicyExam {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 2;
        long keepAliveTime = 0L;
        int workQueueCapacity = 2;

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize
                , maximumPoolSize
                , keepAliveTime
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<Runnable>(workQueueCapacity)
                , new ThreadPoolExecutor.DiscardOldestPolicy());


        submitTask(threadPoolExecutor);
    }

    private static void submitTask(ThreadPoolExecutor executor) {
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println("taskId: " + taskId + "is Running on thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executor.shutdown();
    }
}
