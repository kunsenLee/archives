package com.archives.system.dao.common;

import com.archives.system.model.right.RightBean;

import java.util.List;

/**
 * @Author: lee
 * @Date: Create in 2018-5-14 16:41
 */
public class ICommonDaoImpl implements ICommonDao {
    @Override
    public List<RightBean> queryAllPerms() {
        return null;
    }

    @Override
    public List<RightBean> queryTopNavs(Integer userid) {
        return null;
    }

    @Override
    public List<RightBean> queryChildNavs(Integer userid, Integer parentid) {
        return null;
    }

}
