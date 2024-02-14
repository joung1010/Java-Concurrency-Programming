package io.concurrency.threadUtilization04.exam01;

public class DefaultExceptionHandler {
    public static void main(String[] args) {
        // 전역적으로 스레드 전체에 대한 예외처리
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + "occurred Exception : " + e);
            }
        });

        Thread thread1 = new Thread(() -> {
            throw new RuntimeException("Thread1 Exception occurred!");
        });
        Thread thread2 = new Thread(() -> {
            throw new RuntimeException("Thread2 Exception occurred!");
        });

        thread1.start();
        thread2.start();
    }
}
