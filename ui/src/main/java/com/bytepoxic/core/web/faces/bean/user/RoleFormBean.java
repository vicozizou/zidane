package com.bytepoxic.core.web.facesbean.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.service.UserService;
import com.bytepoxic.core.web.facesbean.form.ClassicFormBean;


public class RoleFormBean extends ClassicFormBean {
	@Autowired
	protected UserService userService;
	protected AppRole role;

	public AppRole getRole() {
		return role;
	}

	public void setRole(AppRole role) {
		this.role = role;
	}

	@Override
	protected String getEntityName() {
		return "role";
	}

	@Override
	protected String getSubDomain() {
		return "users";
	}
}
