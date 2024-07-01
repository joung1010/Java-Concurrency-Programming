package io.concurrency.threadpool10.exam04;

import java.util.concurrent.*;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-01
 */
public class FutureCallbackExam {

    static interface Callback {
        void onCompleted(int result);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callable = () -> {
          Thread.sleep(1000);
            return 10;
        };

        Future<Integer> future = executorService.submit(callable);
        System.out.println(Thread.currentThread().getName()+" 비동기 작업 시작!!!");

        register(future, result -> {
            System.out.println(Thread.currentThread().getName()+" 비동기 작업 result = " + result);
        });

        executorService.shutdown();
    }

    private static void register(Future<Integer> future, Callback callback) {
        new Thread(() -> {
            Integer result;
            try {
                result = future.get();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            callback.onCompleted(result);
        }).start();
    }
}
