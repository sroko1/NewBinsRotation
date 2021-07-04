package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.PortalUser;
import com.example.NewBinsRotation.models.Role;
import com.example.NewBinsRotation.repositories.RoleRepository;
import com.example.NewBinsRotation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PortalUserServiceImpl implements PortalUserService {

    final private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    public PortalUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PortalUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Page<PortalUser> findAllPaginated(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<PortalUser> findByLastNameBeginsWith(String lastName, Pageable pageable) {
        return userRepository.findByLastNameStartsWith(lastName, pageable);
    }

    @Override
    public void save(PortalUser portalUser) {
        portalUser.setPassword(passwordEncoder.encode(portalUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(4,"USER"));
        portalUser.setRoles(roles);
        userRepository.save(portalUser);
    }

}