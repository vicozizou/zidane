package com.bytepoxic.core.dao;

import com.bytepoxic.core.model.UserTrack;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = UserTrack.class)
public interface UserTrackDAO extends UserTrackDAOCustom {
}
