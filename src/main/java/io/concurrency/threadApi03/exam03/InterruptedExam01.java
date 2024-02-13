package io.concurrency.threadApi03.exam03;

public class InterruptedExam01 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) { // Thread.interrupted() : 상태값을 반환한 다음 초기화 진행
                System.out.println("Thread is Working!");
            }
            System.out.println("Thread is interrupted");
            System.out.println("Thread isInterrupted : " + Thread.currentThread().isInterrupted()); //Thread isInterrupted : false
        });
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt(); // 인터럽트를 발생시켜 상태값을 false에서 true로 변경

    }
}
