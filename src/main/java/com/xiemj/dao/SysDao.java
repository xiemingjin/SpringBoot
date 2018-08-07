package com.xiemj.dao;

import com.xiemj.pojo.User;

public interface SysDao {

    User queryUserByName(String username);
}
