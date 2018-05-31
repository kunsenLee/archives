package com.archives.system.service.common;

import com.archives.system.dao.common.ICommonDao;
import com.archives.system.model.right.RightBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: lee
 * @Date: Create in 2018-4-25 14:23
 */
@Service
@Transactional
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private ICommonDao iCommonDao;

    @Override
    public List<RightBean> queryAllPerms() {

        return iCommonDao.queryAllPerms();
    }

    @Override
    public List<RightBean> queryTopNavs(Integer userid) {
        return iCommonDao.queryTopNavs(userid);
    }

    @Override
    public List<RightBean> queryChildNavs(Integer userid, Integer parentid) {
        return iCommonDao.queryChildNavs(userid,parentid);
    }
}
