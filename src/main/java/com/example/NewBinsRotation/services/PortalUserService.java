package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.PortalUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PortalUserService {

    PortalUser findByLogin(String login);

    Page<PortalUser> findAllPaginated(Pageable pageable);

    Page<PortalUser> findByLastNameBeginsWith(String lastName, Pageable pageable);

    void save(PortalUser portalUser);

}