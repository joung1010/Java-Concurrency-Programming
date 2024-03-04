package io.concurrency.synchronization05.exam01;

public class SingleThread {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int sum = 0;
        for (int i = 0; i <= 1000; i++) {
            sum += i;
            try {
                Thread.sleep(1);
//                throw new RuntimeException("Error"); //예외 발생시 전체 프로그램이 종료됨( 싱글 스레드의 특징)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total Sum : "+ sum );
        System.out.println("Single Thread process Time : " + (System.currentTimeMillis() - startTime)+ " ms"); // 2초
    }

}
