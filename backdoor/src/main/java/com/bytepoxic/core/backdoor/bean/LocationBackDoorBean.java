package com.bytepoxic.core.backdoor.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.model.Nationality;
import com.bytepoxic.core.service.LocationService;
import com.bytepoxic.core.throwing.ServiceCoreException;

public class LocationBackDoorBean extends AbstractBackDoor {
	@Autowired
	private LocationService locationService;

	@Override
	public void parseValues(String[] values, Object target) {
		// id, app_current, code, label_key, "name", parent
		Location location = (Location) target;
		int i = 0;

		try {
			location.setId(Long.valueOf(values[i++]));
		} catch (NumberFormatException e) {
			logger.warn(String.format("Location id is not numeric value", values[i - 1]));
		}

		location.setAppCurrent(Boolean.parseBoolean(values[i++]));
		location.setCode(values[i++]);
		location.setLabelKey(values[i++]);
		location.setName(escapeName(values[i++]));
		try {
			location.setParent(locateParent(values[i++]));
		} catch (ServiceCoreException e) {
			logger.error("Error setting the parent location", e);
		}
		location.setDeleted(Boolean.parseBoolean(values[i++]));

		if (location.hasParent()) {
			labelize(location);
		}

		try {
			location.setLatitude(Double.valueOf(values[i++]));
		} catch (Exception e) {
			logger.warn(String.format("Coord x is not numeric value", values[i - 1]));
		}
		try {
			location.setLongitude(Double.valueOf(values[i++]));
		} catch (Exception e) {
			logger.warn(String.format("Coord y is not numeric value", values[i - 1]));
		}
	}

	public String getDelimiter() {
		return ";";
	}

	private String escapeName(String name) {
		return name.replaceAll("\"", "");
	}

	private void labelize(Location location) {
		if (!StringUtils.hasText(location.getLabelKey()) && StringUtils.hasText(location.getName())) {
			String value = location.getName().toLowerCase().replaceAll("'", "");
			value = "location.name." + value.replaceAll(" ", "_");
			location.setLabelKey(value);
		}
	}

	private Location locateParent(String parentId) throws ServiceCoreException {
		Long pid = null;

		if (StringUtils.hasText(parentId)) {
			try {
				pid = Long.valueOf(parentId);
			} catch (NumberFormatException e) {
				logger.warn("Parent location id is not a numeric value, ignoring it");
			}

			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Parent location found: %s", pid != null ? pid : "none"));
			}
		}

		if (pid != null) {
			try {
				return locationService.findLocation(pid);
			} catch (ServiceCoreException e) {
				throw new ServiceCoreException("Error finding parent location", e);
			}
		}

		return null;
	}

	@Override
	public int getFieldsCount() {
		return 9;
	}

	@Override
	public void processLine(String[] values) throws ServiceCoreException {
		if (canProcess(values)) {
			Location location = new Location();
			parseValues(values, location);

			try {
				locationService.createLocation(location);

				if (!location.hasParent()) {
					Nationality nat = new Nationality();
					nat.setCountry(location);
					nat.setLabelKey(location.getLabelKey());
					nat.setName(location.getName());
					locationService.createNationality(nat);
				}

				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Location persisted: %s", location.toString()));
				}
			} catch (Exception e) {
				throw new ServiceCoreException("Error persisting location", e);
			}
		}
	}

	@Override
	public void handleComment(String line) {
	}

}
