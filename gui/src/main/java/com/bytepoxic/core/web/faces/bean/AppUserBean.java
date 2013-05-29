package com.bytepoxic.core.web.faces.bean;

import com.bytepoxic.core.model.AppUser;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = AppUser.class, beanName = "appUserBean")
public class AppUserBean {
}
