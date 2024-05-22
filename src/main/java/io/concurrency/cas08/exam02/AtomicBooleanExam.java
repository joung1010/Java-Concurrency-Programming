package io.concurrency.cas08.exam02;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExam {

    private static AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    System.out.println("스레드 1 이 대기중.. :" + flag.get());
                }
                System.out.println("스레드 1 이 임계영역 수행");
                flag.set(false);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    System.out.println("스레드 2 이 대기중.. :" + flag.get());
                }
                System.out.println("스레드 2 이 임계영역 수행");
                flag.set(false);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
