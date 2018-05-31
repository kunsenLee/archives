package com.archives.config.shiro;

import com.archives.system.model.user.UserBean;
import com.archives.system.service.user.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: lee
 * @Date: Create in 2018-4-18 17:05
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService iUserService;

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //得到当前用户
        Subject subject = SecurityUtils.getSubject();
        UserBean userBean = (UserBean) subject.getPrincipal();

        //查询该用户的权限
        List<String> perms = iUserService.queryPermsByUserid(userBean.getUserid());
        if (perms != null){
            for (String perm : perms){
                if (!StringUtils.isEmpty(perm)){
                    info.addStringPermission(perm);
                }
            }
        }
        return info;
    }

    /**
     * 获取认证（登录）信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //认证：判断用户名是否处存在，判断密码是否正确
        //1、获取用户输入的信息
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        //2、在数据库中查询用户
        UserBean userBean = new UserBean();
        userBean.setUsername(token.getUsername());
        userBean = iUserService.queryByUsername(token.getUsername());

        if (userBean == null){
            //用户不存在，返回null
            return null;
        }

        //当用户名存在，判断密码
        /**
         * 参数一：principal，用于把数据回传到login方法
         * 参数二：数据库密码
         *      shiro底层自动将数据库密码与我们输入的密码（token中获取的密码）进行比较
         *      结果：1）密码正确：认证通过，登录成功
         *           2）密码错误：自动抛出IncorrectCredentialsException（密码错误）异常，认证失败
         * 参数三：realm的名称，只有在多个realm的时候才会使用，如多个用户表
         */
        return new SimpleAuthenticationInfo(userBean,userBean.getUserpass(),"");
    }
}
