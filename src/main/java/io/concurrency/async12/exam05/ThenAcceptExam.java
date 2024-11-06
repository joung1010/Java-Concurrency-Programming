package io.concurrency.async12.exam05;

import io.concurrency.async12.exam04.ThenApplyExam;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <b> ThenAcceptExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-11-06
 */
public class ThenAcceptExam {
    public static void main(String[] args) {
        MyService myService = new MyService();
        CompletableFuture.supplyAsync(() -> {
                    System.out.println("Thread name:" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return 40;
                }).thenAccept(result -> {
                    System.out.println("Thread name:" + Thread.currentThread().getName());
                    System.out.println("result = " + result);

                    List<Integer> data = myService.getData();
                    data.forEach(System.out::println);
                }).thenAcceptAsync(result -> {
                    System.out.println("Thread name:" + Thread.currentThread().getName());
                    System.out.println("result = " + result);

                    List<Integer> data = myService.getData();
                    data.forEach(System.out::println);
                })
                .join()
        ;

    }

    static class MyService {
        public List<Integer> getData() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return List.of(1, 2, 3, 4);
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
