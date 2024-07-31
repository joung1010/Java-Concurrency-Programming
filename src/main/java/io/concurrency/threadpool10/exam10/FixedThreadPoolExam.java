package io.concurrency.threadpool10.exam10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b> FixedThreadPoolExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-31
 */
public class FixedThreadPoolExam {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " is working!");
            });
        }


        executorService.shutdown();
    }

}
