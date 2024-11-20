package io.concurrency.async12.exam07;

import java.util.concurrent.CompletableFuture;

/**
 * <b> AnyOfExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-11-20
 */
public class AnyOfExam {
    public static void main(String[] args) {
        ServiceA serviceA = new ServiceA();
        ServiceB serviceB = new ServiceB();
        ServiceC serviceC = new ServiceC();

        CompletableFuture<Integer> cf = serviceA.getData();
        CompletableFuture<Integer> cf2 = serviceB.getData();
        CompletableFuture<Integer> cf3 = serviceC.getData();
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(cf, cf2, cf3);

        long start = System.currentTimeMillis();
        CompletableFuture<Integer> resultCf = anyOf.thenApply(result -> {
            return (int) result + 10;
        });

        Integer result = resultCf.join();
        long end = System.currentTimeMillis();
        System.out.println("최종 소요 시간!" + (end - start));
        System.out.println("result = " + result);

    }

    static class ServiceA {

        public CompletableFuture<Integer> getData() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(5000);
                    System.out.println("비동기 작업 시작1");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return 10;
            });
        }


    }

    static class ServiceB {

        public CompletableFuture<Integer> getData() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println("비동기 작업 시작2");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return 20;
            });
        }


    }

    static class ServiceC {

        public CompletableFuture<Integer> getData() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("비동기 작업 시작3");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return 30;
            });
        }


    }
}
