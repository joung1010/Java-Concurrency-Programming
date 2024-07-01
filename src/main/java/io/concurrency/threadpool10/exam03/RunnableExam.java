package io.concurrency.threadpool10.exam03;

import java.util.concurrent.*;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-01
 */
public class RunnableExam {
    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Runnable runnableTask = () ->{
            System.out.println("Runnable 작업 수행 중..");
            System.out.println("Runnable 작업 완료");
        };

        executorService.execute(runnableTask);

        executorService.shutdown();
    }
}
