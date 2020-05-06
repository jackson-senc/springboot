package com.sencloud.shiro.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: jason
 * @Description:
 * @Date: Created in 21:48 2019/5/27
 */
@Data
public class User {

   private Integer id;

   private String username;

   private String password;

   private Set<Role> roles = new HashSet<>();
}
