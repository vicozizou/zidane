package com.bytepoxic.core.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findAppUsersByUsername" })
public class AppUser extends Person implements UserDetails {
	private static final long serialVersionUID = 1L;

	@NotNull
    @Column(unique = true)
    @Size(max = 32)
    private String username;

    @NotNull
    @Size(max = 64)
    private String password;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<AppRole> roles = new HashSet<AppRole>();

    @Enumerated
    private UserStatus userStatus;

    private Integer loginAttempts;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lastLoginDate;

    private Integer daysToExpire;
    
    private transient Set<GrantedAuthority> authorities;

	public Collection<GrantedAuthority> getAuthorities() {
		if (authorities == null) {
			authorities = new HashSet<GrantedAuthority>(roles.size());
			for (AppRole role : roles) {
				authorities.add(role);
			}
		}
		return authorities;
	}

	public boolean isAccountNonExpired() {
		return !userStatus.equals(UserStatus.EXPIRED);
	}

	public boolean isAccountNonLocked() {
		return !userStatus.equals(UserStatus.BLOCKED);
	}

	public boolean isCredentialsNonExpired() {
		return !userStatus.equals(UserStatus.EXPIRED);
	}

	public boolean isEnabled() {
		return userStatus.equals(UserStatus.ACTIVE) && !getDeleted();
	}

	public String getStatus() {
		return userStatus.getStatus();
	}

	public String getStatusLabel() {
		return userStatus.getLabelKey();
	}
}
