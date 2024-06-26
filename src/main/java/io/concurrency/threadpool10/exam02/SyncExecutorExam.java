package io.concurrency.threadpool10.exam02;

import java.util.concurrent.Executor;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-06-26
 */
public class SyncExecutorExam {
    public static void main(String[] args) {
        // 작업의 제출과 실행을 분리
        Executor executor = new SyncExecutor();
        executor.execute(() -> {
            System.out.println("동기 작업1 수행!!!");

            System.out.println("동기 작업1 완료!!!");
        });
        executor.execute(() -> {
            System.out.println("동기 작업2 수행!!!");

            System.out.println("동기 작업2 완료!!!");
        });
        executor.execute(() -> {
            System.out.println("동기 작업3 수행!!!");

            System.out.println("동기 작업3 완료!!!");
        });
    }

    static class SyncExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
