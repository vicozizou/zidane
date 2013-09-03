package com.bytepoxic.core.dao;

import com.bytepoxic.core.model.Place;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Place.class)
public interface PlaceDAO {
}
