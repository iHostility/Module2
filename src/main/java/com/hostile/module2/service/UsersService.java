package com.hostile.module2.service;

import com.hostile.module2.entity.Role;
import com.hostile.module2.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsersService {
    Users saveUser(Users users);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    Users getUser(String username);
    List<Users> getUser();
    UserDetails loadUserByUsername(String username);
}
