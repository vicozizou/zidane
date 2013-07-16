package com.bytepoxic.core.dao;

import com.bytepoxic.core.model.AppRole;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = AppRole.class)
public interface AppRoleDAO extends AppRoleDAOCustom {
}
