package com.bytepoxic.backdoor.bean.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.bytepoxic.backdoor.bean.AbstractBackDoor;
import com.bytepoxic.backdoor.throwing.BackDoorException;
import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.service.UserService;

public class AppUserRoleBean extends AbstractBackDoor {
	@Autowired
	private UserService userService;
	
	@Override
	public int getFieldsCount() {
		return 1;
	}

	@Override
	public void parseValues(String[] values, Object target) {
		AppUser user = (AppUser) target;
		Set<AppRole> roles = new HashSet<AppRole>();

		for (String value : values) {
			if (StringUtils.hasText(value)) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Found role %s", value));
				}

				try {
					AppRole role = userService.findAppRoleByName(value);

					if (role != null) {
						roles.add(role);
					}

					if (logger.isDebugEnabled()) {
						logger.debug(String.format("Added role %s", value));
					}
				} catch (Exception e) {
					logger.warn(String.format("Role %s could not be found, ignoring it", value));
					continue;
				}
			} else {
				logger.warn(String.format("Empty role found in %s", values.toString()));
			}
		}
		
		user.setRoles(roles);
	}

	@Override
	public void processLine(String[] values) throws BackDoorException {
	}

	@Override
	protected void handleComment(String line) {
	}
	
	@Override
	public Object instanceTarget() {
		return new AppUser();
	}
}
