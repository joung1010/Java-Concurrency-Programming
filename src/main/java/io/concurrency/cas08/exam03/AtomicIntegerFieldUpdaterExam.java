package io.concurrency.cas08.exam03;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-05-28
 */
public class AtomicIntegerFieldUpdaterExam {
    private static AtomicIntegerFieldUpdater<MyClass> fieldUpdater1;
    private static AtomicReferenceFieldUpdater<MyClass, String> fieldUpdater2;

    public static void main(String[] args) {
        fieldUpdater1 = AtomicIntegerFieldUpdater.newUpdater(MyClass.class, "field1");
        fieldUpdater2 = AtomicReferenceFieldUpdater.newUpdater(MyClass.class, String.class, "field2");

        MyClass myClass = new MyClass();
        fieldUpdater1.addAndGet(myClass, 40);
        fieldUpdater2.compareAndSet(myClass, null, "MyField");

        System.out.println("Update Result: " + myClass.getField1());
        System.out.println("Update Result: " + myClass.getField2());

    }

    static class MyClass {
        private volatile int field1;
        private volatile String field2;

        public int getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }
    }

}

