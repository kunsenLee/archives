package com.archives.system.controller.role;

import com.archives.foundation.util.Util;
import com.archives.system.dao.role.IRoleDao;
import com.archives.system.model.role.RoleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: lee
 * @Date: Create in 2018-5-25 10:11
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleDao iRoleDao;

    @Autowired
    private  Util util;


    /**
     * 获取所有角色信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRole" , method = {RequestMethod.GET, RequestMethod.POST})
    public Map getRole(HttpServletRequest request) {
        util.pageUtil(request);

        List<RoleBean> roleList = iRoleDao.selectAll();

        RoleBean roleBean = new RoleBean();
        Integer total = iRoleDao.selectCount(roleBean);

        return util.getUtilMap(roleList,total);
    }

    /**
     * 根据userid获取所有角色信息以及该用户拥有的角色
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRoleByUserid/{userid}" , method = {RequestMethod.GET,RequestMethod.POST})
    public List<RoleBean> getRoleByUserid(@PathVariable("userid") Integer userid) {

        List<RoleBean> roleList = iRoleDao.queryRoleByUserId(userid);
        return roleList;
    }

    /**
     * 根据角色id获取角色信息
     * @param roleid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRoleInfoByRoleId/{roleid}" , method = {RequestMethod.GET,RequestMethod.POST})
    public RoleBean getRoleInfoByRoleId(@PathVariable("roleid") Integer roleid) {
        RoleBean roleBean = iRoleDao.selectByPrimaryKey(roleid);
        return roleBean;
    }

}
