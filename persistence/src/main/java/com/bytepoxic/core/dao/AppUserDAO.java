package com.bytepoxic.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import com.bytepoxic.core.model.AppUser;

@RooJpaRepository(domainType = AppUser.class)
public interface AppUserDAO {
	@Query("select au from AppUser au where au.username = :username")
	List<AppUser> findAppUserByUsername(@Param("username") String username);
}
