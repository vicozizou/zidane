package com.bytepoxic.core.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.model.Nationality;
import com.bytepoxic.core.throwing.ServiceCoreException;

@RooService(domainTypes = { com.bytepoxic.core.model.Location.class, com.bytepoxic.core.model.Nationality.class })
public interface LocationService {
	void createLocation(Location location) throws ServiceCoreException;
	Location findLocation(Long id) throws ServiceCoreException;
	List<Location> listLocations() throws ServiceCoreException;
	List<Location> listLocations(int firstResult, int maxResults) throws ServiceCoreException;
	void updateLocation(Location location) throws ServiceCoreException;
	void deleteLocation(Location location) throws ServiceCoreException;
	List<Location> findLocationsByParent(Location parent) throws ServiceCoreException;
	List<Location> findMainLocations() throws ServiceCoreException;
	Location buildLocationTree(Location start) throws ServiceCoreException;
	List<Location> findLocationsByName(String name) throws ServiceCoreException;
	List<Location> findLocationsByCode(String code) throws ServiceCoreException;
	long countLocations() throws ServiceCoreException;
	
	void createNationality(Nationality nationality) throws ServiceCoreException;
	Nationality findNationality(Long id) throws ServiceCoreException;
	List<Nationality> listNationalities() throws ServiceCoreException;
	List<Nationality> listNationalities(int firstResult, int maxResults) throws ServiceCoreException;
	void updateNationality(Nationality nationality) throws ServiceCoreException;
	void deleteNationality(Long id) throws ServiceCoreException;
	List<Nationality> findNationalitiesByName(String name) throws ServiceCoreException;
	long countNationalities() throws ServiceCoreException;
}
