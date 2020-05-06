package com.sencloud.shiro.service.impl;

import com.sencloud.shiro.entity.UserRegistration;
import com.sencloud.shiro.mapper.UserMapper;
import com.sencloud.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: jason
 * @Description:
 * @Date: Created in 21:18 2019/5/27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserRegistration findByUsername(String username) {

        return  userMapper.findByUsername(username);
    }
}
