package io.concurrency.async12.exam03;

import java.util.List;
import java.util.concurrent.*;

/**
 * <b> SupplyAsyncExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-10-23
 */
public class SupplyAsyncExam {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        SupplyService service = new SupplyService();

        CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "work async");
            return service.getData();
        });

        List<Integer> result = future.join();
        result.forEach(System.out::println);

        System.out.println("동일 처리 로직을 스레드풀을 이용해서 구현");
        System.out.println("===========================================================================");

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<List<Integer>> future2 = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "work async");
            return service.getData();
        });
        List<Integer> result2 = future2.get();
        result2.forEach(System.out::println);

        System.out.println("main 스레드 종료!");
    }

}


class SupplyService {
    public List<Integer> getData() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return List.of(1, 2, 3, 4);
    }
}
