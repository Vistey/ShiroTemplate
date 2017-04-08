package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * 为当前登录的subject授予角色和权限
 * 该方法调用时机是用户访问权限资源时
 */
public class myshiro extends AuthorizingRealm {
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    //登录用户名
    String user = (String) super.getAvailablePrincipal(principalCollection);
    //获取该用户的信息，包括角色（通过数据库获取）,这里方便测试 直接写死了
    String role = user;
    //设置角色和权限
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    //给用户赋予角色
    info.addRole(role);
    //添加权限
    info.addStringPermission("admin"+role);
    return info;
  }

/**
 *验证当前登录的对象
 *该方法调用的时机是用户执行subject.login()时
 */
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
    //获取传入的用户名
    String username = token.getUsername();
    //用户状态检查，状态异常勾出错误
    //用户状态需要查表检测 这里写死以便测试
    if(username == null){
      throw new UnknownAccountException("该用户不在系统中，请确认用户名");
    }else if ("locked".equals(username)){
      throw new LockedAccountException("该用户被锁定，请联系管理员");
    }else if ("disable".equals(username)){
      throw new DisabledAccountException("当前用户未启用，联系管理员");
    }else if ("disableEmail".equals(username)){
      throw new DisabledAccountException("当前邮箱不可用，请激活邮箱");
    }
    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getUsername(),token
      .getPassword(),getName());
    setSession("ID",token.getUsername());
    return info;
  }

  /**
   * 设置session
   */
  private void setSession(String key, Object value){
    Subject subject = SecurityUtils.getSubject();
    if (subject.isAuthenticated()){
      Session session = subject.getSession();
      session.setAttribute(key,value);
    }
  }
}
