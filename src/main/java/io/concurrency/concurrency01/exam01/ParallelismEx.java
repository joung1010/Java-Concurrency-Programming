package io.concurrency.concurrency01.exam01;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParallelismEx {
    public static void main(String[] args) {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        System.out.println("cpuCores = " + cpuCores);

        // CPU 개수만큼 데이터를 병렬 로처리
        List<Integer> data = new ArrayList<>();
        IntStream.range(0,cpuCores)
                .forEach(data::add);
        System.out.println("data = " + data);

        // CPU 개수 만큼 데이터를 병렬처리
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
/*        long sum = data.stream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * i;
                })
                .sum();*/
        long end = System.currentTimeMillis();
        // 병렬 스트림 처리 결과 : 523 밀리 세컨드
        // 스트림 처리 결과 : 4082 밀리 세컨드
        System.out.println("Processing Time = " + (end - str));
        System.out.println("Result = " + sum);
    }
}
