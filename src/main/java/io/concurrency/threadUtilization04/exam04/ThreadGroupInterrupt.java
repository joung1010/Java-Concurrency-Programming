package io.concurrency.threadUtilization04.exam04;

public class ThreadGroupInterrupt {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup topThreadGroup = new ThreadGroup("Top Thread Group");
        ThreadGroup subThreadGroup = new ThreadGroup(topThreadGroup,"Sub Thread Group");

        Thread thread1 = new Thread(topThreadGroup,() -> {
            System.out.println("Top Thread Group Thread starts...");
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Top Group Thread interrupted");
                    break;
                }
            }
        },"TopGroupThread");

        Thread thread2 = new Thread(subThreadGroup,() -> {
            System.out.println("Sub Thread Group Thread starts...");
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Sub Group Thread interrupted");
                    break;
                }
            }
        },"SubGroupThread");

        thread1.start();
        thread2.start();

        Thread.sleep(3000);
        System.out.println("Try to interrupt Group Thread...");

        subThreadGroup.interrupt();
        topThreadGroup.interrupt();

    }
}
