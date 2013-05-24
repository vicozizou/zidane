package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum TrackingType {
	USER_SIGNIN("USER_SIGNIN", "tracking.user_sigin"), USER_SIGNOUT("USER_SIGNOUT", "tracking.user_sigout");

	@NotNull
	private String type;

	@Size(max = 128)
	private String labelKey;

	private TrackingType(String type, String labelKey) {
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
