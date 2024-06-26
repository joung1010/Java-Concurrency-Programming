package io.concurrency.threadpool10.exam02;

import java.util.concurrent.Executor;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-06-26
 */
public class AsyncExecutorExam {
    public static void main(String[] args) {
        // 작업의 제출과 실행을 분리
        Executor executor = new AsyncExecutor();
        executor.execute(() -> {
            System.out.println("비동기 작업1 수행!!!");

            System.out.println("비동기 작업1 완료!!!");
        });
        executor.execute(() -> {
            System.out.println("비동기 작업2 수행!!!");

            System.out.println("비동기 작업2 완료!!!");
        });
        executor.execute(() -> {
            System.out.println("비동기 작업3 수행!!!");

            System.out.println("비동기 작업3 완료!!!");
        });
    }

    static class AsyncExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            // 현재 진행중인 스레드가 수행하는게 아니라 작업을 다른 스레드에게 위임한다.
            Thread thread = new Thread(command);
            thread.start();
        }
    }
}
