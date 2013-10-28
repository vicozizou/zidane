package com.bytepoxic.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity(inheritanceType = "JOINED")
public abstract class Person implements SoftDeleteable, DateTrackable {
    @NotNull
    @Size(max = 128)
    private String names;

    @NotNull
    @Size(max = 128)
    private String surnames;

    @NotNull
    @Enumerated
    private Gender gender;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date birthday;

    @NotNull
    @Column(unique = true)
    @Size(max = 64)
    private String identification;

    @NotNull
    @Enumerated
    private IdentificationType identificationType;

    @ManyToOne
    private Nationality nationality;

    @ManyToOne
    private Place homePlace;

    @ManyToOne
    private Place workPlace;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Phone> phones = new HashSet<Phone>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Email> emails = new HashSet<Email>();
    
    @NotNull
	private boolean deleted;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date creationDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updateDate;
}
