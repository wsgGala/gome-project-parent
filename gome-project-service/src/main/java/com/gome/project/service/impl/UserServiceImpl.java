package com.gome.project.service.impl;

import com.gome.project.common.MD5;
import com.gome.project.common.util.RandomString;
import com.gome.project.dao.UserMapper;
import com.gome.project.dao.YqmInfoMapper;
import com.gome.project.entity.User;
import com.gome.project.entity.YqmInfo;
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
    @Autowired
    private YqmInfoMapper yqmInfoMapper;


    @Override
    public int registerUser(User user) {
        DBContextHolder.setDataSource("dataSourceOne");
        User paramUser = new User();
        paramUser.setPhone(user.getPhone());
        User checkUser = userMapper.selectUserWithConditions(paramUser);
        if(checkUser == null){
            String salt = RandomString.generateRandomString(16);
            user.setSalt(salt);
            String password = user.getPassword();
            //密码进行加密
            password = MD5.md5Password(salt,password);
            user.setPassword(password);
            Date time = new Date();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setCreatetime(sdf.format(time));
            user.setUpdtime(sdf.format(time));
            userMapper.insertSelective(user);
            YqmInfo yqmInfo = new YqmInfo();
            yqmInfo.setYaoqingma(user.getYaoqingma());
            YqmInfo selYqmInfo = yqmInfoMapper.selectYqmInfoByYqm(yqmInfo);
            selYqmInfo.setUsecount(selYqmInfo.getUsecount()+1);
            yqmInfoMapper.updateYqmCount(selYqmInfo);
            return 1;
        }else{
            //已经存在
            return 0;
        }


    }

    @Override
    public User login(User user) {
        DBContextHolder.setDataSource("dataSourceOne");
        String phone = user.getPhone();
        User selectUser = new User();
        selectUser.setPhone(phone);
        //根据登录名查询用户的信息，如：盐值
        User selUser = userMapper.selectUserWithConditions(selectUser);
        if(selUser != null){
            String password = user.getPassword();
            String salt = selUser.getSalt();
            //对登录密码进行加密
            password = MD5.md5Password(salt,password);
            System.out.println(password);
            user.setPassword(password);
            user = userMapper.selectUserWithConditions(user);
            return user;
        }

        return null;
    }

    /**
     * 更新用户信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        DBContextHolder.setDataSource("dataSourceOne");
        userMapper.updateByUserName(user);
    }

    /**
     *删除用户，更新状态：手机号为可用
     * @param user
     */
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

    /**
     * 根据条件查找用户信息
     * @param user
     * @return
     */
    @Override
    public User checkUserWithConditions(User user) {
        DBContextHolder.setDataSource("dataSourceOne");
        return userMapper.selectUserWithConditions(user);
    }
}

