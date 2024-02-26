package io.concurrency.threadUtilization04.exam05;

public class InheritableThreadLocalExam {
    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    public static void main(String[] args) {
        inheritableThreadLocal.set("parent Thread value");

        Thread childThread = new Thread(() -> {
            System.out.println("Inherited value from the parent thread : " + inheritableThreadLocal.get());
            inheritableThreadLocal.set("Child Thread new Value");
            System.out.println("change parent Inherited value : " + inheritableThreadLocal.get());
        });
        childThread.start();

        try {
            childThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Parent Thread Value : "+ inheritableThreadLocal.get());// 상속받은 스레드 값을 자식스레드가 그 값을 변경해도 부모 스레드의 값에는 영향이 없다.
    }
}
