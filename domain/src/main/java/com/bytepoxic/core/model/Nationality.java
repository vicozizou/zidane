package com.bytepoxic.core.model;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Nationality extends I18NableEntity {
    @NotNull
    @Column(unique = true)
    @Size(max = 64)
    private String name;

    @NotNull
    @ManyToOne
    private Location country;
}
