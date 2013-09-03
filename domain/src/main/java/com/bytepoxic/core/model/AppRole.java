package com.bytepoxic.core.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.GrantedAuthority;

@RooJavaBean
@RooToString
@RooJpaEntity
public class AppRole extends I18NableEntity implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@NotNull
    @Column(unique = true)
    @Size(max = 32)
    private String name;

    @Size(max = 128)
    private String description;
    
    public String getAuthority() {
		return getName();
	}

    public void setName(String name) {
		this.name = name.toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AppRole)) {
			return false;
		}
		AppRole other = (AppRole) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (getId() != other.getId()) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
