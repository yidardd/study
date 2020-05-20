package com.test.ex;

import lombok.Data;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-05-15 11:09
 * @Version 1.0
 * @Description User
 */
public class User {

  private String name;

  private String password;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String toString() {
    return "User(name=" + this.getName() + ", password=" + this.getPassword() + ")";
  }



}
