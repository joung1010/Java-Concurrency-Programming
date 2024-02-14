package io.concurrency.threadUtilization04.exam01;

public class UncaughtExceptionHandler {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("thread starts job!~~~~");

            throw new RuntimeException("Thread Exception occurred!");
        });
        // 스레드별 예외처리
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " occurred Exceptions : "+ e);
        });

        thread.start();
    }
}
