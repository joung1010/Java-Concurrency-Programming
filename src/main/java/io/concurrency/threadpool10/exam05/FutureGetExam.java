package io.concurrency.threadpool10.exam05;

import java.util.concurrent.*;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-02
 */
public class FutureGetExam {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callable = () -> {
            System.out.println("비동기 작업 시작..");
            Thread.sleep(2000);
            System.out.println("비동기 작업 종료..");

            return 30;
        };

        Future<Integer> future = executorService.submit(callable);

        while (!future.isDone()) {
            System.out.println("작업 완료를 기다리는 중...");
            Thread.sleep(500);
        }
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


    }


}
