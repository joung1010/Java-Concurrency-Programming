package io.concurrency.synchronizedKeyword07;

public class ComplexMethodSynchronizedExam {
    private int instanceCount = 0;
    private static int staticCount = 0;

    public synchronized void instanceIncrement() {
        instanceCount++;
        System.out.println(Thread.currentThread().getName() + " 가 값을 증가 시킵니다(객체). 값: " + instanceCount);
    }

    public static synchronized void staticIncrement() {
        staticCount++;
        System.out.println(Thread.currentThread().getName() + " 가 값을 증가 시킵니다(클레스). 값: " + staticCount);
    }

    public synchronized void instanceDecrement() {
        instanceCount--;
        System.out.println(Thread.currentThread().getName() + " 가 값을 감소 시킵니다(객체). 값: " + instanceCount);
    }

    public static synchronized void staticDecrement() {
        staticCount--;
        System.out.println(Thread.currentThread().getName() + " 가 값을 감소 시킵니다(클레스). 값: " + staticCount);
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public static int getStaticCount() {
        return staticCount;
    }
    public static void main(String[] args) throws InterruptedException {
        ComplexMethodSynchronizedExam count1 = new ComplexMethodSynchronizedExam();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count1.instanceIncrement();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count1.instanceIncrement();
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                ComplexMethodSynchronizedExam.staticIncrement();
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                ComplexMethodSynchronizedExam.staticIncrement();
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


        System.out.println("최종 값(객체) : "+ count1.getInstanceCount());
        System.out.println("최종 값(클레스) : "+ ComplexMethodSynchronizedExam.getStaticCount());
    }
}
