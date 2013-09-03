package com.bytepoxic.core.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.throwing.ServiceCoreException;

@RooService(domainTypes = { com.bytepoxic.core.model.Location.class })
public interface LocationService {
	List<Location> findLocationsByParent(Location parent) throws ServiceCoreException;
	List<Location> findMainLocations() throws ServiceCoreException;
	List<Location> findLocationsByName(String name) throws ServiceCoreException;
	List<Location> findLocationsByCode(String code) throws ServiceCoreException;
}
