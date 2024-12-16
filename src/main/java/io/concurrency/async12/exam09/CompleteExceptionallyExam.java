package io.concurrency.async12.exam09;

import java.util.concurrent.CompletableFuture;

/**
 * <b> CompleteExceptionallyExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-12-16
 */
public class CompleteExceptionallyExam {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = new CompletableFuture<>();
        setData(cf1);

        CompletableFuture<String> cf2 = cf1.thenApply(result -> {
            // result 값이 예외이면 해당 작업을 수행하지않고 예외를 핸들링 하는 다음 작업으로 바로 넘어간다.
            System.out.println(result);
            return result.toUpperCase();
        }).handle((r, e) -> {
            if (e != null) {
                System.out.println("Error : " + e.getMessage());
                return "nonname";
            }
            return r;
        });

        String result = cf2.join();
        System.out.println("result = " + result);
    }

    private static void setData(CompletableFuture<String> cf) {
        try {
            System.out.println("비동기 수행중");
            Thread.sleep(5000);
            throw new IllegalArgumentException("error");
        } catch (Exception e) {
            cf.completeExceptionally(e);
        }
    }
}
