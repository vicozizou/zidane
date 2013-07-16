package com.bytepoxic.core.dao;

import com.bytepoxic.core.model.AppUser;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = AppUser.class)
public interface AppUserDAO extends AppUserDAOCustom {
}
