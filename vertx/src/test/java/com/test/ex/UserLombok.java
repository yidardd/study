package com.test.ex;

import lombok.Builder;
import lombok.Data;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-05-15 11:09
 * @Version 1.0
 * @Description User
 */
@Builder
@Data
public class UserLombok {

  private final String name;

  private final String password;

  public static void main(String[] args) {
    UserLombok.UserLombokBuilder build = new UserLombokBuilder().name("aa").password("aa");
    UserLombok build1 = build.build();
    UserLombok build2 = build.build();
    System.out.println(build.name);
  }

}
