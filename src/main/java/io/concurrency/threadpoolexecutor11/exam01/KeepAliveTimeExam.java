package io.concurrency.threadpoolexecutor11.exam01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <b> KeepAliveTimeExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-08-07
 */
public class KeepAliveTimeExam {
    public static void main(String[] args) throws InterruptedException {
        // 기본 파라미터 설정
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 1L; // 유지 시간
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
        int taskNum = 6;

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        TimeUnit.SECONDS,
                        queue);

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + ": 가 잡을 실행하고 있습니다 [" + taskId + "]");
            });
        }
        threadPoolExecutor.allowCoreThreadTimeOut(true); // coreSize 까지 종료하는 옵션

        Thread.sleep(4000);
        threadPoolExecutor.shutdown();
    }
}
