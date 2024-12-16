package io.concurrency.async12.exam09;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b> CompleteExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-12-16
 */
public class CompleteExam {
    public static void main(String[] args) {
        CustomService service = new CustomService();
        CompletableFuture<Integer> cf = service.performTask();
        Integer result = cf.thenApply(r -> r + 20)
                .join();

        System.out.println("result = " + result);
        System.out.println("메인 스레드 종료!!");
    }

    static class CustomService {

        public CompletableFuture<Integer> performTask() {
            CompletableFuture<Integer> future = new CompletableFuture<>();

            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                future.complete(40);
//                future.complete(60); 이미 값을 완료처리 후에는 다른 값으로 변경 불가능
            });

            return future;
        }
    }
}
