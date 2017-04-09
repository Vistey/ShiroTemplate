package com.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 实现表单filter让shiro为我们执行登录操作，只需要添加验证码，继承FormAuthenticationFilter
 * 需要我们注意以下几个方面
 * 1:在onAccessDenied中添加验证码操作，
 * 2:setFailureAttibute中我们也可以在requert对象中设置失败的消息
 * 3:在preHandle拦截登录请求，防止二次登录
 */
public class DefaultForAuthenticationFilter extends FormAuthenticationFilter {

  private String verificationCodeName;

  private Logger log = LoggerFactory.getLogger(DefaultForAuthenticationFilter.class);

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    //验证码操作
    HttpServletRequest req = WebUtils.toHttp(request);
    if (req.getMethod().equalsIgnoreCase(POST_METHOD)){
      HttpSession session = req.getSession();

    }

    return super.onAccessDenied(request, response);
  }

  @Override
  protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
    //登录成功
    return super.onLoginSuccess(token, subject, request, response);
  }

  @Override
  protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
    //登录失败
    return super.onLoginFailure(token, e, request, response);
  }

  @Override
  protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
    //登录失败设置的消息
    log.error(ae.getMessage());
    super.setFailureAttribute(request, ae);
  }

  @Override
  protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
    //执行登录操作
    return super.executeLogin(request, response);
  }

  @Override
  protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

    return super.preHandle(request, response);
  }

  public void setVerificationCodeName(String verificationCodeName) {
    this.verificationCodeName = verificationCodeName;
  }

  public String getVerificationCodeName() {
    return verificationCodeName;
  }
}
