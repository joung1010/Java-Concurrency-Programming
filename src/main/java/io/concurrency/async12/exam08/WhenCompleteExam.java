package io.concurrency.async12.exam08;

import java.util.concurrent.CompletableFuture;

/**
 * <b> WhenCompleteExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-11-27
 */
public class WhenCompleteExam {
    public static void main(String[] args) {

        boolean flag = true;

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(500);
                        if (flag) {
                            throw new RuntimeException("Error");
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return 10;
                })
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.out.println(ex.getMessage());
                    } else {
                        System.out.println("result = " + result);
                    }

                });
        // 예외가 발생하면 대체값을 설정할 수 없다.
        // 따라서 whenComplete 를 이용해서 예외를 처리했더라도 join() 에서 대체값을 설정하지 않았기때문에 예외가 그대로 발생한다.
        // 그래서 별도의 예외처리를 해줘야 한다.
        try {
            Integer result = cf.join();
            System.out.println("result = " + result);
        } catch (Exception e) {
            System.out.println("join() 호출 예외 처리");
        }
        
    }
}
