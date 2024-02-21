package io.concurrency.threadUtilization04.exam04;

import java.lang.*;
public class ThreadGroupExam {
    public static void main(String[] args) {
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup customThreadGroup = new ThreadGroup("Custom Thread Group");

        Thread defaultGroupThread = new Thread(new GroupRunnable(), "DefaultGroupThread");
        Thread mainGroupThread = new Thread(mainThreadGroup,new GroupRunnable(),"MainGroupThread");
        Thread customGroupThread = new Thread(customThreadGroup,new GroupRunnable(),"CustomGroupThread");

        defaultGroupThread.start();
        mainGroupThread.start();
        customGroupThread.start();
    }

    static class GroupRunnable implements Runnable {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName()+ " is part of " +thread.getThreadGroup().getName());
        }
    }
}
