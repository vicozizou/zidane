package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum IdentificationType {
	LOCALE("LOCALE", "identificationType.locale"), RESIDENCE("RESIDENCE", "identificationType.residence"), AMNESTY("AMNESTY", "identificationType.amnesty"),
	PASSPORT("PASSPORT", "identificationType.passport"), OTHER("OTHER", "identificationType.other");

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
