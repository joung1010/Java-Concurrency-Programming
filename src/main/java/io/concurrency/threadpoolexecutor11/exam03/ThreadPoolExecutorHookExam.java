package io.concurrency.threadpoolexecutor11.exam03;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <b> ThreadPoolExecutorHookExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-08-28
 */
public class ThreadPoolExecutorHookExam {

    static class CustomThreadPoolExecutor extends ThreadPoolExecutor {

        public CustomThreadPoolExecutor(int corePoolSize
                , int maximumPoolSize
                , long keepAliveTime
                , TimeUnit timeUnit
                , LinkedBlockingQueue<Runnable> runnables) {

            super(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, runnables);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println(Thread.currentThread().getName() + ":가 적업을 실행하려고 함");
            super.beforeExecute(t, r);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (!Objects.isNull(t)) {
                System.out.println(t.getMessage() +", 이유로 작업을 수행하지 못했습니다.");
            } else {
                System.out.println("작업이 완료!!");
            }
            super.afterExecute(r, t);
        }

        @Override
        protected void terminated() {
            System.out.println("스레드 풀 종료!!!!");
            super.terminated();
        }
    }

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 2;
        long keepAliveTime = 0L;
        int workQueueSize = 2;

        ThreadPoolExecutor threadPoolExecutor = new CustomThreadPoolExecutor(
                corePoolSize
                , maximumPoolSize
                , keepAliveTime
                , TimeUnit.SECONDS
                , new LinkedBlockingQueue<Runnable>(workQueueSize)
        );
        for (int i = 0; i < 4; i++) {
            final int taskId = i;
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ": is working " + taskId);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        threadPoolExecutor.shutdown();

    }
}
