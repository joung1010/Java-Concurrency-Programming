package io.concurrency.threadUtilization04.exam05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolThreadLocalExam {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2); // 2개의 스레드를 가진 스레드 풀 생성

        // 첫번째 작업: ThreadLocal 값을 설정
        executor.submit(() -> {
            threadLocal.set("Job 1");
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
        });
        //잠시 대기
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 여러 번의 두 번째 작업 : ThreadLocal 값을 설정하지 않고 바로 값을 가져와 출력
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
                // 스레드 풀은 스레드를 새롭게 생성하는게 아니기때문에 초기값이 아닌 이전 스레드가 설정한 값을 가져올 수도 있다.
                /*
                *   pool-1-thread-1 : Job 1
                *
                    pool-1-thread-1 : Job 1 이전 스레드에서 설정한 값
                    pool-1-thread-2 : null
                    pool-1-thread-1 : Job 1 이전 스레드에서 설정한 값
                    pool-1-thread-1 : Job 1 이전 스레드에서 설정한 값
                    pool-1-thread-2 : null
                * */
            });
        }
        executor.shutdown();

    }
}
