package io.concurrency.cas08.exam02;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExam {
    public static void main(String[] args) throws InterruptedException {
        User user1 = new User("Mason", 25);
        User user2 = new User("Bob", 30);

        AtomicReference<User> userRef = new AtomicReference<User>(user1);
        Thread thread1 = new Thread(() -> {
            User update = new User("Kai", 45);
            boolean success = userRef.compareAndSet(user1, update);
            if (success) {
                System.out.println("스레드 1이 " + update + "로 변경 했습니다.");
            } else {
                System.out.println("스레드 1이 " + update + "로 변경 실패 했습니다.");
            }
        });
        Thread thread2 = new Thread(() -> {
            User update = new User("David", 35);
            boolean success = userRef.compareAndSet(user2, update);
            if (success) {
                System.out.println("스레드 2이 " + update + "로 변경 했습니다.");
            } else {
                System.out.println("스레드 2이 " + update + "로 변경 실패 했습니다.");
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final User: " + userRef.get());
    }

}


class User {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
