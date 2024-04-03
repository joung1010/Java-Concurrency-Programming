package io.concurrency.synchronizedKeyword07.exam01.block;


class MethodblockClass {

}
public class StaticBlockSynchronizedExam {

    private static int staticCount = 0;

    private static Object lockObject = new Object();

    public static void instanceIncrementBlockClass() {
        synchronized (StaticBlockSynchronizedExam.class) {
            staticCount++;
            System.out.println(Thread.currentThread().getName() + " 가 Class에 의해 블록 동기화 됨. 값: " + staticCount);
        }

    }

    public static void instanceIncrementBlockOtherClass() {
        synchronized (MethodblockClass.class) {
            staticCount++;
            System.out.println(Thread.currentThread().getName() + " 가 OtherClass에 의해 블록 동기화 됨. 값: " + staticCount);
        }
    }



    public static int getStaticCount() {
        return staticCount;
    }
    public static void main(String[] args) throws InterruptedException {


        // 서로다른 모니터 4개
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                StaticBlockSynchronizedExam.instanceIncrementBlockClass();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                StaticBlockSynchronizedExam.instanceIncrementBlockOtherClass();
            }
        });


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종 값1 : " + StaticBlockSynchronizedExam.getStaticCount());
    }
}
