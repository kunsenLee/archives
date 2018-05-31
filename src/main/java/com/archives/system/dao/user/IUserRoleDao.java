package com.archives.system.dao.user;

import com.archives.foundation.util.UtilMapper;
import com.archives.system.model.user.UserRoleBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface IUserRoleDao extends UtilMapper<UserRoleBean> {

    /**
     * 批量动态删除用户角色
     * @param userid
     * @return
     */
    public Integer deleteUserrole(@Param("userid") BigDecimal userid);
}