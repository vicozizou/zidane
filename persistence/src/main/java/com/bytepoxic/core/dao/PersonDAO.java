package com.bytepoxic.core.dao;

import com.bytepoxic.core.model.Person;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Person.class)
public interface PersonDAO {
}
