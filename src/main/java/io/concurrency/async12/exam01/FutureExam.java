package io.concurrency.async12.exam01;

import java.util.concurrent.*;

/**
 * <b> FutureExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-09-11
 */
public class FutureExam {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> future1 = executorService.submit(new Service1());
        Future<Integer> future2 = executorService.submit(new Service2(future1));
        Future<Integer> future3 = executorService.submit(new Service3(future2));
        Future<Integer> future4 = executorService.submit(new Service4(future3));

        Integer result = future4.get();
        executorService.shutdown();
        System.out.println("result = " + result);
    }

    private static class Service1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("Service 1 is started.....");
            return 1;
        }
    }

    private static class Service2 implements Callable<Integer> {
        private Future<Integer> future;

        public Service2(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 2 is started.....");
            return future.get() + 1;
        }
    }

    private static class Service3 implements Callable<Integer> {
        private Future<Integer> future;

        public Service3(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 3 is started.....");
            return future.get() + 1;
        }
    }
    private static class Service4 implements Callable<Integer> {
        private Future<Integer> future;

        public Service4(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 4 is started.....");
            return future.get() + 1;
        }
    }
}
