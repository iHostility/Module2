package com.hostile.module2.service.impl;

import com.hostile.module2.entity.Role;
import com.hostile.module2.entity.Users;
import com.hostile.module2.repo.RoleRepository;
import com.hostile.module2.repo.UsersRepository;
import com.hostile.module2.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersServiceImpl implements UsersService, UserDetailsService {
    private final UsersRepository userRepo;
    private final RoleRepository roleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepo.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        users.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(), authorities);
    }

    @Override
    public Users saveUser(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return userRepo.save(users);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Users users = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        users.getRoles().add(role);
    }

    @Override
    public Users getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<Users> getUser() {
        return userRepo.findAll();
    }

}
