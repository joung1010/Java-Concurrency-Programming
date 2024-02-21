package io.concurrency.threadUtilization04.exam04;

public class ThreadGroupScopeExam {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup topThreadGroup = new ThreadGroup("Top Thread Group");
        ThreadGroup subThreadGroup = new ThreadGroup(topThreadGroup,"Sub Thread Group");

        Thread thread1 = new Thread(topThreadGroup, () -> {
            System.out.println("Changing the priority of a sub-group thread before changing it in the parent group thread " + subThreadGroup.getMaxPriority());
            subThreadGroup.setMaxPriority(7);
            System.out.println("Changing the priority of a sub-group thread after changing it in the parent group thread " + subThreadGroup.getMaxPriority());
        }, "TopThreadGroup");

        Thread thread2 = new Thread(subThreadGroup, () -> {
            System.out.println("Changing the priority setting of a parent group thread from a sub-group thread before the change " + topThreadGroup.getMaxPriority());
            topThreadGroup.setMaxPriority(4);
            System.out.println("Changing the priority setting of a parent group thread from a sub-group thread after the change " + topThreadGroup.getMaxPriority());
        }, "TopThreadGroup");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        // 스레드가 생성된 이후에 우선순위를 변경하면 적용되지 않는다.
        System.out.println(thread1.getName() + " : " + thread1.getPriority());
        System.out.println(thread2.getName() + " : " + thread2.getPriority());

        System.out.println("------------------------------------------------------------");
        Thread userThread1 = new Thread(topThreadGroup, () -> {}, "User Thread 1");
        Thread userThread2 = new Thread(subThreadGroup, () -> {}, "User Thread 2");

        userThread1.start();
        userThread2.start();

        userThread1.join();
        userThread2.join();

        // 스레드 그룹의 우선순위가 변경된 이후에 새롭게 생선된 스레드는 스레드 그룹의 우선순위를 적용받는다.
        System.out.println(userThread1.getName() + " : " + userThread1.getPriority()); //4
        System.out.println(userThread2.getName() + " : " + userThread2.getPriority()); //4

        // 부모 스레드 그룹의 최대 우선순위인 4를 하위 스레드 그룹은 해당 운선순위 보다 높은 값을 설정 할 수 없다.
    }
}
