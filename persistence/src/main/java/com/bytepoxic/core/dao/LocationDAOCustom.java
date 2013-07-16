package com.bytepoxic.core.dao;

import java.util.List;

import com.bytepoxic.core.model.Location;

public interface LocationDAOCustom {
	List<Location> findLocationsByParent(Location parent);
	List<Location> findMainLocations();
	List<Location> findLocationsByName(String name);
	List<Location> findLocationsByCode(String code);
}
