package com.bytepoxic.core.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bytepoxic.core.service.UserService;
import com.bytepoxic.core.throwing.ServiceCoreException;

@Service(value = "userSecurityService")
public class UserSecurityServiceImpl implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(UserDetailsService.class.getName());

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Attempting to load user with username %s", username));
		}
		
		UserDetails userDetails = null;
		try {
			userDetails = userService.findAppUserByUsername(username);
		} catch (ServiceCoreException e) {
			throw new UsernameNotFoundException("Could not retrieve user", e);
		}

		if (userDetails == null) {
			throw new UsernameNotFoundException(String.format("User %s not found.", username));
		}

		return userDetails;
	}
}
