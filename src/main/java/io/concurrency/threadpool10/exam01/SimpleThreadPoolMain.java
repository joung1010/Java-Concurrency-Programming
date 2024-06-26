package io.concurrency.threadpool10.exam01;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-06-26
 */
public class SimpleThreadPoolMain {
    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(3);

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            simpleThreadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName()+" 작업("+taskId+"): 작업 수행중");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+" 작업("+taskId+"): 작업 완료!!");
            });

        }

        Thread.sleep(3000);
        simpleThreadPool.shutDown();
    }
}
