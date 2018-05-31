package com.archives.system.dao.role;

import com.archives.foundation.util.UtilMapper;
import com.archives.system.model.role.RoleBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IRoleDao extends UtilMapper<RoleBean> {

    public List<RoleBean> queryRoleByUserId(Integer userid);

}