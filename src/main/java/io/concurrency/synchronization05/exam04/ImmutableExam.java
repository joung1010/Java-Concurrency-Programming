package io.concurrency.synchronization05.exam04;

public class ImmutableExam implements Runnable{

    private Person person;

    public ImmutableExam(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " , Name : "+ person.getName()+ "  , Age: "+ person.getAge());
    }

    public static void main(String[] args) {
        Person person = new Person("Kim Mark", 25);
        for (int i = 0; i < 10; i++) {
            new Thread(new ImmutableExam(person)).start();
        }

    }

    static final class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
