package io.concurrency.async12.exam06;

import java.util.concurrent.CompletableFuture;

/**
 * <b> ThenCombineExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-11-13
 */
public class ThenCombineExam {

    public static void main(String[] args) {
        CombineService combineService = new CombineService();

        CompletableFuture<Integer> cf1 = combineService.getData(2);
        CompletableFuture<Integer> cf2 = combineService.getData2(3);

        CompletableFuture<Integer> future = cf1.thenCombine(cf2, Integer::sum);
        Integer result = future.join();
        System.out.println("result = " + result);

    }

    static class CombineService {

        public CompletableFuture<Integer> getData(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return input * 2;
            });
        }

        public CompletableFuture<Integer> getData2(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return input * 3;
            });
        }

    }
}
