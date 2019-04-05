package com.test;

import com.test.config.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test1() {
		Object school=  SpringContextUtil.getBean("factoryBeanPojo");
		Object bean = SpringContextUtil.getBean("&factoryBeanPojo");

		System.out.println(school.getClass().getName());
		System.out.println(bean.getClass().getName());

	}

}
