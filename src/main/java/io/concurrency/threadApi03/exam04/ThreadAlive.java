package io.concurrency.threadApi03.exam04;

public class ThreadAlive {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread1 is working..");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread2 is working..");
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        thread1.start();
        thread2.start();

        while (thread1.isAlive() || thread2.isAlive()) {
            System.out.println("Thread1 isAlive : "+ thread1.isAlive() );
            System.out.println("Thread2 isAlive : "+ thread2.isAlive() );
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Thread is Done");
        System.out.println("Thread1 isAlive : "+ thread1.isAlive() ); //false
        System.out.println("Thread2 isAlive : "+ thread2.isAlive() ); //false
    }
}
