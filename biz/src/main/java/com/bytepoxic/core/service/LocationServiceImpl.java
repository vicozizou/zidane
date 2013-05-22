package com.bytepoxic.core.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.throwing.ServiceCoreException;
import com.bytepoxic.core.util.LogUtils;

public class LocationServiceImpl implements LocationService {
	private static Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class.getName());
	
	@Override
	public List<Location> findLocationsByParent(Location parent) throws ServiceCoreException {
		if (parent == null) {
			logger.warn("Parent location is null, getting parent less locations");
		}
		try {
			List<Location> locations = Location.findLocationsByParent(parent).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("Locations found:" + LogUtils.formatCollection(locations, "\n"));
			}
			return locations;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding locations with parent %s", parent), e);
		}
	}

	@Override
	public List<Location> findMainLocations() throws ServiceCoreException {
		try {
			List<Location> locations = Location.findMainLocations().getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("Locations found:" + LogUtils.formatCollection(locations, "\n"));
			}
			return locations;
		} catch (Exception e) {
			throw new ServiceCoreException("Error finding main locations", e);
		}
	}

	@Override
	public List<Location> findLocationsByName(String name) throws ServiceCoreException {
		if (!StringUtils.hasText(name)) {
			logger.warn("Location name is empty, cannot find locations");
			return null;
		}
		try {
			List<Location> locations = Location.findLocationsByName(name).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("Locations found:" + LogUtils.formatCollection(locations, "\n"));
			}
			return locations;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding locations with name %s", name), e);
		}
	}

	@Override
	public List<Location> findLocationsByCode(String code) throws ServiceCoreException {
		if (!StringUtils.hasText(code)) {
			logger.warn("Location code is empty, cannot find locations");
			return null;
		}
		try {
			List<Location> locations = Location.findLocationsByCode(code).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("Locations found:" + LogUtils.formatCollection(locations, "\n"));
			}
			return locations;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding locations with code %s", code), e);
		}
	}
	
	@Override
	public Location buildLocationTree(Location start) throws ServiceCoreException {
		List<Location> children = null;
		if (start != null) {
			children = findLocationsByParent(start);
		} else {
			start = new Location();
			children = findMainLocations();
		}
		Collections.sort(children);
		for(Location child : children) {
			start.addLocation(child);
			buildLocationTree(child);
		}
		return start;
	}
}
