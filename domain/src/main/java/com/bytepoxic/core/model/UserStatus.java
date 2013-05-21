package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum UserStatus {
	ACTIVE("ACTIVE", "user_status.active"), BLOCKED("BLOCKED", "user_status.blocked"), EXPIRED("EXPIRED", "user_status.expired");

	@NotNull
	private String status;

	@Size(max = 128)
	private String labelKey;

	private UserStatus(String userStatus, String labelKey) {
		this.status = userStatus;
		this.labelKey = labelKey;
	}

	public String getStatus() {
		return status;
	}

	public String getLabelKey() {
		return labelKey;
	}
}
