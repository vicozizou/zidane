package com.bytepoxic.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.TrackingType;
import com.bytepoxic.core.model.UserTrack;
import com.bytepoxic.core.throwing.ServiceCoreException;

@RooService(domainTypes = { com.bytepoxic.core.model.AppUser.class, com.bytepoxic.core.model.AppRole.class, com.bytepoxic.core.model.UserTrack.class })
public interface UserService {
	List<AppUser> findAppUsersByUsername(String name) throws ServiceCoreException;
	AppUser findAppUserByUsername(String username) throws ServiceCoreException;
	
	void trackUser(AppUser appUser, TrackingType type) throws ServiceCoreException;
	void trackUserSignin(AppUser appUser) throws ServiceCoreException;
	void trackUserSignout(AppUser appUser) throws ServiceCoreException;
	UserTrack findUserTrack(Long id) throws ServiceCoreException;
	List<UserTrack> listUserTracks() throws ServiceCoreException;
	List<UserTrack> listUserTracks(int firstResult, int maxResults) throws ServiceCoreException;
	List<UserTrack> findUserTracksByUser(AppUser appUser) throws ServiceCoreException;
	List<UserTrack> findUserTracksByUserAndDate(AppUser appUser, Date date) throws ServiceCoreException;
	List<UserTrack> findUserTracksByDate(Date date) throws ServiceCoreException;
}
