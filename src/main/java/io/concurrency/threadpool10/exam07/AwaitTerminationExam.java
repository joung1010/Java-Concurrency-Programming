package io.concurrency.threadpool10.exam07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-09
 */
public class AwaitTerminationExam {
    public static void main(String[] args) throws InterruptedException {
        // 스레드 풀은 내부적으로 스레드 팩토리를 통해서 스레드를 생성
        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);

                return thread;
            }
        });

        executorService.submit(() -> {
            while (true) {
                System.out.println("데몬 스레드 실행 중!!!!");
                Thread.sleep(1000);
            }
        });

        executorService.shutdown();

        executorService.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("메인 스레드 작업 종료");
    }
}
