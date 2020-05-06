package com.sencloud.shiro.mapper;

import com.sencloud.shiro.entity.User;

import org.apache.ibatis.annotations.Select;

/**
 * @Author: jason
 * @Description:
 * @Date: Created in 13:55 2019/5/27
 */
public interface UserMapper {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Select(" select * from user_registration where name = #{username}")
    User findByUsername(String username);

}
