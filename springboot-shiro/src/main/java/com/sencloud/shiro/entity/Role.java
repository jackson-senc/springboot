package com.sencloud.shiro.entity;


import lombok.Data;

import java.util.HashSet;
import java.util.Set;


/**
 * @Author: jason
 * @Description:
 * @Date: Created in 21:08 2019/5/27
 */
@Data
public class Role {

  private long roleId;
  private String roleName;


  private Set<Permission> permissions = new HashSet<>();

  private Set<User> users = new HashSet<>();

}
