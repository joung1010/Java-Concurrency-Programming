package io.concurrency.threadUtilization04.exam02;

public class FlagThreadInterruptExam01 {
    private static boolean running = true;
//    private volatile static boolean running = true;
    public static void main(String[] args) {

        new Thread(() -> {
            int count = 0;
            while (running) {
                // 해당 변수일때 정상적으로 스레드1이 종료되지 않았음. 이유: 캐시 메모리 값은 변경되었으나 실제 메모리 값은 변경되지 않아서 스레드 1이 계속 작업함
                // 밑에 코드를 추가하니까 정상적으로 종료됨
                // 왜? : 각각의 CPU 마다 캐시 메모리 라는 것을 가지고 있다.(성능을 좀더 좋게하기 위해서 메모리보다 성능이 좋은 저장소)
                // 이때 각 스레드는 메모리에서 값을 가져오는 것보다 캐시 메모리에서 가져오는 것이 연산 속도가 더 빠르기 때문이다.
                // 예를 들어 우리 예제에서 스레드2먼에서 running의 값을 false로 변경하면 바로 메모리에 해당 값을 변경하는 것이 아니라 변경된 값이 캐시 메모리에 저장이 된다.
                // 각각 스레드들이 가지고 있는 TCB가 다르기 때문에 CPU가 다른 스레드로 작업이 할당될때마다 이 컨텍스트 문맥정보가 달라진다.
                // 스레드2번이 가지고 있는 running의 값은 false 이지만 스레드1번이 가지고 있는 스레드1번에 값은 true이다
                // 즉 두 스레드가 동일한 장소에 있는 공유 데이터를 참조하는 것이 아니라 스레드 마다 가지고 있는 캐시값을 활용하기 때문에 이런 현상이 발생한다.
                // 이 volatile 이라는 키워드가 동일한 메모리에 접근해서 읽고 쓰고 하는 것을 보장해주는 키워드 이다.
                // 그러면 왜 스레드1에 Thread.sleep(1) 코드를 추가하니까 정상작동하는 이유는 아주 짧은 시간이여도 컨텍스트 스위칭이 발생한다.(스레드간 가지고 있는 문맥정보를 전환한다)
                // 컨텍스트 스위칭이 발생하게 되면 해당 스레드에 있는 캐시값을 비워줘야 되기때문이다 왜냐하면 전환된 스레드의 문맥정보를 캐시로 사용해야 되기 때문이다.
                // 스레드가 대기 상태에서 다시 running 상태로 변경되었을때는 기존에 가지고 있던 문맥정보가 없어졌기 때문에 새롭게 메모리에서부터 정보를 가지고 오게된다.
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count ++;
            }
            System.out.println("Thread 1 is Done.. : "+ count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 2 is Done..  ");
            running = false;
        }).start();

    }
}
