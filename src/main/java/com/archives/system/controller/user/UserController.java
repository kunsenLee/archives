package com.archives.system.controller.user;


import com.archives.foundation.util.Util;
import com.archives.system.dao.user.IUserDao;
import com.github.pagehelper.PageHelper;
import com.archives.system.model.department.DepartmentBean;
import com.archives.system.model.user.UserBean;
import com.archives.system.service.user.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lee
 * @Date: Create in 2018-4-20 16:41
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private Util util;


    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String login(UserBean userBean, HttpServletRequest request,Model model){
        //用shiro进行登录
        Subject subject = SecurityUtils.getSubject();

        //使用shiro对密码进行加密
        //第一个参数：原密码，如123456
        //第二个参数：盐，自定义的一个变量，这里选用户名作为变量加密
        //第三个参数：加密次数
        Md5Hash hashpasword = new Md5Hash(userBean.getUserpass(),userBean.getUsername(),2);

        UsernamePasswordToken token = new UsernamePasswordToken(userBean.getUsername(), String.valueOf(hashpasword));

        try {
            subject.login(token);
            //登录成功，获取一些登陆信息
            UserBean user = (UserBean) subject.getPrincipal();
            request.getSession().setAttribute("username",user.getRealname());
            model.addAttribute("name",user.getRealname());
            if(user.getState().equals("1")){
                model.addAttribute("msg","该用户已停用");
                return "login/login";
            }else {
                return "/index";
            }
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户不存在，请输入正确用户");
            return "login/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码错误，请输入正确密码");
            return "login/login";
        }
    }

    /**
     * 注销登录
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        //shiro底层删除session的会话信息
        subject.logout();
        //返回登录页面
        return "redirect:/loginPage";
    }

    /**
     * 获取部门树形结构
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDeptTree",method = {RequestMethod.GET,RequestMethod.POST})
    public List<DepartmentBean> getDeptTree(){
        List<DepartmentBean> deptList = iUserService.queryDept();
        return deptList;
    }

    /**
     * 根据部门id获取该部门用户
     */
    @ResponseBody
    @RequestMapping(value = "/getUserByDeptId/{deptid}",method = {RequestMethod.GET,RequestMethod.POST})
    public Map getUserByDeptId(@PathVariable("deptid") Integer deptid, HttpServletRequest request){

        util.pageUtil(request);

        List<UserBean> userList = iUserService.queryUserByDeptId(deptid);
        Integer total = iUserService.userTotalByDeptId(deptid);

        return util.getUtilMap(userList,total);
    }

    /**
     * 启用/停用用户
     * @param userBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateState" , method = {RequestMethod.GET,RequestMethod.POST})
    public Integer updateState(UserBean userBean){
        if (userBean.getState().equals("true")) {
            userBean.setState("0");
        } else {
            userBean.setState("1");
        }
        int flag = iUserDao.updateByPrimaryKeySelective(userBean);
        return flag;
    }

    /**
     * 重置密码
     * 重置后的密码为1
     * @param userBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/resetPassword", method = {RequestMethod.GET, RequestMethod.POST})
    public Integer resetPassword(UserBean userBean){
        Md5Hash hashpasword = new Md5Hash("1",userBean.getUsername(),2);
        userBean.setUserpass(String.valueOf(hashpasword));
        int flag = iUserDao.updateByPrimaryKeySelective(userBean);
        return flag;
    }

    /**
     * 打开添加用户页面
     * @return
     */
    @RequestMapping(value = "/userAddPage")
    public String userAddPage() {
        return "system/userAdd";
    }

    /**
     * 添加用户
     * @param userBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addUser", method = {RequestMethod.GET,RequestMethod.POST})
    public Integer addUser(UserBean userBean) {
        if (userBean.getState().equals("on")){
            userBean.setState("0");
        } else {
            userBean.setState("1");
        }
        Md5Hash hashpwd = new Md5Hash(userBean.getUserpass(), userBean.getUsername(),2);
        userBean.setUserpass(String.valueOf(hashpwd));
        Integer flag = iUserDao.insertSelective(userBean);
        return flag;
    }

    /**
     * 打开修改用户页面
     * @return
     */
    @RequestMapping(value = "/userUpdatePage/{userid}", method = {RequestMethod.GET,RequestMethod.POST})
    public String userUpdatePage(@PathVariable("userid") Integer userid, Model model) {

        UserBean userBean = iUserDao.selectByPrimaryKey(userid);

        model.addAttribute("user", userBean);
        return "system/userUpdate";
    }

    /**
     * 修改用户
     * @param userBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUser" , method = {RequestMethod.GET,RequestMethod.POST})
    public Integer updateUser(UserBean userBean) {
        Integer flag = iUserDao.updateByPrimaryKeySelective(userBean);
        return flag;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser/{userid}")
    public Integer deleteUser(@PathVariable("userid") int[] userid) {
        Integer flag = iUserDao.deleteUser(userid);

        return flag;
    }

    /**
     * 跳转到用户角色分配页面
     * @return
     */
    @RequestMapping(value = "/userRolePage/{userid}")
    public String userRolePage(@PathVariable("userid") Integer userid, Model model) {
        UserBean userBean = iUserDao.queryUserByUserId(userid);
        model.addAttribute("user", userBean);
        return "system/userRole";
    }
}
