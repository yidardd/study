package com.test.queue;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import com.google.common.collect.Queues;
import com.test.User;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-06-09 11:22
 * @Version 1.0
 * @Description LinkedBlockingQueueTest
 */
public class LinkedBlockingQueueTest {


  public static void main(String[] args) {
    LinkedBlockingQueue<User> objects = Queues.newLinkedBlockingQueue();
    User user = new User("1", "1");
    ArrayList<User> objects1 = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      objects.add(user);
      if (!objects1.contains(user)) {
        objects1.add(user);
      }
    }
    User user2 = new User("2", "2");
    for (int i = 0; i < 3; i++) {
      objects.add(user2);
    }
    objects.remove(user);
    System.out.println(1);

  }

}
