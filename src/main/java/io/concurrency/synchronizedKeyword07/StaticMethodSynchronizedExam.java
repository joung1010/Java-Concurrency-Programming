package io.concurrency.synchronizedKeyword07;

public class StaticMethodSynchronizedExam {
    private static int count = 0;

    public static synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " 가 값을 증가 시킵니다. 값: " + count);
    }

    public static synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + " 가 값을 감소 시킵니다. 값: " + count);
    }

    public static int getCount() {
        return count;
    }
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                StaticMethodSynchronizedExam.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                StaticMethodSynchronizedExam.decrement();
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종 값 : "+ StaticMethodSynchronizedExam.getCount());
    }
}
