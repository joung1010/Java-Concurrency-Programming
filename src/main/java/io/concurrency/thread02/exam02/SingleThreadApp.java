package io.concurrency.thread02.exam02;

import java.util.stream.IntStream;

public class SingleThreadApp {
    public static void main(String[] args) {
/*        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }*/
        int sum = IntStream.range(0, 1000).sum();
        System.out.println("sum = " + sum);
        System.out.println("main thread end");
        // 싱글 스레드는 모든 작업이 종료되어야 스레드가 종료됨
    }
}
