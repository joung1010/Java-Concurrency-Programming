package io.concurrency.synchronizedKeyword07;

public class SynchronizedKeywordExam {

    private int instanceCount = 0;

    private static int staticCount = 0;

    public synchronized  void instanceMethod() {
        instanceCount++;
        System.out.println("instance 메소드 동기화"+ instanceCount);
    }

    public synchronized static void staticMethod() {
        staticCount++;
        System.out.println("static 메소드 동기화"+ staticCount);
    }

    public void instanceBlock() {
        synchronized (this) {
            instanceCount++;
            System.out.println("instance 메소드 동기화"+ instanceCount);
        }
    }

    public static void staticBlock() {
        synchronized (SynchronizedKeywordExam.class) {
            staticCount++;
            System.out.println("static 메소드 동기화"+ staticCount);
        }
    }

    public static void main(String[] args) {
        SynchronizedKeywordExam exam = new SynchronizedKeywordExam();
        new Thread(() -> exam.instanceMethod()).start();
        new Thread(() -> exam.instanceBlock()).start();
        new Thread(() -> SynchronizedKeywordExam.staticMethod()).start();
        new Thread(() -> SynchronizedKeywordExam.staticBlock()).start();
    }
}
