package io.concurrency.threadApi03.exam04;

public class CurrentThread {
    public static void main(String[] args) {
        System.out.println("Current Thread (main)" + Thread.currentThread());
        System.out.println("Current Thread (main) Name" + Thread.currentThread().getName());

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("Current Thread(anonymous) : " + Thread.currentThread());
                System.out.println("Current Thread(anonymous) name: " + getName());
            }
        };
        thread.start();

        Thread thread2 = new Thread(new ThreadName());
        thread2.start();
        
    }

    static class ThreadName implements Runnable {
        @Override
        public void run() {
            System.out.println("Current Thread (Runnable)" + Thread.currentThread());
            System.out.println("Current Thread (Runnable) Name" + Thread.currentThread().getName());
        }
    }
}
