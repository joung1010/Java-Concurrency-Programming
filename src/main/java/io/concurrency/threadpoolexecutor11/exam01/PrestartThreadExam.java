package io.concurrency.threadpoolexecutor11.exam01;

import java.util.concurrent.*;

/**
 * <b> PrestartThreadExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-08-07
 */
public class PrestartThreadExam {
    public static void main(String[] args) {
        // 기본 파라미터 설정
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 0L; // 유지 시간

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        int taskNum = 9;

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        TimeUnit.SECONDS,
                        queue);
        threadPoolExecutor.prestartCoreThread();

        threadPoolExecutor.prestartAllCoreThreads();

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ": 가 잡을 실행하고 있습니다 [" + taskId + "]");
            });
        }

        threadPoolExecutor.shutdown();
    }
}
