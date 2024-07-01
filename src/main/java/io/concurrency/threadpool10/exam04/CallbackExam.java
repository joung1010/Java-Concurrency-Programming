package io.concurrency.threadpool10.exam04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-01
 */
public class CallbackExam {

    static interface Callback {
        void onCompleted(int result);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int result = 40;

            Callback callback = new MyCallback();
            callback.onCompleted(result);
        });
        System.out.println("비동기 작업 시작!");
    }

    static class MyCallback implements Callback {
        @Override
        public void onCompleted(int result) {
            System.out.println("비동기 작업 result = "+ result);
        }
    }
}
