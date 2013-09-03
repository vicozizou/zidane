package com.bytepoxic.core.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.throwing.ServiceCoreException;
import com.bytepoxic.core.util.LogUtils;

public class LocationServiceImpl implements LocationService {
	private static Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);
	
	@Override
	@Transactional(readOnly = true)
	public List<Location> findLocationsByParent(Location parent) throws ServiceCoreException {
		if (parent == null) {
			logger.warn("Parent location is null, getting parent less locations");
		}
		try {
			List<Location> locations = locationDAO.findLocationsByParent(parent);
			if (logger.isDebugEnabled()) {
				logger.debug("Locations found:" + LogUtils.formatCollection(locations, "\n"));
			}
			return locations;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding locations with parent %s", parent), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Location> findMainLocations() throws ServiceCoreException {
		try {
			List<Location> locations = locationDAO.findMainLocations();
			if (logger.isDebugEnabled()) {
				logger.debug("Locations found:" + LogUtils.formatCollection(locations, "\n"));
			}
			return locations;
		} catch (Exception e) {
			throw new ServiceCoreException("Error finding main locations", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Location> findLocationsByName(String name) throws ServiceCoreException {
		if (!StringUtils.hasText(name)) {
			logger.warn("Location name is empty, cannot find locations");
			return null;
		}
		try {
			List<Location> locations = locationDAO.findLocationsByName(name);
			if (logger.isDebugEnabled()) {
				logger.debug("Locations found:" + LogUtils.formatCollection(locations, "\n"));
			}
			return locations;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding locations with name %s", name), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Location> findLocationsByCode(String code) throws ServiceCoreException {
		if (!StringUtils.hasText(code)) {
			logger.warn("Location code is empty, cannot find locations");
			return null;
		}
		try {
			List<Location> locations = locationDAO.findLocationsByCode(code);
			if (logger.isDebugEnabled()) {
				logger.debug("Locations found:" + LogUtils.formatCollection(locations, "\n"));
			}
			return locations;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding locations with code %s", code), e);
		}
	}
}
