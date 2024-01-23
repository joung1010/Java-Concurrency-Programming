package io.concurrency.concurrency01.exam01;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConcurrencyEx {
    public static void main(String[] args) {
        int cpuCores = Runtime.getRuntime().availableProcessors() * 2; // 1012
//        int cpuCores = 9; //1009
        // 작업 수가 코어수의 2배일때나 코어 수보다 1개 많은 작업이나 작업시간에는 크게 차이가 나지 않는다.
        // 이는 CPU 코어수 보다 작업수가 많아지게 된다면 병렬성을 가져가긴 하지만 하나의 스레드가 2개의 작업을 번갈아가면서 작업하지 않기 때문 이다.
        // 즉 CPU 코어 수를 초과하는 작업들은 대기 상태가 될 수 있고, 스레드 풀에서 관리하는 작업 큐에 따라 순차적으로 처리됩니다.

        System.out.println("cpuCores = " + cpuCores);

        // CPU 개수를 초과하는 데이터를 생성
        List<Integer> data = new ArrayList<>();
        IntStream.range(0,cpuCores)
                .forEach(data::add);
        System.out.println("data = " + data);

        // CPU 개수를 초과하는 데이터를 병렬처리
        long str = System.currentTimeMillis();
        long sum = data.parallelStream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * i;
                })
                .sum();
        long end = System.currentTimeMillis();

        System.out.println("Processing Time  = " + (end - str));
        System.out.println("Result = " + sum);
    }
}
