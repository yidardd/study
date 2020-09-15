package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by 东东 on 2018/10/28.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class RunApplication {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(RunApplication.class);
    springApplication.run(args);
  }

}
