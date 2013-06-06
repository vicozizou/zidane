package com.bytepoxic.core.service;

import java.io.Serializable;
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

public class UserServiceImpl implements UserService, Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
			
	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void createAppRole(AppRole appRole) throws ServiceCoreException {
		if (appRole == null) {
			throw new ServiceCoreException("AppRole is null, cannot create it");
		}
		try {
			appRole.persist();
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error persisting appRole %s", appRole), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppRole persisted with id %s", appRole.getId()));
		}
	}

	@Override
	public AppRole findAppRole(Long id) throws ServiceCoreException {
		try {
			AppRole appRole = AppRole.findAppRole(id);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("AppRole with id %s%sfound", id, appRole == null ? " was not " : ""));
			}
			return appRole;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding appRole with %s", id), e);
		}
	}

	@Override
	public List<AppRole> listAppRoles() throws ServiceCoreException {
		try {
			List<AppRole> appRoles = AppRole.findAllAppRoles();
			if (logger.isDebugEnabled()) {
				logger.debug("AppRoles found:" + LogUtils.formatCollection(appRoles));
			}
			return appRoles;
		} catch (Exception e) {
			throw new ServiceCoreException("Error listing appRoles", e);
		}
	}

	@Override
	public List<AppRole> listAppRoles(int firstResult, int maxResults) throws ServiceCoreException {
		try {
			List<AppRole> appRoles = AppRole.findAppRoleEntries(firstResult, maxResults);
			if (logger.isDebugEnabled()) {
				logger.debug("AppRoles found:" + LogUtils.formatCollection(appRoles));
			}
			return appRoles;
		} catch (Exception e) {
			throw new ServiceCoreException("Error listing appRoles", e);
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void updateAppRole(AppRole appRole) throws ServiceCoreException {
		if (appRole == null) {
			throw new ServiceCoreException("AppRole is null, cannot update it");
		}
		try {
			appRole.merge();
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error updating appRole %s", appRole), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppRole updated: %s", appRole));
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void deleteAppRole(Long id) throws ServiceCoreException {
		if (id == null) {
			throw new ServiceCoreException("AppRole id is null, cannot delete it");
		}
		AppRole appRole = findAppRole(id);
		if (appRole != null) {
			try {
				appRole.remove();
			} catch (Exception e) {
				throw new ServiceCoreException(String.format("Error updating appRole with id %s", id), e);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppRole with id %s%sdeleted", id, appRole == null ? " was not " : ""));
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void deleteAppRoles(List<AppRole> roles) throws ServiceCoreException {
		if (roles == null) {
			throw new ServiceCoreException("AppRole ids are null, cannot delete them");
		}
		for (AppRole role : roles) {
			deleteAppRole(role.getId());
		}
	}

	@Override
	public List<AppRole> findAppRolesByName(String name) throws ServiceCoreException {
		if (!StringUtils.hasText(name)) {
			logger.warn("AppRole name is empty, cannot find appRoles");
			return null;
		}
		try {
			List<AppRole> appRoles = AppRole.findAppRolesByName(name).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("AppRoles found:" + LogUtils.formatCollection(appRoles, "\n"));
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
			List<AppRole> appRoles = findAppRolesByName(name);
			AppRole appRole = null;
			if (appRoles != null && !appRoles.isEmpty()) {
				appRole = appRoles.get(0);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("AppRole found: %s", appRole != null ? appRole : "none"));
			}
			return appRole;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding appRole with name %s", name), e);
		}
	}

	@Override
	public long countAppRoles() throws ServiceCoreException {
		try {
			long count = AppRole.countAppRoles();
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Found %s appRoles", count));
			}
			return count;
		} catch (Exception e) {
			throw new ServiceCoreException("Error counting appRoles", e);
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public void createAppUser(AppUser appUser) throws ServiceCoreException {
		if (appUser == null) {
			throw new ServiceCoreException("AppUser is null, cannot create it");
		}
		try {
			appUser.persist();
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error persisting appUser %s", appUser), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppUser persisted with id %s", appUser.getId()));
		}
	}

	@Override
	public AppUser findAppUser(Long id) throws ServiceCoreException {
		try {
			AppUser appUser = AppUser.findAppUser(id);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("AppUser with id %s%sfound", id, appUser == null ? " was not " : ""));
			}
			return appUser;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding appUser with %s", id), e);
		}
	}

	@Override
	public List<AppUser> listAppUsers() throws ServiceCoreException {
		try {
			List<AppUser> appUsers = AppUser.findAllAppUsers();
			if (logger.isDebugEnabled()) {
				logger.debug("AppUsers found:" + LogUtils.formatCollection(appUsers));
			}
			return appUsers;
		} catch (Exception e) {
			throw new ServiceCoreException("Error listing appUsers", e);
		}
	}

	@Override
	public List<AppUser> listAppUsers(int firstResult, int maxResults) throws ServiceCoreException {
		try {
			List<AppUser> appUsers = AppUser.findAppUserEntries(firstResult, maxResults);
			if (logger.isDebugEnabled()) {
				logger.debug("AppUsers found:" + LogUtils.formatCollection(appUsers));
			}
			return appUsers;
		} catch (Exception e) {
			throw new ServiceCoreException("Error listing appUsers", e);
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void updateAppUser(AppUser appUser) throws ServiceCoreException {
		if (appUser == null) {
			throw new ServiceCoreException("AppUser is null, cannot update it");
		}
		try {
			appUser.merge();
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error updating appUser %s", appUser), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppUser updated: %s", appUser));
		}
	}

	@Override
	@Transactional
	@Secured({"ROLE_ADMIN"})
	public void deleteAppUser(Long id) throws ServiceCoreException {
		if (id == null) {
			throw new ServiceCoreException("AppUser id is null, cannot delete it");
		}
		AppUser appUser = findAppUser(id);
		if (appUser != null) {
			try {
				appUser.remove();
			} catch (Exception e) {
				throw new ServiceCoreException(String.format("Error updating appUser with id %s", id), e);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppUser with id %s%sdeleted", id, appUser == null ? " was not " : ""));
		}
	}

	@Override
	public List<AppUser> findAppUsersByUsername(String username) throws ServiceCoreException {
		if (!StringUtils.hasText(username)) {
			logger.warn("AppUser username is empty, cannot find appUsers");
			return null;
		}
		try {
			List<AppUser> appUsers = AppUser.findAppUsersByUsername(username).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("AppUsers found:" + LogUtils.formatCollection(appUsers, "\n"));
			}
			return appUsers;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding appUsers with username %s", username), e);
		}
	}

	@Override
	public AppUser findAppUserByUsername(String username) throws ServiceCoreException {
		if (!StringUtils.hasText(username)) {
			logger.warn("AppUser name is empty, cannot find appUser");
			return null;
		}
		try {
			List<AppUser> appUsers = findAppUsersByUsername(username);
			AppUser appUser = null;
			if (appUsers != null && !appUsers.isEmpty()) {
				appUser = appUsers.get(0);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("AppUser found: %s", appUser != null ? appUser : "none"));
			}
			return appUser;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding appUser with name %s", username), e);
		}
	}
	
	@Override
	public long countAppUsers() throws ServiceCoreException {
		try {
			long count = AppUser.countAppUsers();
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Found %s appUsers", count));
			}
			return count;
		} catch (Exception e) {
			throw new ServiceCoreException("Error counting appUsers", e);
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

	@Override
	public UserTrack findUserTrack(Long id) throws ServiceCoreException {
		try {
			UserTrack userTrack = UserTrack.findUserTrack(id);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("UserTrack with id %s%sfound", id, userTrack == null ? " was not " : ""));
			}
			return userTrack;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding userTrack with %s", id), e);
		}
	}

	@Override
	public List<UserTrack> listUserTracks() throws ServiceCoreException {
		try {
			List<UserTrack> userTracks = UserTrack.findAllUserTracks();
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks));
			}
			return userTracks;
		} catch (Exception e) {
			throw new ServiceCoreException("Error listing userTracks", e);
		}
	}

	@Override
	public List<UserTrack> listUserTracks(int firstResult, int maxResults) throws ServiceCoreException {
		try {
			List<UserTrack> userTracks = UserTrack.findUserTrackEntries(firstResult, maxResults);
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks));
			}
			return userTracks;
		} catch (Exception e) {
			throw new ServiceCoreException("Error listing userTracks", e);
		}
	}

	@Override
	public List<UserTrack> findUserTracksByUser(AppUser appUser) throws ServiceCoreException {
		if (appUser == null) {
			logger.warn("AppUser is null, cannot find userTracks");
			return null;
		}
		try {
			List<UserTrack> userTracks = UserTrack.findUserTracksByTracked(appUser).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks, "\n"));
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
			List<UserTrack> userTracks = UserTrack.findUserTracksByTrackingDate(date).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks, "\n"));
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
			List<UserTrack> userTracks = UserTrack.findUserTracksByTrackedAndTrackingDate(appUser, date).getResultList();
			if (logger.isDebugEnabled()) {
				logger.debug("UserTracks found:" + LogUtils.formatCollection(userTracks, "\n"));
			}
			return userTracks;
		} catch (Exception e) {
			throw new ServiceCoreException(String.format("Error finding userTracks with user %s and date %s", appUser, date), e);
		}
	}
}
