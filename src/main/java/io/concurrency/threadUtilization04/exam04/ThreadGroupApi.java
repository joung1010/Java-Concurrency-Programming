package io.concurrency.threadUtilization04.exam04;

public class ThreadGroupApi {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup topGroup = new ThreadGroup("Top Group Thread");

        // 상위 스레드 그룹에 속하는 스레드들 생성
        Thread[] topThreads = new Thread[5];
        for (int i = 1; i <= 5; i++) {
            topThreads[i-1]  = new Thread(topGroup, () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread" + i);
            topThreads[i-1].start();
        }

        ThreadGroup subGroup = new ThreadGroup(topGroup, "SubGroupThread");

        // 하위 스레드 그룹에 속하는 스레드들 생성
        Thread[] subThreads = new Thread[5];
        for (int i = 6; i <= 10; i++) {
            subThreads[i-6] = new Thread(subGroup, () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread" + i);
            subThreads[i-6].start();
        }

        // 모든 스레드가 종료될 때까지 기다림
       /* for (Thread thread : topThreads) {
            thread.join();
        }
        for (Thread thread : subThreads) {
            thread.join();
        }*/

        System.out.println("Number of active threads in the top group: " + topGroup.activeCount());
        System.out.println("Number of active thread groups in the top group: " + topGroup.activeGroupCount());

        System.out.println("Number of active threads in the sub-group: " + subGroup.activeCount());
        System.out.println("Number of active thread groups in the sub-group: " + subGroup.activeGroupCount());

        System.out.println("Parent group of the sub-group: " + subGroup.getParent().getName());

        System.out.println("Is the top group a parent or ancestor of the sub-group? " + topGroup.parentOf(subGroup));

    }
}
