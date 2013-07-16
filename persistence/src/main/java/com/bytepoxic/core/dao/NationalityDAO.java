package com.bytepoxic.core.dao;

import com.bytepoxic.core.model.Nationality;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Nationality.class)
public interface NationalityDAO {
}
