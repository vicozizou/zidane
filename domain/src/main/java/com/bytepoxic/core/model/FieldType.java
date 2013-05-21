package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum FieldType {
	TEXT_TYPE("TEXT", "java.lang.String", "field.type.text"), INTEGER_TYPE("INTEGER", "java.lang.Integer", "field.type.integer"),
	LONG_TYPE("LONG", "java.lang.Long", "field.type.long"), FLOAT_TYPE("FLOAT", "java.lang.Float", "field.type.float"), DOUBLE_TYPE("DOUBLE", "java.lang.Double", "field.type.double"),
	BIG_DECIMAL_TYPE("BIG_DECIMAL", "java.math.BigDecimal", "field.type.big_decimal"), BOOLEAN_TYPE("BOOLEAN", "java.lang.Boolean", "field.type.boolean"),
	DATE_TYPE("DATE", "java.util.Date", "field.type.date");

    @NotNull
    private String type;

    @NotNull
    private String javaType;
    
    private FieldType(String type, String javaType, String labelKey) {
		this.type = type;
		this.javaType = javaType;
		this.labelKey = labelKey;
	}

    @Size(max = 128)
    private String labelKey;

	public String getType() {
		return type;
	}

	public String getJavaType() {
		return javaType;
	}

	public String getLabelKey() {
		return labelKey;
	}
}
