package io.concurrency.async12.exam08;

import java.util.concurrent.CompletableFuture;

/**
 * <b> HandleExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-11-27
 */
public class HandleExam {
    public static void main(String[] args) {
        boolean flag1 = false;
        boolean flag2 = false;

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
                if (flag1) {
                    throw new RuntimeException("error1");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        }).handle((r,ex) -> {
            if(ex != null){
                System.out.println(ex.getMessage());

                return -1;
            }
            return r;
        });


        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
                if (flag2) {
                    throw new RuntimeException("error2");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 20;
        }).handle((r,ex) -> {
            if(ex != null){
                System.out.println(ex.getMessage());

                return -1;
            }
            return r;
        });

        CompletableFuture<Integer> cf3 = cf1.thenCombine(cf2, (x, y) ->{
            if(x == -1 || y == -1) {
                return -1;
            }
            return x + y;
        });

        Integer result = cf3.join();
        System.out.println("result = " + result);
    }
}
