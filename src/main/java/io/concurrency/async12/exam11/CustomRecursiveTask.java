package io.concurrency.async12.exam11;

import java.util.concurrent.RecursiveTask;

/**
 * <b> CustomRecursiveTask </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2025-01-13
 */
public class CustomRecursiveTask extends RecursiveTask<Integer> {

    private final int[] array;
    private final int start;
    private final int end;

    private static final int THRESHOLD = 2;

    public CustomRecursiveTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {//분할할 작업을 정의

        if (end - start <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i <end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = start + (end - start) / 2;
            CustomRecursiveTask left = new CustomRecursiveTask(array, start, mid);
            CustomRecursiveTask right = new CustomRecursiveTask(array, mid, end);

            left.fork();
            Integer rightResult = right.compute();
            Integer leftResult = left.join();

            return leftResult + rightResult;
        }

    }
}
