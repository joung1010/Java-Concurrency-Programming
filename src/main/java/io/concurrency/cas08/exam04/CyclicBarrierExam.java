package io.concurrency.cas08.exam04;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-06-10
 */
public class CyclicBarrierExam {
    static int[] parallelSum = new int[2];

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numThreads = 2;

        CyclicBarrier barrier = new CyclicBarrier(numThreads, new BarrierAction(parallelSum));

        for (int i = 0; i < numThreads; i++) {
            new Thread(new Worker(i,numbers,barrier,parallelSum)).start();

        }

    }

    static class Worker implements Runnable {
        private int id;
        private int[] numbers;
        private CyclicBarrier barrier;
        private int[] parallelSum;

        public Worker(int id,
                      int[] numbers,
                      CyclicBarrier barrier,
                      int[] parallelSum) {

            this.id = id;
            this.numbers = numbers;
            this.barrier = barrier;
            this.parallelSum = parallelSum;
        }

        @Override
        public void run() {
            int start = this.id * (numbers.length / 2);
            int end = (this.id + 1) * (numbers.length / 2);

            int sum = IntStream.range(start, end)
                    .map(i -> numbers[i])
                    .sum();
            System.out.println("sum = " + sum);
            parallelSum[id] = sum;

            try {
                barrier.await();
                System.out.println("대기에서 풀려 났음111");
                barrier.await();
                System.out.println("대기에서 풀려 났음222"); // 재사용 가능
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class BarrierAction implements Runnable {
        private int[] sum;

        public BarrierAction(int[] sum) {
            this.sum = sum;
        }

        @Override
        public void run() {
            int finalSum = 0;
            for (int i : sum) {
                finalSum += i;
            }
            System.out.println("최종 합계 : " + finalSum);
        }
    }
}
