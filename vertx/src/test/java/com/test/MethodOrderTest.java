package com.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Date 2020-07-15 14:25
 * @Description MethodOrderTest
 */
@FixMethodOrder(MethodSorters.JVM)
public class MethodOrderTest {

  @Test
  public void btest() {
    System.out.print("3");
  }

  @Test
  public void atest2() {
    System.out.print("1");
  }

  @Test
  public void atest1() {
    System.out.print("2");
  }

  @Test
  public void ctest() {
    System.out.print("4");
  }

}
