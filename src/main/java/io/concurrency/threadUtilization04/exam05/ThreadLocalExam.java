package io.concurrency.threadUtilization04.exam05;

public class ThreadLocalExam {
    //    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Hello word!!");
    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : "+ threadLocal.get());
            threadLocal.set("Thread 1 Value");
            System.out.println(Thread.currentThread().getName() + " set ThreadLocal : "+ threadLocal.get());
            threadLocal.remove();
            System.out.println(Thread.currentThread().getName() + " remove ThreadLocal : "+ threadLocal.get());
        },"Thread 1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : "+ threadLocal.get());
            threadLocal.set("Thread 2 Value");
            System.out.println(Thread.currentThread().getName() + " set ThreadLocal : "+ threadLocal.get());
            threadLocal.remove();
            System.out.println(Thread.currentThread().getName() + " remove ThreadLocal : "+ threadLocal.get());
        },"Thread 2").start();
    }
}
