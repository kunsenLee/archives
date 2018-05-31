package com.archives.system.dao.common;

import com.archives.system.model.right.RightBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: lee
 * @Date: Create in 2018-4-24 15:00
 */
@Mapper
public interface ICommonDao {


    /**
     * 获取所有的权限资源
     * @return
     */
    public List<RightBean> queryAllPerms();

    /**
     * 根据当前用户获取顶部导航菜单
     * @param userid
     * @return
     */
    public List<RightBean> queryTopNavs(Integer userid);

    /**
     * 根据当前用户以及一级菜单获取二级菜单
     * @param userid
     * @param parentid
     * @return
     */
    public List<RightBean> queryChildNavs(Integer userid,Integer parentid);


}
