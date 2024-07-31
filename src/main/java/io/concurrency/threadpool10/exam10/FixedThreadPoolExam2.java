package io.concurrency.threadpool10.exam10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <b> FixedThreadPoolExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-07-31
 */
public class FixedThreadPoolExam2 {
    public static void main(String[] args) {

        ThreadFactory customThreadFactory = new MyThreadPoolFactory("customThreadFactory");
        ExecutorService executorService = Executors.newFixedThreadPool(5,customThreadFactory);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final int taskNum = i;
            Callable<Integer> task = () -> {
                System.out.println(Thread.currentThread().getName() + ": " + taskNum + 1);
                return taskNum + 1;
            };
            Future<Integer> future = executorService.submit(task);
            futures.add(future);
        }

        for (Future<Integer> future : futures) {
            Integer result;
            try {
                result = future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }


        executorService.shutdown();
    }

    static class MyThreadPoolFactory implements ThreadFactory {
        private final String name;
        private int ThreadCounter = 0;

        public MyThreadPoolFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            ThreadCounter++;
            String threadName = name + "-" + ThreadCounter;
            Thread thread = new Thread(r, threadName);
            System.out.println("스레드 생성!!!");
            return thread;
        }
    }
}
