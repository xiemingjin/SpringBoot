package com.xiemj.MogodbDao;

import com.xiemj.pojo.UserEntity;

public interface UserDao {


    public void saveUser(UserEntity user);

    public UserEntity findUserByUserName(String userName);

    public void updateUser(UserEntity user);


    public void deleteUserById(Long id);
}
