package com.bytepoxic.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import com.bytepoxic.core.model.Location;

@RooJpaRepository(domainType = Location.class)
public interface LocationDAO {
	@Query("select l from Location l where l.parent = :parent order by l.name")
	List<Location> findLocationsByParent(@Param("parent") Location parent);
	
	@Query("select l from Location l where l.parent = null order by l.name")
	List<Location> findMainLocations();
	
	@Query("select l from Location l where l.name = :name order by l.name")
	List<Location> findLocationsByName(@Param("name") String name);
	
	@Query("select l from Location l where l.code = :code order by l.name")
	List<Location> findLocationsByCode(String code);
}
