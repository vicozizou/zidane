package com.bytepoxic.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bytepoxic.core.dao.AppRoleDAOCustom;
import com.bytepoxic.core.dao.AppUserDAOCustom;
import com.bytepoxic.core.dao.UserTrackDAOCustom;
import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.UserTrack;
import com.bytepoxic.core.throwing.ServiceCoreException;
import com.bytepoxic.core.util.LogUtils;

public class UserServiceImpl implements UserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private AppRoleDAOCustom appRoleDAOCustom;
	
	@Autowired
	private AppUserDAOCustom appUserDAOCustom;
	
	@Autowired
	private UserTrackDAOCustom userTrackDAOCustom;
	
	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void deleteAppRoles(List<AppRole> roles) throws ServiceCoreException {
		if (roles == null) {
			throw new ServiceCoreException("AppRole ids are null, cannot delete them");
		}
		for (AppRole role : roles) {
			this.deleteAppRole(role);
		}
	}

	@Override
	public List<AppRole> findAppRolesByName(String name) throws ServiceCoreException {
		if (!StringUtils.hasText(name)) {
			logger.warn("AppRole name is empty, cannot find appRoles");
			return null;
		}
		try {
			List<AppRole> appRoles = appRoleDAOCustom.findAppRolesByName(name);
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
	public AppRole findAppRoleByName(String name) throws ServiceCoreException {
		if (!StringUtils.hasText(name)) {
			logger.warn("AppRole name is empty, cannot find appRole");
			return null;
		}
		try {
			AppRole appRole = this.findAppRoleByName(name);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("AppRole found: %s", appRole != null ? appRole : "none"));
			}
			return appRole;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding appRole with name %s", name), e);
		}
	}
	
	@Override
	public AppUser findAppUserByUsername(String username) throws ServiceCoreException {
		if (!StringUtils.hasText(username)) {
			logger.warn("AppUser name is empty, cannot find appUser");
			return null;
		}
		try {
			AppUser appUser = appUserDAOCustom.findAppUserByUsername(username);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("AppUser found: %s", appUser != null ? appUser : "none"));
			}
			return appUser;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding appUser with name %s", username), e);
		}
	}
	
	@Override
	public List<UserTrack> findUserTracksByUser(AppUser appUser) throws ServiceCoreException {
		if (appUser == null) {
			logger.warn("AppUser is null, cannot find userTracks");
			return null;
		}
		try {
			List<UserTrack> userTracks = userTrackDAOCustom.findUserTracksByUser(appUser);
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
	public List<UserTrack> findUserTracksByDate(Date date) throws ServiceCoreException {
		if (date == null) {
			logger.warn("Tracking date is null, cannot find userTracks");
			return null;
		}
		try {
			List<UserTrack> userTracks = userTrackDAOCustom.findUserTracksByDate(date);
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks, "\n"));
			}
			if (null == userTracks) {
				userTracks = new ArrayList<UserTrack>(0);
			}
			return userTracks;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding userTracks with date %s", date), e);
		}
	}

	@Override
	public List<UserTrack> findUserTracksByUserAndDate(AppUser appUser, Date date) throws ServiceCoreException {
		if (appUser == null) {
			logger.warn("AppUser is null, cannot find userTracks");
			return null;
		}
		if (date == null) {
			logger.warn("Tracking date is null, cannot find userTracks");
			return null;
		}
		try {
			List<UserTrack> userTracks = userTrackDAOCustom.findUserTracksByUserAndDate(appUser, date);
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks, "\n"));
			}
			if (null == userTracks) {
				userTracks = new ArrayList<UserTrack>(0);
			}
			return userTracks;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding userTracks with user %s and date %s", appUser, date), e);
		}
	}
}
