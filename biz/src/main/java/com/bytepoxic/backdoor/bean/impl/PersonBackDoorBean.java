package com.bytepoxic.backdoor.bean.impl;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.bytepoxic.backdoor.bean.AbstractBackDoor;
import com.bytepoxic.backdoor.throwing.BackDoorException;
import com.bytepoxic.core.model.Gender;
import com.bytepoxic.core.model.IdentificationType;
import com.bytepoxic.core.model.Person;
import com.bytepoxic.core.service.LocationService;

public class PersonBackDoorBean extends AbstractBackDoor {
	@Autowired
	private LocationService locationService;
	
	@Override
	public void parseValues(String[] values, Object target) {
		// birthday, names, gender, identification, identification_type,
		// surnames, home_place, nationality, work_place
		Person person = (Person) target;
		int i = 0;

		try {
			person.setBirthday(DATE_FORMAT.parse(values[i++]));
		} catch (ParseException e) {
			logger.error(String.format("Wrong birthdate %s, ignoring", values[i - 1]), e);
			return;
		}

		person.setNames(values[i++]);
		person.setGender(Gender.MALE.getGender().equalsIgnoreCase(values[i++]) ? Gender.MALE : Gender.FEMALE);
		person.setIdentification(values[i++]);
		person.setIdentificationType(resolveIdType(values[i++]));
		person.setSurnames(values[i++]);
		person.setDeleted(Boolean.parseBoolean(values[i++]));
		Long value = convertId(values[i++]);
		if (null != value) {
			person.setHomePlace(locationService.findPlace(value));
		}
		value = convertId(values[i++]);
		if (null != value) {
			person.setNationality(locationService.findNationality(value));
		}
		value = convertId(values[i++]);
		if (null != value) {
			person.setWorkPlace(locationService.findPlace(value));
		}
		person.setDeleted(false);
		person.setCreationDate(new Date());
	}

	private IdentificationType resolveIdType(String value) {
		if (IdentificationType.LOCALE.getType().equalsIgnoreCase(value)) {
			return IdentificationType.LOCALE;
		} else if (IdentificationType.RESIDENCE.getType().equalsIgnoreCase(value)) {
			return IdentificationType.RESIDENCE;
		} else if (IdentificationType.AMNESTY.getType().equalsIgnoreCase(value)) {
			return IdentificationType.AMNESTY;
		} else if (IdentificationType.PASSPORT.getType().equalsIgnoreCase(value)) {
			return IdentificationType.PASSPORT;
		} else {
			return IdentificationType.OTHER;
		}
	}

	@Override
	public int getFieldsCount() {
		return 10;
	}

	@Override
	public void processLine(String[] values) throws BackDoorException {
	}

	@Override
	public void handleComment(String line) {
	}
}
