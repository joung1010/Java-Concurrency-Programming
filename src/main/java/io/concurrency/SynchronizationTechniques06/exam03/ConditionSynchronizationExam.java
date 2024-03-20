package io.concurrency.SynchronizationTechniques06.exam03;

public class ConditionSynchronizationExam {

    private boolean isAvailable = false;

    public synchronized void produce() {
        while (isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("생산!!!!!!!");
        isAvailable = true;
        notify();
    }

    public synchronized void consume() {
        while (!isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("소비함!!!!!!!");
        isAvailable = false;
        notify();
    }
    public static void main(String[] args) {
        ConditionSynchronizationExam exam = new ConditionSynchronizationExam();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                exam.produce();
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                exam.consume();
            }
        });
        thread2.start();
    }
}
