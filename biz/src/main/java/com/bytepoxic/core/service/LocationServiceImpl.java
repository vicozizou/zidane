package com.bytepoxic.core.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.model.Nationality;
import com.bytepoxic.core.throwing.ServiceCoreException;
import com.bytepoxic.core.util.LogUtils;

@Service(value = "locationService")
public class LocationServiceImpl implements LocationService {
	private static Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);
	
	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void createLocation(Location location) throws ServiceCoreException {
		if (location == null) {
			throw new ServiceCoreException("Location is null, cannot create it");
		}
		if (location.getName() == null) {
			logger.warn("Detected null name, doing nothing");
			return;
		}
		try {
			prepareSaveLocation(location);
			if (location.hasParent()) {
				location.merge();
			} else {
				location.persist();
			}
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error persisting location %s", location), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Location persisted with id %s", location.getId()));
		}
	}

	private void prepareSaveLocation(Location location) {
		prepareUpdateLocation(location);
		location.setDeleted(false);
	}

	private void prepareUpdateLocation(Location location) {
		if (!StringUtils.hasText(location.getCode())) {
			location.setCode(null);
		}
	}

	@Override
	public Location findLocation(Long id) throws ServiceCoreException {
		try {
			Location location = Location.findLocation(id);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Location with id %s%sfound", id, location == null ? " was not " : ""));
			}
			return location;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding location with %s", id), e);
		}
	}

	@Override
	public List<Location> listLocations() throws ServiceCoreException {
		try {
			List<Location> locations = Location.findAllLocations();
			if (logger.isDebugEnabled()) {
				logger.debug("Locations found:" + LogUtils.formatCollection(locations));
			}
			return locations;
		} catch (Exception e) {
			throw new ServiceCoreException("Error listing locations", e);
		}
	}

	@Override
	public List<Location> listLocations(int firstResult, int maxResults) throws ServiceCoreException {
		try {
			List<Location> locations = Location.findLocationEntries(firstResult, maxResults);
			if (logger.isDebugEnabled()) {
				logger.debug("Locations found:" + LogUtils.formatCollection(locations));
			}
			return locations;
		} catch (Exception e) {
			throw new ServiceCoreException("Error listing locations", e);
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void updateLocation(Location location) throws ServiceCoreException {
		if (location == null) {
			throw new ServiceCoreException("Location is null, cannot update it");
		}
		try {
			prepareUpdateLocation(location);
			location.merge();
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error updating location %s", location), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Location updated: %s", location));
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void deleteLocation(Location location) throws ServiceCoreException {
		if (location == null || location.getId() == null) {
			throw new ServiceCoreException("Location or location id is null, cannot delete it");
		}
		try {
			location.setParent(null);
			location.remove();
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error deleting location: %s", location), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Location: %s%sdeleted", location, location == null ? " was not " : ""));
		}
	}
	
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
	
	@Override
	public long countLocations() throws ServiceCoreException {
		try {
			long count = Location.countLocations();
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Found %s locations", count));
			}
			return count;
		} catch (Exception e) {
			throw new ServiceCoreException("Error counting locations", e);
		}
	}
	
	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void createNationality(Nationality nationality) throws ServiceCoreException {
		if (nationality == null) {
			throw new ServiceCoreException("Nationality is null, cannot create it");
		}
		try {
			nationality.persist();
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error persisting nationality %s", nationality), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Nationality persisted with id %s", nationality.getId()));
		}
	}

	@Override
	public Nationality findNationality(Long id) throws ServiceCoreException {
		try {
			Nationality nationality = Nationality.findNationality(id);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Nationality with id %s%sfound", nationality.getId(), nationality == null ? " was not " : ""));
			}
			return nationality;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding nationality with id %s", id), e);
		}
	}

	@Override
	public List<Nationality> listNationalities() throws ServiceCoreException {
		try {
			List<Nationality> nationalities = Nationality.findAllNationalitys();
			if (logger.isDebugEnabled()) {
				logger.debug("Nationalities found:" + LogUtils.formatCollection(nationalities, "\n"));
			}
			return nationalities;
		} catch (Exception e) {
			throw new ServiceCoreException("Error listing nationalities", e);
		}
	}

	@Override
	public List<Nationality> listNationalities(int firstResult, int maxResults) throws ServiceCoreException {
		try {
			List<Nationality> nationalities = Nationality.findNationalityEntries(firstResult, maxResults);
			if (logger.isDebugEnabled()) {
				logger.debug("Nationalities found:" + LogUtils.formatCollection(nationalities, "\n"));
			}
			return nationalities;
		} catch (Exception e) {
			throw new ServiceCoreException("Error listing nationalities", e);
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void updateNationality(Nationality nationality) throws ServiceCoreException {
		if (nationality == null) {
			throw new ServiceCoreException("Nationality is null, cannot update it");
		}
		try {
			nationality.merge();
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error updading nationality %s", nationality), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Nationality updated: %s", nationality));
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void deleteNationality(Long id) throws ServiceCoreException {
		if (id == null) {
			throw new ServiceCoreException("Nationality id is null, cannot delete it");
		}
		Nationality nationality = findNationality(id);
		if (nationality != null) {
			try {
				nationality.remove();
			} catch (Exception e) {
				throw new ServiceCoreException(String.format("Error delteing nationality with id %s", id), e);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Nationality with id %s%sdeleted", id, nationality == null ? " was not " : ""));
		}
	}

	@Override
	public List<Nationality> findNationalitiesByName(String name) throws ServiceCoreException {
		if (!StringUtils.hasText(name)) {
			logger.warn("Nationality name is empty, cannot find nationalities");
			return null;
		}
		try {
			List<Nationality> nationalities = Nationality.findNationalitysByName(name).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("Nationalities found:" + LogUtils.formatCollection(nationalities, "\n"));
			}
			return nationalities;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding nationalities with name %s", name), e);
		}
	}

	@Override
	public long countNationalities() throws ServiceCoreException {
		try {
			long count = Nationality.countNationalitys();
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Found %s nationalities", count));
			}
			return count;
		} catch (Exception e) {
			throw new ServiceCoreException("Error counting nationalities", e);
		}
	}
}
