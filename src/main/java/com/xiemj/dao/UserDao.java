package com.xiemj.dao;

import com.xiemj.pojo.User;
import org.apache.ibatis.annotations.Mapper;

public interface UserDao {

    User queryUserByName(String username);
}
