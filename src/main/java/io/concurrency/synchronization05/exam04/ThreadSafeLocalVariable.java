package io.concurrency.synchronization05.exam04;

public class ThreadSafeLocalVariable {
    public void printNumber(int plus) {
        // 지역 변수, 매개변수로 정의
        int localSum = 0;

        for (int i = 0; i <= 5; i++) {
            localSum += i;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        localSum += plus;
        System.out.println("localSum = " + localSum);
    }

    public static void main(String[] args) {
        ThreadSafeLocalVariable safe = new ThreadSafeLocalVariable();
        Thread thread1 = new Thread(() -> {
            safe.printNumber(50);
        });
        Thread thread2 = new Thread(() -> {
            safe.printNumber(40);
        });

        thread1.start();
        thread2.start();
    }
}
