package io.concurrency.synchronization05.exam04;

public class ThreadSafeLocalReference {
    static class LocalObj {
        private int value;

        public void increment(){value++; }

        @Override
        public String toString() {
            return "LocalObj{ value = " + value + " }";
        }
    }

    public void userLocalObject() {
        // 지역 객체 참조, 각 스레드는 독립적인 객체를 가짐
        LocalObj obj = new LocalObj();

        for (int i = 1; i < 5; i++) {
            obj.increment();
            System.out.println(Thread.currentThread().getName()+ " - " + obj);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadSafeLocalReference localObject = new ThreadSafeLocalReference();

        Thread thread1 = new Thread(() -> {
            localObject.userLocalObject();
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            localObject.userLocalObject();
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
