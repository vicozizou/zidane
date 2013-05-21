package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum IdentificationType {
	MALE("MALE", "gender.male"), FEMALE("FEMALE", "gender.female");

    @NotNull
    private String type;

    @Size(max = 128)
    private String labelKey;
    
    private IdentificationType(String type, String labelKey) {
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
