package com.bytepoxic.core.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.service.UserService;
import com.bytepoxic.core.throwing.ServiceCoreException;

@Service(value = "userSecurityService")
public class UserSecurityServiceImpl implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(UserSecurityServiceImpl.class);
	
	private static final long DEFAULT_ID = 123456789L;
	
	private String memUsername;
	private String memUserPasswd;

	@Autowired
	UserService userService;
	
	public void setMemUsername(String memUsername) {
		this.memUsername = memUsername;
	}

	public void setMemUserPasswd(String memUserPasswd) {
		this.memUserPasswd = memUserPasswd;
	}
	
	private boolean hasMemUser() {
		return !StringUtils.hasText(memUsername) && !StringUtils.hasText(memUserPasswd);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Attempting to load user with username %s", username));
		}
		
		UserDetails userDetails = null;
		try {
			userDetails = userService.findAppUserByUsername(username);
		} catch (ServiceCoreException e) {
			if (!hasMemUser()) {
				throw new UsernameNotFoundException("Could not retrieve user, ", e);
			} else {
				logger.error("Could not retrieve user, trying with mem user", e);
				
				if (username.equals(memUsername)) {
					AppUser user = new AppUser();
					user.setId(DEFAULT_ID);
					user.setUsername(username);
					return user;
				}
			}
		}

		if (userDetails == null) {
			throw new UsernameNotFoundException(String.format("User %s not found.", username));
		}

		return userDetails;
	}
}
