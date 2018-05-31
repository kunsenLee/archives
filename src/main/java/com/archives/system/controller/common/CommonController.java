package com.archives.system.controller.common;

import com.archives.system.model.right.RightBean;
import com.archives.system.model.user.UserBean;
import com.archives.system.service.common.ICommonService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用控制器
 * @author lee
 */
@Controller
public class CommonController {

    @Autowired
    private ICommonService iCommonService;

    /**
     * 根据权限资源获取导航菜单
     */
    @ResponseBody
    @RequestMapping(value = "/getNavs",method = RequestMethod.GET)
    public Map getNavs(HttpServletRequest request,HttpServletResponse response){
        Map map = new HashMap();
        String sessionId = request.getRequestedSessionId();

        if(sessionId != null) {

            SessionKey key = new WebSessionKey(sessionId,request,response);
            try {
                Session session = SecurityUtils.getSecurityManager().getSession(key);
                Object object = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                SimplePrincipalCollection collection = (SimplePrincipalCollection)object;
                UserBean userBean = (UserBean)collection.getPrimaryPrincipal();

                Integer userid = userBean.getUserid();
                List<RightBean> navToplist = iCommonService.queryTopNavs(userid);

                for (RightBean rightBean : navToplist){
                    Integer parentid = rightBean.getRightid();
                    List<RightBean> navChildlist = iCommonService.queryChildNavs(userid,parentid);
                    map.put(rightBean.getRightcode(),navChildlist);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 当访问了"/"的请求,返回到登录页面
     * @return
     */
    @RequestMapping(value = "/")
    public String slash(){
        return "login/login";
    }

    /**
     * 主页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response, HttpSession session,Model model){
        return "index";
    }


    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "/loginPage")
    public String loginPage(HttpServletRequest request, HttpServletResponse response, HttpSession session,Model model){

        return "login/login";
    }

    /**
     * 错误/无权限页面
     * @return
     */
    @RequestMapping(value = "/errorPage")
    public String errorPage(){
        return "404";
    }

    /**
     * 待归档页面
     * @return
     */
    @RequestMapping(value = "/preparingPage")
    public String preparingPage(){
        return "prepare/preparing";
    }

    /**
     * 已归档页面
     * @return
     */
    @RequestMapping(value = "/preparedPage")
    public String preparedPage(){
        return "prepare/prepared";
    }

    /**
     * 用户管理页面
     * @return
     */
    @RequestMapping(value = "/userManagerPage")
    public String userManagerPage(){
        return "system/userManager";
    }

    /**
     * 角色管理页面
     * @return
     */
    @RequestMapping(value = "/roleManagerPage")
    public String roleManagerPage() {
        return "system/roleManager";
    }
}



