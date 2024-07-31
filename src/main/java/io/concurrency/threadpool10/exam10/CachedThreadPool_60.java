package io.concurrency.threadpool10.exam10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b> CachedThreadPool_60 </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-31
 */
public class CachedThreadPool_60 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " is working!");
            });
        }

        // 60초 동안 아무 작업도 수행 하지 않음
        try {
            Thread.sleep(70000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }
}
