package com.bytepoxic.core.backdoor.bean;

import org.springframework.beans.factory.annotation.Autowired;

import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.service.UserService;
import com.bytepoxic.core.throwing.ServiceCoreException;

public class AppRoleBackDoorBean extends AbstractBackDoor {
	@Autowired
	private UserService userService;

	public void processLine(String[] values) throws ServiceCoreException {
		if (canProcess(values)) {
			AppRole appRole = new AppRole();
			parseValues(values, appRole);

			try {
				userService.createAppRole(appRole);

				if (logger.isDebugEnabled()) {
					logger.debug(String.format("AppRole persisted: %s", appRole.toString()));
				}
			} catch (Exception e) {
				throw new ServiceCoreException("Error persisting app role", e);
			}
		}
	}

	public int getFieldsCount() {
		return 2;
	}

	@Override
	public void handleComment(String line) {
	}

	@Override
	public void parseValues(String[] values, Object target) {
		AppRole appRole = (AppRole) target;
		appRole.setDescription(values[0]);
		appRole.setName(values[1]);

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("AppRole parsed: %s", appRole.toString()));
		}
	}
}
