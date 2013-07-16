package com.bytepoxic.core.dao;

import com.bytepoxic.core.model.Email;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Email.class)
public interface EmailDAO {
}
