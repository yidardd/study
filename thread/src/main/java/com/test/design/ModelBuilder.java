package com.test.design;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-05-15 16:24
 * @Version 1.0
 * @Description ModelBuilder
 */
public interface ModelBuilder {

  public void setBrand(String brand);


  public void setModel(String model);


  public void setSize(Integer size);


  public void setMoney(BigDecimal money);

}
