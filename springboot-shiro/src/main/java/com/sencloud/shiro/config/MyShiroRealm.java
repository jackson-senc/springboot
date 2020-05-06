package com.sencloud.shiro.config;

import com.sencloud.shiro.entity.Permission;
import com.sencloud.shiro.entity.Role;
import com.sencloud.shiro.entity.User;

import com.sencloud.shiro.mapper.UserMapper;
import com.sencloud.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: jason
 * @Description:
 * @Date: Created in 13:36 2019/5/27
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User)principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionsList = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();
            if (!CollectionUtils.isEmpty(permissions)) {
                for (Permission permission : permissions) {
                    permissionsList.add(permission.getName());

                }
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissionsList);
        return authorizationInfo;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.findByUsername(username);
        if (user!= null) {
            //  认证通过后，存放在session,一般存放user对象,用户数据库中的密码,返回Realm名
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,
                    user.getPassword(),
                    this.getClass().getName());
            return authenticationInfo;
        }
        return null;
    }
}
