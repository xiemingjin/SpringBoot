package com.xiemj.dao;

import com.xiemj.pojo.UserEntity;

import java.util.Map;

public interface UserDao {


    public void saveUser(Map<String,Object> user);


    public void updateUser(Map<String,Object> user);

}
