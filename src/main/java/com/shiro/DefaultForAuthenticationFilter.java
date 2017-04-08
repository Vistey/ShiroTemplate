package com.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * 实现表单filter让shiro为我们执行登录操作，只需要添加验证码，继承FormAuthenticationFilter
 * 需要我们注意以下几个方面
 * 1:在onAccessDenied中添加验证码操作，
 * 2:setFailureAttibute中我们也可以在requert对象中设置失败的消息
 * 3:在preHandle拦截登录请求，防止二次登录
 */
public class DefaultForAuthenticationFilter extends FormAuthenticationFilter {


  private String verificationCodeName;

  public void setVerificationCodeName(String verificationCodeName) {
    this.verificationCodeName = verificationCodeName;
  }

  public String getVerificationCodeName() {
    return verificationCodeName;
  }
}
