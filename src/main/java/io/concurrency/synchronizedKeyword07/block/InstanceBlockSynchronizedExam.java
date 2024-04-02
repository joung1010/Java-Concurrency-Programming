package io.concurrency.synchronizedKeyword07.block;

public class InstanceBlockSynchronizedExam {
    private int instanceCount = 0;

    private Object lockObject = new Object();

    public void instanceIncrementBlockThis() {
        synchronized (this) {
            instanceCount++;
            System.out.println(Thread.currentThread().getName() + " 가 this에 의해 블록 동기화 됨. 값: " + instanceCount);
        }

    }

    public void instanceIncrementBlockLockObject() {
        synchronized (lockObject) {
            instanceCount++;
            System.out.println(Thread.currentThread().getName() + " 가 LockObject에 의해 블록 동기화 됨. 값: " + instanceCount);
        }
    }



    public int getInstanceCount() {
        return instanceCount;
    }


    public static void main(String[] args) throws InterruptedException {
        InstanceBlockSynchronizedExam count1 = new InstanceBlockSynchronizedExam();
        // 카운트 라는 공유 변수를 공유하고 있지만 두 메소드의 모니터는 다르다
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count1.instanceIncrementBlockThis();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count1.instanceIncrementBlockThis();
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count1.instanceIncrementBlockLockObject();
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count1.instanceIncrementBlockLockObject();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();



        System.out.println("최종 값(객체) : " + count1.getInstanceCount());
    }
}
