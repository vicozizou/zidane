package com.bytepoxic.core.backdoor.bean;

import java.io.File;

import com.bytepoxic.core.throwing.ServiceCoreException;

public interface BackDoorBean {
	String DELIMITER_CHAR = ",";
	String BLANK_SPACE_CHAR = " ";
	String COMMENT_CHAR = "#";

	void runBackDoor(File dataLocation);

	void parseValues(String[] values, Object target);

	void processLine(String[] values) throws ServiceCoreException;

	int getFieldsCount();

	String getDelimiter();
}
