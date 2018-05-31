package com.archives.system.controller.user;

import com.archives.system.dao.user.IUserRoleDao;
import com.archives.system.model.user.UserRoleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @Author: lee
 * @Date: Create in 2018-5-28 13:25
 */
@Controller
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private IUserRoleDao iUserRoleDao;

    /**
     * 新增用户角色信息
     * @param userid
     * @param roleid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addUserRole")
    public Integer addUserRole(@RequestParam("userid") BigDecimal userid,
                               @RequestParam("roleid[]") BigDecimal[] roleid) {
        UserRoleBean userRoleBean = new UserRoleBean();

        Integer flag = 0;
        Integer delFlag = iUserRoleDao.deleteUserrole(userid);

        userRoleBean.setUserid(userid);

        if (delFlag >= 0) {
            for (int i = 0 ; i < roleid.length ; i++) {
                userRoleBean.setRoleid(roleid[i]);
                flag += iUserRoleDao.insertSelective(userRoleBean);
            }
        }
        return flag;
    }
}
