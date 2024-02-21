package io.concurrency.threadUtilization04.exam04;

public class NestedThreadGroupExam {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup topThreadGroup = new ThreadGroup("Top Thread Group");
        ThreadGroup subThreadGroup = new ThreadGroup(topThreadGroup,"Sub Thread Group");

        Thread topGroupThread = new Thread(topThreadGroup,new ThreadGroupExam.GroupRunnable(), "TopGroupThread");
        Thread subGroupThread = new Thread(subThreadGroup,new ThreadGroupExam.GroupRunnable(),"SubGroupThread");


        topGroupThread.start();
        subGroupThread.start();

        Thread.sleep(1000);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("About Top Thread Group Information");
        topThreadGroup.list();
    }

    static class GroupRunnable implements Runnable {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName()+ " is part of " +thread.getThreadGroup().getName());
        }
    }
}
