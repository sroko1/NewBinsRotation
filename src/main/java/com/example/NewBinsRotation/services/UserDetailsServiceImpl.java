package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.PortalUser;
import com.example.NewBinsRotation.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PortalUserService portalUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PortalUser portalUser = portalUserService.findByLogin(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (portalUser != null) {


            for (Role role : portalUser.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        } else {
            throw new UsernameNotFoundException(username + "is not found");
        }

        return new User(portalUser.getLogin(), portalUser.getPassword(), grantedAuthorities);
    }
}