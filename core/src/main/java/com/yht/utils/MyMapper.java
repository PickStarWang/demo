package com.yht.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @description
 * @author:
 * @create: 2019-08-07 18:04
 **/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
