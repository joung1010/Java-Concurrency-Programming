package io.concurrency.threadUtilization04.exam01;

public class ThreadException {
    public static void main(String[] args) {
        try {
            new Thread(() -> {
                throw new RuntimeException("Thread Exception Occurred");
            }).start();
        } catch (Exception e) {
            notify(e);
            // 정상적으로 예외는 발생 하였으나 catch문에서 예외를 받지 못하였음
            // 즉 thread 의 run()에서 발생하는 예외는 그 즉시 소멸해 버린다.
        }
    }

    private static void notify(Exception e) {
        System.out.println("plz call system manager : "+ e);
    }
}
