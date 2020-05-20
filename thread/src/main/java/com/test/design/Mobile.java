package com.test.design;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-05-15 16:21
 * @Version 1.0
 * @Description Mobile
 */

public class Mobile {

  private String brand;

  private String model;

  private Integer size;

  private BigDecimal money;

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }
}
