package com.study.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import sun.misc.Service;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-06-30 14:54
 * @Version 1.0
 * @Description SpiTest
 */
public class SpiTest {

  public static void main(String[] args) {

    Iterator<SPIService> providers = Service.providers(SPIService.class);
    ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);

    while (providers.hasNext()) {
      SPIService next = providers.next();
      next.execute();
    }
    System.out.println("--------------------------------");
    Iterator<SPIService> iterator = load.iterator();
    while(iterator.hasNext()) {
      SPIService ser = iterator.next();
      ser.execute();
    }

  }

}
