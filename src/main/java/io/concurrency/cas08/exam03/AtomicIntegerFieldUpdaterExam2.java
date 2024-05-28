package io.concurrency.cas08.exam03;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-05-28
 */
public class AtomicIntegerFieldUpdaterExam2 {

    private static AtomicIntegerFieldUpdater<MyClass> fieldUpdater;

    private static int TREAD_NUM = 3;

    static class MyClass {
        private volatile int count;



        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(MyClass.class, "count");

        Thread[] threads = new Thread[TREAD_NUM];
        for (int i = 0; i < TREAD_NUM; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    int expectedValue;
                    int newValue;
                    do {
                        expectedValue = fieldUpdater.get(myClass);
                        newValue = expectedValue + 1;
                    } while (!fieldUpdater.compareAndSet(myClass, expectedValue, newValue));
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Result: " + fieldUpdater.get(myClass));
        System.out.println("Result: " + myClass.getCount());

    }


}
