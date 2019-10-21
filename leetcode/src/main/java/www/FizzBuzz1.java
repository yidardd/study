package www;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class FizzBuzz1 {

    public static void main(String[] args) {
        HashSet<String> objects = new HashSet<>();
        objects.add("q");
        objects.add("q");
        System.out.println(objects);
    }

    public static void main1(String[] args) {
        FizzBuzz1 foo = new FizzBuzz1(12);



        CompletableFuture.runAsync(() -> {
            try {
                foo.fizz(()->{
                    System.out.println("fizz");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture.runAsync(() -> {

            try {
                foo.fizzbuzz(()->{
                    System.out.println("fizzbuzz");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture.runAsync(() -> {

            try {
                foo.buzz(()->{
                    System.out.println("buzz");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture.runAsync(() -> {

            try {
                foo.number(i->{
                    System.out.println(i);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private int n;
    private volatile int state = -1;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    public FizzBuzz1(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {   //只输出3的倍数(不包含15的倍数)
            if (i % 15 == 0)    //15的倍数不处理，交给fizzbuzz()方法处理
                continue;
            lock.lock();
            while (state != 3) {
                cond.await();
            }
            printFizz.run();
            state = -1;    //控制权交还给number()方法
            cond.signalAll();    //全体起立
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {   //只输出5的倍数(不包含15的倍数)
            if (i % 15 == 0)    //15的倍数不处理，交给fizzbuzz()方法处理
                continue;
            lock.lock();
            while (state != 5) {
                cond.await();
            }
            printBuzz.run();
            state = -1;    //控制权交还给number()方法
            cond.signalAll();    //全体起立
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {   //只输出15的倍数
            lock.lock();
            while (state != 15) {
                cond.await();
            }
            printFizzBuzz.run();
            state = -1;    //控制权交还给number()方法
            cond.signalAll();    //全体起立
            lock.unlock();

        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; ++i) {
            lock.lock();
            while (state != -1) {
                cond.await();
            }
            if (i % 3 != 0 && i % 5 != 0)
                printNumber.accept(i);
            else {
                if (i % 15 == 0)
                    state = 15;    //交给fizzbuzz()方法处理
                else if (i % 5 == 0)
                    state = 5;    //交给buzz()方法处理
                else
                    state = 3;    //交给fizz()方法处理

                cond.signalAll();    //全体起立
            }
            lock.unlock();
        }
    }
}