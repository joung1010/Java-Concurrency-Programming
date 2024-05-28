package io.concurrency.cas08.exam03;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * <b>  </b>
 *
 * @author jh.park
 * @version 0.1.0
 * @since 2024-05-28
 */
public class AtomicReferenceUpdaterExam {
    private static AtomicReferenceFieldUpdater<AtomicReferenceUpdaterExam,String> messageUpdater
            = AtomicReferenceFieldUpdater.newUpdater(AtomicReferenceUpdaterExam.class, String.class, "message");

    private volatile String message = "";

    public void doSomething() {
        if (messageUpdater.compareAndSet(this, "", "helloUser")) {
            for (int i = 0; i < 10; i++) {
                System.out.println(messageUpdater.get(this));
            }
            // 초기화
//            messageUpdater.set(this,""); // 모든 스레드가 접근 가능
        } else {
            System.out.println(Thread.currentThread().getName()+ " :현재 스레드는 접근할 수 없습니다.");
        }
    }

    public static void main(String[] args) {
        AtomicReferenceUpdaterExam referenceUpdaterExam = new AtomicReferenceUpdaterExam();
        new Thread(() -> {
            referenceUpdaterExam.doSomething();
        }).start();

        new Thread(() -> {
            referenceUpdaterExam.doSomething();
        }).start();
    }
}
