package io.concurrency.async12.exam01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * <b> CompletableFutureExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-09-11
 */
public class CompletableFutureExam {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Integer result = CompletableFuture.supplyAsync(() -> {
            System.out.println("Service 1 is started.....");

            return 1;
        }).thenApplyAsync(res2 -> {
            System.out.println("Service 2 is started.....");

            return res2 + 1;
        }).thenApplyAsync(res3 -> {
            System.out.println("Service 3 is started.....");

            return res3 + 1;
        }).thenApplyAsync(res4 -> {
            System.out.println("Service 4 is started.....");

            return res4 + 1;
        }).get();

        System.out.println("result = " + result);
    }


}
