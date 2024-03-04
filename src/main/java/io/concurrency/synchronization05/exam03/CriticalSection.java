package io.concurrency.synchronization05.exam03;

public class CriticalSection {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread t1 = new Thread(resource::increment);
        Thread t2 = new Thread(resource::increment);

        t1.start();
        t2.start();
    }
}

class SharedResource {
    private int counter = 0;

    public void increment() {

        for (int i = 0; i < 100000; i++) {

            synchronized (this) { // Entry Section

                // Critical Section
                counter++;
                System.out.println(Thread.currentThread().getName() + ": " + counter);

            }// Exit Section
        }

        // Remainder Section
        doOtherWork();
    }

    private void doOtherWork() {
        System.out.println(Thread.currentThread().getName() + " is working out of  critical section");
    }

    public int getCounter() {
        return counter;
    }
}
