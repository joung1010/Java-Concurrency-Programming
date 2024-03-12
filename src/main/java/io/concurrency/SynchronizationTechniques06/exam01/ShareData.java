package io.concurrency.SynchronizationTechniques06.exam01;

public class ShareData {

    private int shareInteValue = 0;

    private Mutex mutex;

    public ShareData(Mutex mutex) {
        this.mutex = mutex;
    }

    public void sum() {
        this.mutex.acquired(); // 락 획득
        try {
            for (int i = 0; i < 10000000; i++) {
                shareInteValue++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.mutex.release();
        }

    }

    public int getSum() {
        return shareInteValue;
    }
}
