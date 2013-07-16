package com.bytepoxic.core.dao;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import com.bytepoxic.core.model.Location;

@RooJpaRepository(domainType = Location.class)
public interface LocationDAO extends LocationDAOCustom {
}
