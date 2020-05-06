package com.sencloud.shiro.service;

import com.sencloud.shiro.entity.User;

/**
 * @Author: jason
 * @Description:
 * @Date: Created in 21:17 2019/5/27
 */
public interface UserService {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
