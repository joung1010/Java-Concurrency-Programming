package io.concurrency.thread02.exam02;

import java.util.Random;

public class MultiThreadApp {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new ThreadStack.MyRunnable(i));
            thread.start();
        }
        System.out.println("main thread end");
        // 멀티 스레드는 생성된 스레드들이 모두 독립적이기 때문에 모든 스레드가 종료되어야 종료된다 결과에서 확인해보면 메인 스레드가 가장먼저 종료된걸 확인할 수 있다.
        // 멀티 스레드는 만약 어떤 이유로 하나의 스레드가 종료되지 않는 다면 어플리케이션은 종료 되지 않는다.
       /* main thread end
        Thread-0: Thread(runnable) Running
        Thread-2: Thread(runnable) Running
        Thread-1: Thread(runnable) Running
        Thread-2 : Thread ID :2: firstMethod Local Id: 244
        Thread-1 : Thread ID :1: firstMethod Local Id: 600
        Thread-0 : Thread ID :0: firstMethod Local Id: 508
*/
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
            System.out.println(Thread.currentThread().getName() + " : Thread ID :" + this.threadId + ": firstMethod Local Id: " + firstMethodId);
        }
    }
}
