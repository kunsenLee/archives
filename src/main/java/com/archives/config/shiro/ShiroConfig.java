package com.archives.config.shiro;

import com.archives.system.model.right.RightBean;
import com.archives.system.service.common.ICommonService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro配置类
 * @Author: lee
 * @Date: Create in 2018-4-20 15:27
 */
@Configuration
public class ShiroConfig {


    @Autowired
    private ICommonService iCommonService;

    /**
     * 创建ShiroFilterFactoryBean
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        //关联SecurityManager
        factoryBean.setSecurityManager(securityManager);

        Map<String,String> filterMap = new LinkedHashMap<>(); //有序的资源过滤Map

        //认证过滤器
        filterMap.put("/user/login","anon");

        //设置静态资源可匿名访问
        filterMap.put("/css/**","anon");
        filterMap.put("/image/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/json/**","anon");
        filterMap.put("/layui/**","anon");

        //将所有的权限放入filterMap过滤器
        List<RightBean> permsList = iCommonService.queryAllPerms();
        for (RightBean perms : permsList){
            if(perms.getHref() != null){
                filterMap.put(perms.getHref(),"perms[" + perms.getRightcode() + "]");
            }
        }

        filterMap.put("/**","authc");

        //添加shiro过滤器
        factoryBean.setFilterChainDefinitionMap(filterMap);

        //修改登录请求，所有请求都会跳转到登录页面
        factoryBean.setLoginUrl("/loginPage");

        //认证成功后跳转页面
        factoryBean.setSuccessUrl("/index");

        //添加未授权提示页面
        factoryBean.setUnauthorizedUrl("/errorPage");

        return factoryBean;
    }

    /**
     * 创建SecurityManager
     * @param shiroRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(ShiroRealm shiroRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //关联Realm
        securityManager.setRealm(shiroRealm);

        securityManager.setSessionManager(sessionManager());

        //4、关联rememberMeManager
        //securityManager.setRememberMeManager(rememberMeManager);

        return securityManager;
    }

    /**
     * 创建自定义Realm
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }


    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**
     * 2、创建CookieRememberMeManager
     * cookie管理器
     */
    /*@Bean
    public CookieRememberMeManager cookieRememberMeManager(SimpleCookie simpleCookie){
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        //3、该管理器去关联创建的cookie
        rememberMeManager.setCookie(simpleCookie);
        return rememberMeManager;
    }*/

    /**
     * RememberMe功能
     * 1、创建cookie
     */
    /*@Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //设置cookie的时间长度
        cookie.setMaxAge(180);
        //设置只读模式
        cookie.setHttpOnly(true);
        return cookie;
    }*/
}
