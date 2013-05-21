package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum PhoneType {
	HOME_PHONE("HOME_PHONE", "phone.home_phone"), HOME_FAX("HOME_FAX", "phone.home_fax"), WORK_PHONE("WORK_PHONE", "phone.work_phone"), WORK_FAX("WORK_FAX", "phone.work_fax"),
	MOBILE_PHONE("MOBILE_PHONE", "phone.mobile_phone");

    @NotNull
    private String type;

    @Size(max = 128)
    private String labelKey;

	private PhoneType(String type, String labelKey) {
		this.type = type;
		this.labelKey = labelKey;
	}

	public String getType() {
		return type;
	}

	public String getLabelKey() {
		return labelKey;
	}
}
