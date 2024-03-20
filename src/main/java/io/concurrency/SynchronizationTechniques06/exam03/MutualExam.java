package io.concurrency.SynchronizationTechniques06.exam03;

public class MutualExam {
    private int count = 0;
    public void increase() {
        count++;
        System.out.println(Thread.currentThread().getName() + " : "+ count);
    }
    public static void main(String[] args) {
        MutualExam exam = new MutualExam();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                exam.increase();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                exam.increase();
            }
        });

        thread1.start();
        thread2.start();
    }
}
