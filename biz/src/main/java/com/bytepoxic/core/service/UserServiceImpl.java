package com.bytepoxic.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.TrackingType;
import com.bytepoxic.core.model.UserTrack;
import com.bytepoxic.core.throwing.ServiceCoreException;
import com.bytepoxic.core.util.LogUtils;

public class UserServiceImpl implements UserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void deleteAppRoles(List<AppRole> roles) throws ServiceCoreException {
		if (roles == null) {
			throw new ServiceCoreException("AppRole ids are null, cannot delete them");
		}
		for (AppRole role : roles) {
			deleteAppRole(role);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<AppRole> findAppRolesByName(String name) throws ServiceCoreException {
		if (!StringUtils.hasText(name)) {
			logger.warn("AppRole name is empty, cannot find appRoles");
			return null;
		}
		try {
			List<AppRole> appRoles = appRoleDAO.findAppRolesByName(name);
			if (logger.isDebugEnabled()) {
				logger.debug("AppRoles found:" + LogUtils.formatCollection(appRoles, "\n"));
			}
			if (null == appRoles) {
				appRoles = new ArrayList<AppRole>(0);
			}
			return appRoles;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding appRoles with name %s", name), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public AppRole findAppRoleByName(String name) throws ServiceCoreException {
		List<AppRole> appRoles = findAppRolesByName(name);
		if (!appRoles.isEmpty()) {
			return appRoles.get(0);
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly = true)
	public AppUser findAppUserByUsername(final String username) throws ServiceCoreException {
		if (!StringUtils.hasText(username)) {
			logger.warn("AppUser name is empty, cannot find appUser");
			return null;
		}
		try {
			List<AppUser> appUsers = appUserDAO.findAppUserByUsername(username);
			if (null == appUsers) {
				appUsers = new ArrayList<AppUser>(0);
			}
			AppUser appUser = appUsers.isEmpty() ? null : appUsers.get(0);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("AppUser found: %s", appUser != null ? appUser : "none"));
			}
			return appUser;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding appUser with name %s", username), e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<UserTrack> findUserTracksByUser(AppUser appUser) throws ServiceCoreException {
		if (appUser == null) {
			logger.warn("AppUser is null, cannot find userTracks");
			return null;
		}
		try {
			List<UserTrack> userTracks = userTrackDAO.findUserTracksByUser(appUser);
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks, "\n"));
			}
			if (null == userTracks) {
				userTracks = new ArrayList<UserTrack>(0);
			}
			return userTracks;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding userTracks with user %s", appUser), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserTrack> findUserTracksByDates(Date fromDate, Date toDate) throws ServiceCoreException {
		if (fromDate == null) {
			logger.warn("Tracking date is null, cannot find userTracks");
			return null;
		}
		try {
			List<UserTrack> userTracks = userTrackDAO.findUserTracksByDates(fromDate, toDate);
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks, "\n"));
			}
			if (null == userTracks) {
				userTracks = new ArrayList<UserTrack>(0);
			}
			return userTracks;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding userTracks with form date %s and toDate %s", fromDate, toDate), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserTrack> findUserTracksByUserAndDates(AppUser appUser, Date fromDate, Date toDate) throws ServiceCoreException {
		if (appUser == null) {
			logger.warn("AppUser is null, cannot find userTracks");
			return null;
		}
		if (fromDate == null) {
			logger.warn("Tracking date is null, cannot find userTracks");
			return null;
		}
		try {
			List<UserTrack> userTracks = userTrackDAO.findUserTracksByUserAndDates(appUser, fromDate, toDate);
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks, "\n"));
			}
			if (null == userTracks) {
				userTracks = new ArrayList<UserTrack>(0);
			}
			return userTracks;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format(
					"Error finding userTracks with user %s, fromDate %s and toDate %s", appUser, fromDate, toDate), e);
		}
	}
	
	@Override
	@Transactional
	public void trackUser(AppUser appUser, TrackingType type) throws ServiceCoreException {
		if (appUser == null) {
			throw new ServiceCoreException("AppUser is null, cannot create it");
		}
		if (type == null) {
			throw new ServiceCoreException("Tracking type is null, cannot create it");
		}
		UserTrack userTrack = new UserTrack();
		try {
			userTrack.setTracked(appUser);
			userTrack.setTrackingType(type);
			userTrack.setTrackingDate(new Date());
			userTrack.persist();
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error persisting userTrack %s", userTrack), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("UserTrack persisted with id %s", userTrack.getId()));
		}
	}

	@Override
	@Transactional
	public void trackUserSignin(AppUser appUser) throws ServiceCoreException {
		trackUser(appUser, TrackingType.USER_SIGNIN);
	}

	@Override
	@Transactional
	public void trackUserSignout(AppUser appUser) throws ServiceCoreException {
		trackUser(appUser, TrackingType.USER_SIGNOUT);
	}
}
