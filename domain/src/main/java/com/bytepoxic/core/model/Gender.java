package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum Gender {
	MALE("MALE", "gender.male"), FEMALE("FEMALE", "gender.female");

    @NotNull
    private String gender;

    @Size(max = 128)
    private String labelKey;
    
    private Gender(String gender, String labelKey) {
		this.gender = gender;
		this.labelKey = labelKey;
	}

	public String getGender() {
		return gender;
	}

	public String getLabelKey() {
		return labelKey;
	}
}
