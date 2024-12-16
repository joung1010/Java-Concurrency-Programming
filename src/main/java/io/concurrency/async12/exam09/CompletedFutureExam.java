package io.concurrency.async12.exam09;

import java.util.concurrent.CompletableFuture;

/**
 * <b> CompletedFuture </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-12-16
 */
public class CompletedFutureExam {
    public static void main(String[] args) {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("HELLO");

        CompletableFuture cf2 = new CompletableFuture<>();
        cf2.complete("WORLD");

        cf.thenAccept(System.out::println);
        String result = cf.join();
        System.out.println("result = " + result);

//        cf1 과 cf2는 동일
    }
}
