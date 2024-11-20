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

        CompletableFuture<Void> allOfCf = CompletableFuture.allOf(cf, cf2, cf3);
        System.out.println("메인 스레드 종료"); //allOf만 호출한다고해서 모든작업이 끝날때까지 대기하지 않는다.

    }


    static class ServiceA {

        public CompletableFuture<Integer> getData() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
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
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return 30;
            });
        }


    }
}
