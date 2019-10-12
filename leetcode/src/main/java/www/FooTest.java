package www;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2019/10/12 16:58
 * @Version 1.0
 * @Description FooTest
 *
 *
 *
 *
 *
 *我们提供了一个类：
 *
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: "onetwothree"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
 * 正确的输出是 "onetwothree"。
 * 示例 2:
 *
 * 输入: [1,3,2]
 * 输出: "onetwothree"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
 * 正确的输出是 "onetwothree"。
 *  
 *
 * 注意:
 *
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 *
 * 你看到的输入格式主要是为了确保测试的全面性。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 总结：所有的解法都是一个理念,无论采用何种方式，开始时必须执行first线程，然后设置条件满足second执行而first和third线程都不能执行，同时只有first线程执行完才能给与该条件，然后设置条件满足third执行而first和second线程都不能执行，同时只有second线程执行成功后才能给与该条件
 *
 * 作者：no-one-9
 * 链接：https://leetcode-cn.com/problems/print-in-order/solution/javayou-jie-by-no-one-9/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 */
public class FooTest {

    public static void main(String[] args) throws Exception {
        Foo foo = new Foo();

        CompletableFuture.runAsync(() -> {
            try {
                foo.first(()->{
                    System.out.println(1);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture.runAsync(() -> {

            try {
                foo.third(()->{
                    System.out.println(3);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture.runAsync(() -> {

        try {
                foo.second(()->{
                System.out.println(2);
            });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        while (true) {

        }
    }


}

class Foo {
    private CountDownLatch countDownLatchF;
    private CountDownLatch countDownLatchS;

    public Foo() {
        countDownLatchF = new CountDownLatch(1);
        countDownLatchS = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        countDownLatchF.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        countDownLatchF.await();
        printSecond.run();
        countDownLatchS.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        countDownLatchS.await();
        printThird.run();
    }
}

