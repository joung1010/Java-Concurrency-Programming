package io.concurrency.threadpool10.exam07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-09
 */
public class ShutdownExam {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ": 작업 중 !!!!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("앗 인터럽트 걸림!!");
                }
                return 30;
            });
 /*           executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ": 작업 중 !!!!");
                // executorService.shutdownNow(); 메서드가 인터럽트를 시도 하지만 인터럽트를 반환하는 코드가 존재 하지 않기때문에 (InterruptedException 발생하는 코드가 없음)
                // 그렇기 때문에 작업을 계속 수행
                return 30;
            });*/
        }

        // 셔다운 요청
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                System.out.println("스레드 풀 작업 강제 종료!!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        if (executorService.isShutdown()) {
            System.out.println("스레드 풀 종료 여부 : " + executorService.isShutdown());
        }

        if (executorService.isTerminated()) {
            System.out.println("스레드 풀 모든 작업 종료 여부 : " + executorService.isTerminated());
        }

        while (!executorService.isTerminated()) {
            System.out.println("스레드 풀 종료 중입니다!!~~~");
        }

        System.out.println("스레드 풀의 모든 작업이 종료되었음!!");
    }
}
