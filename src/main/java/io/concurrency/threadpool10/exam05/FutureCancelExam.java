package io.concurrency.threadpool10.exam05;

import java.util.concurrent.*;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-02
 */
public class FutureCancelExam {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callable = () -> {
            System.out.println("비동기 작업 시작..");
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                throw new RuntimeException("인터럽트 걸림");
            }

            System.out.println("비동기 작업 종료..");

            return 30;
        };

        Future<Integer> future = executorService.submit(callable);
        Thread.sleep(1000);

        future.cancel(true); // 비동기 작업 중간에 예외발생
//        future.cancel(false); // 비동기 작업은 완료하고 결과값을 받는 부분에서 예외 발생

        if (!future.isCancelled()) {
            try {
                Integer result = future.get();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (CancellationException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("작업이 취소됨!");
        }

/*
        try {
            Integer result = future.get();
            System.out.println("result = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }finally {
            executorService.shutdown();
        }
*/


    }
}
