package com.controller.LookupTest;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;


/**
 * @controller 注释可以省略bean的配置
 * @lookup 注释代替xml文件中的lookup-method标签
 * 只有需要被装配的资源需要用到@Resource来注释
 * @Scope("prototype") 注释代替bean中scope=“prototype”属性
 */
@Controller
public abstract class HjlCarrier {

  private WhlCarrier whlCarrier;

  @Lookup
  abstract WhlCarrier getEntity();

  WhlCarrier getWhlCarrier(){
    return this.getEntity();
  }
}
