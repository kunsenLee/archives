package com.archives.system.service.user;

import com.archives.system.model.department.DepartmentBean;
import com.archives.system.model.user.UserBean;

import java.util.List;

/**
 * @Author: lee
 * @Date: Create in 2018-4-18 17:32
 */
public interface IUserService {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public UserBean queryByUsername(String username);

    /**
     * 根据userid查询用户的权限资源码
     * @param userid
     * @return
     */
    public List<String> queryPermsByUserid(Integer userid);

    /**
     * 查询组织机构部门信息
     * @return
     */
    public List<DepartmentBean> queryDept();

    /**
     * 根据部门id获取该部门用户
     * @param deptid
     * @return
     */
    public List<UserBean> queryUserByDeptId(Integer deptid);

    /**
     * 根据部门id获取该部门用户数量
     * @param deptid
     * @return
     */
    public Integer userTotalByDeptId(Integer deptid);

    /**
     * 启用/停用用户
     * @param userid
     */
    public Integer updateState(UserBean userBean);
}
