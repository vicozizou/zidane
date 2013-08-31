package com.bytepoxic.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import com.bytepoxic.core.model.AppRole;

@RooJpaRepository(domainType = AppRole.class)
public interface AppRoleDAO {
	@Query("select ar from AppRole ar where ar.name = :name")
	List<AppRole> findAppRolesByName(@Param("name") String name);
}
