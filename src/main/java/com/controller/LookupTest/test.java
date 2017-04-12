package com.controller.LookupTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/*
 * junit测试方法（格式如下）
 * 类上加注释
 * @RunWith(SpringJUnit4ClassRunner.class)
 * @ContextConfiguration({"需要用到的spring.xml文件"})
 * 测试方法上加@test注解
 */

/**
 * controller中HjlCarrier类和WhlCarrier类用于测试Spring lookup
 * 在bean配置中需要构建一个单例bean并注入scope="prototype" 属性的多例bean
 * 每一次调用单例bean中多例bean 并且防止bean第一次在入是注入的多例bean不是第一个多例bean
 * <lookup-method name="getEntity" bean="whl"/>  getEntity：调用父类的方法
 * 父类中要写一个<public|protected> [abstract] <返回类型> 方法名（同lookup-method里的name一致）(无传入参数);
 * 也可以在父类抽象方法中添加@lookup注释 这杨bean中lookup-method可以省略
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:lookupMethod.xml"})
public class test {

  @Resource
  private HjlCarrier hjlCarrier;

  @Test
  public void testttt(){
    System.out.println(hjlCarrier.getWhlCarrier() == hjlCarrier.getWhlCarrier());
    System.out.println(hjlCarrier);
    System.out.println(hjlCarrier);
    System.out.println(hjlCarrier.getWhlCarrier());
    System.out.println(hjlCarrier.getWhlCarrier());
  }

}
