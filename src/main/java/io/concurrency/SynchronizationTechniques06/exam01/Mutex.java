package io.concurrency.SynchronizationTechniques06.exam01;

public class Mutex {

    private boolean lock = false;
    public synchronized  void acquired() { //synchronized : 하나의 스레드만 해당 메소드에 접근하는 것을 보장하는 키워드
        while (lock) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.lock = true; //락을 획득하고 빠져나올때는 락의 값을 변경, 이렇게 해야만 첫번째 스레드가 임계영역에서 작업을 수행할때 다른 스레드가 접근했을때 대기하게 된다.(첫번째 스레드가 락을 해제할 때까지 값을 false로 변경할때 까지)
    }
    public synchronized  void release() {
        this.lock = false; // 락 을 해제
        this.notify();// 대기 중인 스레드를 깨움
    }
}
