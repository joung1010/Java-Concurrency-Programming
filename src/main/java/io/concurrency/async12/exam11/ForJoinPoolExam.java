package io.concurrency.async12.exam11;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * <b> ForJoinPoolExam </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2025-01-13
 */
public class ForJoinPoolExam {
    public static void main(String[] args) {
        // 초기 데이터
        int [] array = new int[10];
        for (int i = 0; i < args.length; i++) {
            array[i] = i;
            //[0,1,2,3,4,5,6,7,8,9,10]
            // 2개씩 분할
            //[0,1]
            //[1,2]
            //[2,3]
            //[3,4]
            //[4,5]
            //[5,6]
            //[6,7]
            //[7,8]
            //[8,9]
            //[9,10]

            // 더 이상 분할할 수 없을때 까지 분할한다음에 2개씩 합친다.
            //[0,1] = 0
            //[1,2] = 1 = 1
            //[2,3] = 2
            //[3,4] = 3 = 5
            //[4,5] = 4
            //[5,6] = 5 = 9
            //[6,7] = 6
            //[7,8] = 7 = 13
            //[8,9] = 8
            //[9,10] = 9 = 17

            // 이를 반복한다.
            //[1,2] = 1 = 1
            //[3,4] = 3 = 5 = 6
            //[5,6] = 5 = 9
            //[7,8] = 7 = 13 = 22
            //[9,10] = 9 = 17 = 17

            //[3,4] = 3 = 5 = 6
            //[7,8] = 7 = 13 = 22 = 28
            //[9,10] = 9 = 17 = 17

            //[7,8] = 7 = 13 = 22 = 28
            //[9,10] = 9 = 17 = 17

            // 28 + 17 = 45
            // 위 처럼 특정 배열을 분할하고 그결과를 다시 합치는 것을 정복이라고 한다. 이러한 방식을 ForkJoinPool이 동작한다.
        }
        // CPU 만큼 Pool 생성
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        RecursiveTask<Integer> task = new CustomRecursiveTask(array,0,array.length);
        Integer result = pool.invoke(task);

        System.out.println("result = " + result);

    }
}
