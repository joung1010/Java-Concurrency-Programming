package io.concurrency.async12.exam08;

import java.util.concurrent.CompletableFuture;

/**
 * <b> ExceptionallyExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-11-27
 */
public class ExceptionallyExam {
    public static void main(String[] args) {
        boolean flag = false;

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
                if (flag) {
                    throw new RuntimeException("error");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;

        }).thenApply(result -> {
            return result + 10;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return -1;
        });

        Integer result = cf.join();
        System.out.println("result = " + result);
    }
}
