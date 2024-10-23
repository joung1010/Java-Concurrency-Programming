package io.concurrency.async12.exam03;

import java.util.List;
import java.util.concurrent.*;

/**
 * <b> RunAsyncExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-10-23
 */
public class RunAsyncExam {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        RunSupplyService service = new RunSupplyService();

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "work async");
            List<Integer> data = service.getData();
            data.forEach(System.out::println);
        });

        future.join();
        System.out.println("main 스레드 종료!");
    }
}

class RunSupplyService {
    public List<Integer> getData() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return List.of(1, 2, 3, 4);
    }
}


