package io.concurrency.threadUtilization04.exam03;

public class UserThread {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("User Thread 1 starts...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("User Thread 1 is done..");
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                System.out.println("User Thread 2 starts...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("User Thread 2 is done..");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All User thread is done. Main Thread is Done");
    }
}
