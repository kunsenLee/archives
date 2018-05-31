package com.archives.system.service.user;

import com.archives.system.dao.user.IUserDao;
import com.archives.system.model.department.DepartmentBean;
import com.archives.system.model.user.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: lee
 * @Date: Create in 2018-4-18 17:32
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Override
    public UserBean queryByUsername(String username) {
        return iUserDao.queryByUsername(username);
    }

    @Override
    public List<String> queryPermsByUserid(Integer userid) {
        return iUserDao.queryPermsByUserid(userid);
    }

    @Override
    public List<DepartmentBean> queryDept() {
        return iUserDao.queryDept();
    }

    @Override
    public List<UserBean> queryUserByDeptId(Integer deptid) {
        return iUserDao.queryUserByDeptId(deptid);
    }

    @Override
    public Integer userTotalByDeptId(Integer deptid) {

        return iUserDao.userTotalByDeptId(deptid);
    }

    /**
     * 启用/停用用户
     * @param userBean
     */
    @Override
    public Integer updateState(UserBean userBean) {
        Integer flag = iUserDao.updateByPrimaryKeySelective(userBean);
        return flag;
    }

}
