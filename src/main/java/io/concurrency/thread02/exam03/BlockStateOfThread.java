package io.concurrency.thread02.exam03;

public class BlockStateOfThread {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                while (true) {

                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("try to get lock....");
            }
        });

        thread1.start();
//        thread1.start(); // 스레드는 한번 start() 메소드를 호출하면 다시 호출 하면 안된다.
      /*  Exception in thread "main" java.lang.IllegalThreadStateException
        at java.base/java.lang.Thread.start(Thread.java:1525)
        at io.concurrency.thread02.exam03.BlockStateOfThread.main(BlockStateOfThread.java:20)*/

        Thread.sleep(100);
        thread2.start();
        Thread.sleep(100);
        System.out.println("Thread State: " + thread2.getState()); //Thread State: BLOCKED
    }
}
