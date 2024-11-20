package io.concurrency.async12.exam07;

import java.util.concurrent.CompletableFuture;

/**
 * <b> AllOfExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-11-20
 */
public class AllOfExam {
    public static void main(String[] args) {

        ServiceA serviceA = new ServiceA();
        ServiceB serviceB = new ServiceB();
        ServiceC serviceC = new ServiceC();

        CompletableFuture<Integer> cf = serviceA.getData();
        CompletableFuture<Integer> cf2 = serviceB.getData();
        CompletableFuture<Integer> cf3 = serviceC.getData();

        long start = System.currentTimeMillis();
        CompletableFuture<Void> allOfCf = CompletableFuture.allOf(cf, cf2, cf3);
//        allOfCf.join(); // 종료까지 대기
        CompletableFuture<Integer> finalCf = allOfCf.thenApply(v -> {
            Integer result1 = cf.join();
            Integer result2 = cf2.join();
            Integer result3 = cf3.join();

            System.out.println("result1 = " + result1);
            System.out.println("result2 = " + result2);
            System.out.println("result3 = " + result3);
            return result1 + result2 + result3;
        });

        Integer result = finalCf.join();
        long end = System.currentTimeMillis();
        System.out.println("최종 소요 시간!" + (end - start));
        System.out.println("result = " + result);

        System.out.println("메인 스레드 종료");

    }


    static class ServiceA {

        public CompletableFuture<Integer> getData() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
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
