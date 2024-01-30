package io.concurrency.thread02.exam02;

public class ThreadStartExam {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "Thread Running~");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "Thread Running~");
        });
        Thread thread3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "Thread Running~");
        });
        thread.start(); // 새로운 thread를 생성해서 run()을 호출 Thread-0Thread Running~
        thread2.start();
        thread3.start();
//        thread.run(); // main 스레드가 해당 run() 메소드를 호출 mainThread Running~
        System.out.println(Thread.currentThread().getName() + ": main Thread End");
        // 스레드들은 비동기적으로 실행한다. 즉 각 스레드들은 독립적으로 실행해서 보인의 스택에 있는 작업을 실행하고 종료한다.
    }
}
