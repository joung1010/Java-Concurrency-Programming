package io.concurrency.async12.exam06;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * <b> ThenComposeExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-11-13
 */
public class ThenComposeExam {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ComposeService service = new ComposeService();


        CompletableFuture<Integer> future1 = service.getData(5);

        CompletableFuture<Integer> future2 = future1.thenCompose(service::getData2);

        Integer result = future2.get();
        System.out.println("result = " + result);
    }


    static class ComposeService {

        public CompletableFuture<Integer> getData(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return input * 2;
            });
        }

        public CompletableFuture<Integer> getData2(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return input * 3;
            });
        }

    }
}
