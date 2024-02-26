package io.concurrency.threadUtilization04.exam05.log;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalLog {
    private static final ThreadLocal<List<String>> THREAD_LOCAL_LOG = ThreadLocal.withInitial(ArrayList::new);

    public static void addLog(String log) {
        THREAD_LOCAL_LOG.get().add(log);
    }
    public static void printLog() {
        List<String> logs = THREAD_LOCAL_LOG.get();
        System.out.println("["+Thread.currentThread().getName()+"]"+ String.join("->",logs));
    }
    public static void clearLog() {
        THREAD_LOCAL_LOG.remove();
    }

    static class ServiceA {
        public void process() {
            addLog("Service A Logic starts");
        }
    }
    static class ServiceB {
        public void process() {
            addLog("Service B Logic starts");
        }
    }
    static class ServiceC {
        public void process() {
            addLog("Service C Logic starts");
        }
    }
}
