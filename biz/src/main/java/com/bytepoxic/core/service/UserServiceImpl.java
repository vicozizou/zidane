package com.bytepoxic.core.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.model.TrackingType;
import com.bytepoxic.core.model.UserTrack;
import com.bytepoxic.core.throwing.ServiceCoreException;
import com.bytepoxic.core.util.LogUtils;

public class UserServiceImpl implements UserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());
			
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
