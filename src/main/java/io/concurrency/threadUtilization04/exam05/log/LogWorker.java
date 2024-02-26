package io.concurrency.threadUtilization04.exam05.log;

public class LogWorker implements Runnable{
    @Override
    public void run() {
        ThreadLocalLog.ServiceA serviceA = new ThreadLocalLog.ServiceA();
        ThreadLocalLog.ServiceB serviceB = new ThreadLocalLog.ServiceB();
        ThreadLocalLog.ServiceC serviceC = new ThreadLocalLog.ServiceC();

        if (Thread.currentThread().getName().equals("Thread-1")) {
            serviceA.process();
            serviceB.process();
            serviceC.process();
        } else if (Thread.currentThread().getName().equals("Thread-2")) {
            serviceB.process();
            serviceA.process();
            serviceC.process();
        } else {
            serviceC.process();
            serviceA.process();
            serviceB.process();
        }

        ThreadLocalLog.printLog();
        ThreadLocalLog.clearLog();
    }
}
