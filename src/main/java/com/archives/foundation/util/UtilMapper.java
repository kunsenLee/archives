package com.archives.foundation.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: lee
 * @Date: Create in 2018-5-15 13:50
 */
public interface UtilMapper<T> extends Mapper<T> , MySqlMapper<T>{

}
