package com.gome.project.dao;

import com.gome.project.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 更新用户信息：手机号为可用
     * @param user
     * @return
     */
    int deleteByPrimaryKey(User user);

    /**
     * 插入一条新数据
     * @param user
     * @return
     */
    int insertSelective(User user);

    /**
     * 查询所有的用户
     * @return
     */
    List<User> selectUserList(User user);

    /**
     * 根据条件查询用户信息
     * @param user
     * @return
     */
    User selectUserWithConditions(User user);

    /**
     * 根据用户手机号更新用户的信息
     * @param user
     * @return
     */
    int updateByUserName(User user);
}