package io.concurrency.threadpool10.exam06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-03
 */
public class ExecuteExam {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(() -> {
            System.out.println("비동기 작업");
        });

        // 위와 동일 작업을 수행 하지만 스레드풀을 이용해서 스레드 생성과 작업을 분리한다.
        /*new Thread(() -> {
            System.out.println("비동기 작업");
        }).start();*/

        executorService.shutdown();
    }
}
