package com.gome.project.service.impl;

import com.gome.project.common.MD5;
import com.gome.project.common.util.RandomString;
import com.gome.project.dao.UserMapper;
import com.gome.project.entity.User;
import com.gome.project.service.UserService;
import com.gome.project.service.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by qiaowentao on 2016/8/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int registerUser(User user) {
        DBContextHolder.setDataSource("dataSourceOne");
        User paramUser = new User();
        paramUser.setUsername(user.getUsername());
        User checkUser = userMapper.selectUserWithConditions(paramUser);
        if(checkUser == null){
            String salt = RandomString.generateRandomString(16);
            user.setSalt(salt);
            String password = user.getPassword();
            //密码进行加密
            password = MD5.md5Password(salt,password);
            user.setPassword(password);
            user.setCreatetime(new Date());
            user.setUpdtime(new Date());
            userMapper.insertSelective(user);
            return 1;
        }else{
            //已经存在
            return 0;
        }


    }

    @Override
    public User login(User user) {
        DBContextHolder.setDataSource("dataSourceOne");
        String userName = user.getUsername();
        User selectUser = new User();
        selectUser.setUsername(userName);
        //根据登录名查询用户的信息，如：盐值
        selectUser = userMapper.selectUserWithConditions(selectUser);
        if(selectUser != null){
            String password = user.getPassword();
            String salt = selectUser.getSalt();
            //对登录密码进行加密
            password = MD5.md5Password(salt,password);
            user.setPassword(password);
            user = userMapper.selectUserWithConditions(user);
            return user;
        }

        return null;
    }

    @Override
    public void updateUser(User user) {
        DBContextHolder.setDataSource("dataSourceOne");
        userMapper.updateByUserName(user);
    }

    @Override
    public void deleteUser(User user) {
        DBContextHolder.setDataSource("dataSourceOne");
        userMapper.deleteByPrimaryKey(user);
    }

    @Override
    public List<User> selectUserList(User user) {
        DBContextHolder.setDataSource("dataSourceOne");
        return userMapper.selectUserList(user);
    }

    @Override
    public User checkUserWithConditions(User user) {
        DBContextHolder.setDataSource("dataSourceOne");
        return userMapper.selectUserWithConditions(user);
    }
}

