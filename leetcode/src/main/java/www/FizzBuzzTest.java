package www;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2019/10/15 17:23
 * @Version 1.0
 * @Description FizzBuzzTest
 */
public class FizzBuzzTest {

    public static void main(String[] args) {

        System.out.println(7 % 3);

    }

}

class FizzBuzz {
    private int n;

    private AtomicInteger i = new AtomicInteger(1);

    private CountDownLatch countDownLatchFizzBuzz;
    private CountDownLatch countDownLatchNumber;


    public FizzBuzz(int n) {
        this.n = n;
        countDownLatchFizzBuzz = new CountDownLatch(1);

    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {

        if (i.get() <= n && i.get() % 3 == 0) {
            System.out.println("fizz");
        }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {

        if (i.get() <= n && i.get() % 5 == 0) {
            System.out.println("buzz");
        }

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        if (i.get() <= n && i.get() % 5 == 0 && i.get() % 3 == 0) {
            System.out.println(i);
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

    }
}
