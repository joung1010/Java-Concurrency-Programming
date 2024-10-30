package io.concurrency.async12.exam04;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b> ThenApplyExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-10-30
 */
public class ThenApplyExam {
    public static void main(String[] args) {

        MyService myService = new MyService();
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {// 새로운 CompleteFuture 객체생성1
            System.out.println("Thread(supplyAsync) : " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return 30;
        }).thenApply(result -> {// 새로운 CompleteFuture 객체생성2

            System.out.println("Thread(thenApply) : " + Thread.currentThread().getName());
            Integer data = myService.getData();

            return data + result;
        })/*.thenApplyAsync(result -> {// 새로운 CompleteFuture 객체생성3

            System.out.println("Thread(thenApplyAsync) : " + Thread.currentThread().getName());
            Integer data = myService.getData2();

            return data + result;
        })*/;
        Integer result = future.join();
        System.out.println("result = " + result);
        System.out.println("End Time" + (System.currentTimeMillis() - start));
    }

    static class MyService {
        public Integer getData() {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 50;
        }

        public Integer getData2() {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 60;
        }
    }
}
