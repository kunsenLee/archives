package com.archives.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义认证过滤器，加入rememberMe的功能
 * @Author: lee
 * @Date: Create in 2018-5-10 14:08
 */
public class UserFilter extends FormAuthenticationFilter{

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue){

        Subject subject = getSubject(request,response);
        /** 如果isAuthenticated为false，则该用户在3分钟内未登录过 */
        if(!subject.isAuthenticated() && subject.isRemembered()){
            //查看session是否为空
            Session session = subject.getSession(true);

            //查看session属性当前是否为空
            if(session.getAttribute("J") == null){


            }

        }
        return subject.isAuthenticated() || subject.isRemembered();
    }
}
