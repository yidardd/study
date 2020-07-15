package LoadBalancer;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 负载均衡工具类
 */
public class LoadBalancerUtil {

  private final static Random rand = new Random();

  private final static AtomicInteger nextCyclicCounter = new AtomicInteger(0);

  /**
   * 随机算法
   *
   * @param number 随机数的上线
   * @return 返回 0 ~ (number-1) 的随机数
   */
  public static int randomRule(int number) {
    return rand.nextInt(number);
  }

  /**
   * 轮巡算法
   *
   * @param modulo 取余的阔值
   * @return 当前轮巡的数值 0 ~ (number-1)
   */
  public static int roundRule(int modulo) {
    int current;
    int next;
    do {
      current = nextCyclicCounter.get();
      next = (current + 1) % modulo;
    } while (!nextCyclicCounter.compareAndSet(current, next));
    return next;
  }


  public static void main(String[] args) {
//    for (int i = 0; i < 1000; i++) {
//      System.out.println(randomRule(3));
//    }
//    System.out.println("===============================");
//    for (int i = 0; i < 1000; i++) {
//      System.out.println(roundRule(3));
//    }

    for (int i = 0; i < 10; i++) {
      CompletableFuture.runAsync(() -> {
        while (true) {
          System.out.println(roundRule(50));
        }
      });
    }
    try {
      TimeUnit.SECONDS.sleep(30);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
