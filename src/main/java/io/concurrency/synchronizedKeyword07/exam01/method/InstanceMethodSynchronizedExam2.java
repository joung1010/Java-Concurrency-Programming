package io.concurrency.synchronizedKeyword07.exam01.method;

public class InstanceMethodSynchronizedExam2 {
    private int count = 0;

    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " 가 값을 증가 시킵니다. 값: " + count);
    }

    public synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + " 가 값을 감소 시킵니다. 값: " + count);
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        InstanceMethodSynchronizedExam2 count1 = new InstanceMethodSynchronizedExam2();
        InstanceMethodSynchronizedExam2 count2 = new InstanceMethodSynchronizedExam2();
        // 두 객체는 서로 다른 모니터를 가지고 있기때문에 서로 간섭받지 않는다.
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count1.increment();
                count2.decrement();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count1.decrement();
                count2.increment();
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종 값1 : "+ count1.getCount());
        System.out.println("최종 값2 : "+ count2.getCount());
    }
}
