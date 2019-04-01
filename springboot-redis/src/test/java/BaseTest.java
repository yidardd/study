import java.util.concurrent.TimeUnit;

/**
 * Created by 东东 on 2019/3/24.
 */
public class BaseTest {

    public static Integer i = 0;

    public static void main(String[] args) throws Exception {
        BaseTest baseTest = new BaseTest();
        for (int j = 0; j < 10; j++) {
            new Thread(()
                    -> {
                baseTest.test1();
            }
            ).start();

            new Thread(()
                    -> {
                baseTest.test2();
            }
            ).start();

        }

    }

    public synchronized void test1() {
        i--;
        try {
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1    " + i);
    }

    public synchronized void test2() {
        i++;
        System.out.println("test2     " + i);
    }

}
