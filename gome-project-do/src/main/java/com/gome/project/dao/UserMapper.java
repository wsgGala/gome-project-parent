package com.gome.project.dao;

import com.gome.project.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int deleteByPrimaryKey(User user);

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

    int updateByUserName(User user);
}