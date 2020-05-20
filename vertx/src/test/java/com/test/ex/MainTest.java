package com.test.ex;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-05-15 17:59
 * @Version 1.0
 * @Description MainTest
 */
public class MainTest {

  public static void main(String[] args) {
    User user = new User();
    user.setName("aaa");
    user.setPassword("123");
    System.out.println(user.toString());

    UserB.UserBuilder aaa = UserB.UserBuilder.create("aaa");
    aaa.withPassword("123");

    UserB build = aaa.build();
    aaa.withPassword("456");
    UserB build1 = aaa.build();
    aaa.withPassword("111");
    UserB build2 = aaa.build();
    System.out.println(build);
    System.out.println(build1);
    System.out.println(build2);

  }

}
