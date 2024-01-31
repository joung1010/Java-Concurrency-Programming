package io.concurrency.thread02.exam02;


import java.util.Random;

public class ThreadStack {
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable(i));
            thread.start();
        }
    }
    static class MyRunnable implements Runnable {
        private int threadId;
        public MyRunnable(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": Thread(runnable) Running");
            this.firstMethod(this.threadId);
        }

        private void firstMethod(int threadId) {
            int localThreadId = threadId + new Random().nextInt(900) + 100;
            secondMethod(localThreadId);
        }

        private void secondMethod(int firstMethodId) {
            String objectReference = "Hello " + firstMethodId;
            System.out.println(Thread.currentThread().getName()+ " : Thread ID :" + this.threadId + ": firstMethod Local Id: "+ firstMethodId);
            // this.threadId 는 객체의 멤버로서 Heap 영역에 저장되고 firstMethodId 는 지역변수로써 스택마다 고유하게 가져간다.
            // 이때 지역변수말고 일반 참조 객체도 스택 별로 새롭게 생겨난 객체라면 스레드에 간섭 없이 사용할 수 있다.
        }
    }

}
