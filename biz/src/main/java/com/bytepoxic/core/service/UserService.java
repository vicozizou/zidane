package com.bytepoxic.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.TrackingType;
import com.bytepoxic.core.model.UserTrack;
import com.bytepoxic.core.throwing.ServiceCoreException;

@RooService(domainTypes = { com.bytepoxic.core.model.AppUser.class, com.bytepoxic.core.model.AppRole.class,
		com.bytepoxic.core.model.UserTrack.class })
public interface UserService {
	void deleteAppRoles(List<AppRole> roles) throws ServiceCoreException;
	List<AppRole> findAppRolesByName(String name) throws ServiceCoreException;
	AppRole findAppRoleByName(String name) throws ServiceCoreException;
	
	AppUser findAppUserByUsername(String username) throws ServiceCoreException;
	
	List<UserTrack> findUserTracksByUser(AppUser appUser) throws ServiceCoreException;
	List<UserTrack> findUserTracksByUserAndDates(AppUser appUser, Date fromDate, Date toDate) throws ServiceCoreException;
	List<UserTrack> findUserTracksByDates(Date fromDate, Date toDate) throws ServiceCoreException;
	
	void trackUser(AppUser appUser, TrackingType type) throws ServiceCoreException;
	void trackUserSignin(AppUser appUser) throws ServiceCoreException;
	void trackUserSignout(AppUser appUser) throws ServiceCoreException;
}
