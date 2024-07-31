package io.concurrency.threadpool10.exam10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b> SingleThreadPoolExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-31
 */
public class SingleThreadPoolExam {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executorService.execute(() -> {
                System.out.println("Task Id : "+ taskId + "is Working!! : "+ Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
    }
}
