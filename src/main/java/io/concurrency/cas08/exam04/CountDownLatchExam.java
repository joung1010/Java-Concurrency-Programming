package io.concurrency.cas08.exam04;

import java.util.concurrent.CountDownLatch;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-05-28
 */
public class CountDownLatchExam {
    public static void main(String[] args) throws InterruptedException {
        int numThreads = 5;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch endSignal = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(new Worker(startSignal,endSignal)).start();
        }
        Thread.sleep(3000);
        startSignal.countDown();
        System.out.println("시작 신호!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        endSignal.await();
        System.out.println("모든 스레드 작업 완료");
    }

    static class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal
                , CountDownLatch doneSignal) {

            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();  // 시작 신호를 기다림
                doWork();             // 실제 작업 수행
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }finally {
                doneSignal.countDown(); // 작업 완료 신호
            }
        }

        void doWork() {
            System.out.println(Thread.currentThread().getName() + "가 작업을 수행하고 있음");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "가 작업을 완료함!");
        }
    }
}
