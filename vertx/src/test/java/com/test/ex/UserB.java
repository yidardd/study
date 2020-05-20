package com.test.ex;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-05-15 17:49
 * @Version 1.0
 * @Description UserA
 */
public class UserB {

  private final String name;

  private final String password;

  public UserB(UserB.UserBuilder builder) {
    this.name = builder.name;
    this.password = builder.password;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String toString() {
    return "User(name=" + this.getName() + ", password=" + this.getPassword() + ")";
  }


  public static final class UserBuilder {
    private final String name;
    private String password;

    private UserBuilder(String names) {
      this.name = names;
    }

    //构建参数是必传参数
    public static UserB.UserBuilder create(String name) {
      return new UserB.UserBuilder(name);
    }

    public UserB.UserBuilder withPassword(String password) {
      this.password = password;
      return this;
    }

    public UserB build() {
      return new UserB(this);
    }
  }


}
