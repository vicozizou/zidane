package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum EmailType {
	HOME_EMAIL("HOME_EMAIL", "email.home"), WORK_EMAIL("WORK_EMAIL", "email.work");

    @NotNull
    private String type;

    @Size(max = 128)
    private String labelKey;

	private EmailType(String type, String labelKey) {
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
