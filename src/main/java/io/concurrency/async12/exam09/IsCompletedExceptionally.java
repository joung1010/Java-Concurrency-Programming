package io.concurrency.async12.exam09;

import java.util.concurrent.CompletableFuture;

/**
 * <b> IsCompletedExceptionally </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-12-16
 */
public class IsCompletedExceptionally {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
//            throw new RuntimeException("error");
            return 20;
        });

//        cf2.cancel(true);

        CompletableFuture<Integer> combine = cf1.thenCombine(cf2.exceptionally(e -> 15), (r1, r2) -> {

            if (cf2.isCancelled()) {
                return 0;
            } else if (cf2.isCompletedExceptionally()) {
                return r2;
            } else if (cf2.isDone()) {
                return r1 + r2;
            } else {
                return -1;
            }

        });

        Integer result = combine.join();
        System.out.println("result = " + result);
    }
}
