package com.bytepoxic.core.dao;

import com.bytepoxic.core.model.Phone;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Phone.class)
public interface PhoneDAO {
}
