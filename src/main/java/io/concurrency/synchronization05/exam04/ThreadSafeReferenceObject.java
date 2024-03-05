package io.concurrency.synchronization05.exam04;

import java.lang.reflect.Member;

public class ThreadSafeReferenceObject {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Thread Safe Logic");
        new Thread(new MyRunnable(new Company("user"))).start();
        new Thread(new MyRunnable(new Company("user"))).start();

        Thread.sleep(1000);
        System.out.println("-----------------------------------------------------------");
        System.out.println("None Thread Safe Logic");
        Company cp = new Company("User");

        new Thread(new MyRunnable(cp)).start();
        new Thread(new MyRunnable(cp)).start();
        System.out.println("-----------------------------------------------------------");
    }

    static class MyRunnable implements Runnable {
        private Company company;

        public MyRunnable(Company company) {
            this.company = company;
        }

        @Override
        public void run() {
            company.changeNm(Thread.currentThread().getName());
        }
    }

    static class Company {
        private MyMember member;

        public Company(String name) {
            this.member = new MyMember(name);
        }

        public synchronized void changeNm(String name) {
            String oldName = member.getName();
            member.setName(name);
            System.out.println("oldName = " + oldName + " , newName : " + member.getName());
        }
    }

    static class MyMember {
        private String name;

        public MyMember(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
