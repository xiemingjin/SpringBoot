package com.xiemj.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface TestDao {

    public List<Map<String,Object>>  queryList();
}
