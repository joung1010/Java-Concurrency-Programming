package io.concurrency.synchronization05.exam01;

public class MultiThread {
    private static int sum = 0;

    private final static Object lock = new Object();
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 500; i++) {
                synchronized (lock) { // 해당 키워드 synchronized를 주석하게 되면 값이 차이가 발생하는데 이는 공유 자원에 대한 접근시 시점차이 때문에 발생한다.
                                        // 그렇기 때문에 synchronized 키워드를 통해서 동시성 문제를 해결해 줘야 한다.
                    sum += i;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread thread2 = new Thread(() -> {
            for (int i = 501; i <= 1000; i++) {
                synchronized (lock) {
                    sum += i;
                }
                try {
                    Thread.sleep(1);
//                    throw new RuntimeException("Error");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total Sum : "+ sum );
        System.out.println("Multi Thread process Time : " + (System.currentTimeMillis() - startTime)+ " ms"); // 1초
    }
}
