package com.archives.foundation.util;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lee
 * @Date: Create in 2018-5-22 13:40
 */
@Service
public class Util {

    /**
     * 密码加密
     * @param password
     * @param username
     * @return
     */
    public String encrypt(String password, String username) {

        Md5Hash hashpassword = new Md5Hash(password, username,2);

        return String.valueOf(hashpassword);
    }

    /**
     * 分页通用方法
     * @param request
     */
    public void pageUtil(HttpServletRequest request) {
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        PageHelper.startPage(page,limit);
    }

    /**
     * 返回前端表格数据通用方法
     * @param list
     * @param total
     * @return
     */
    public Map getUtilMap(List list , Integer total) {

        Map map = new HashMap();

        map.put("code", 0);
        map.put("msg","");
        map.put("count",total);
        map.put("data",list);
        return map;
    }
}
