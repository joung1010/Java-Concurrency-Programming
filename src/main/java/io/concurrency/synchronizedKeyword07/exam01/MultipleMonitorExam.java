package io.concurrency.synchronizedKeyword07.exam01;
class BankAccount {
    private double balance;
    private final Object lock = new Object();

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        synchronized (lock) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        synchronized (lock) {
            if (balance < amount) {
                return false;
            }
            balance -= amount;
            return true;
        }
    }

    public boolean transfer(BankAccount to, double amount) {
        // 한 모니터가 다른 객체의 모니터를 가지고 있는 형태다
        // 이는 한 모니터가 작업이 끝날때까지는 to객체의 락을 획득할 수 없다.
        // 해당 로직에서는 A 계좌에서 B계좌로 송금이 완료 한다는 로직의 원자성을 보장해야 줘야한다.
        // 만약 A 계좌에서 출금을 했는데 B계좌에 송금이 실패하면 A계좌는 출금만한 형태가 발생한다.
        // 따라서 A 의 출금과 B의 송금 모두 원자성을 보장해야 하기때문에 B에도 객체에도 락을 걸어줘야한다.
        synchronized (this.lock) {
            if (this.withdraw(amount)) {
                synchronized (to.lock) {
                    to.deposit(amount);
                    return true;
                }
            }
            return false;
        }
    }

    public double getBalance() {
        synchronized (lock) {
            return balance;
        }
    }

}
public class MultipleMonitorExam {
    public static void main(String[] args) {
        BankAccount accountA = new BankAccount(1000);
        BankAccount accountB = new BankAccount(1000);

        // accountA에서 accountB로 송금하는 스레드
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountA.transfer(accountB, 10);
                if (result) {
                    System.out.println("accountA에서 accountB로 10 송금 성공");
                } else {
                    System.out.println("accountA에서 accountB로 10 송금 실패");
                }
            }
        });

        // accountB에서 accountA로 송금하는 스레드
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountB.transfer(accountA, 10);
                if (result) {
                    System.out.println("accountB에서 accountA로 10 송금 성공");
                } else {
                    System.out.println("accountB에서 accountA로 10 송금 실패");
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("accountA의 최종 잔액: " + accountA.getBalance());
        System.out.println("accountB의 최종 잔액: " + accountB.getBalance());
    }
}
