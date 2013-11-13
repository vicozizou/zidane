package com.bytepoxic.core.web.facesbean.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.bytepoxic.core.model.AppUser;
import com.bytepoxic.core.service.UserService;
import com.bytepoxic.core.web.facesbean.form.ClassicFormBean;


public class UserFormBean extends ClassicFormBean {
	@Autowired
	protected UserService userService;
	protected AppUser user;

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	@Override
	protected String getEntityName() {
		return "user";
	}

	@Override
	protected String getSubDomain() {
		return "users";
	}
}
