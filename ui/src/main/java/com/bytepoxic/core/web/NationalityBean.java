package com.bytepoxic.core.web;

import com.bytepoxic.core.model.Nationality;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Nationality.class, beanName = "nationalityBean")
public class NationalityBean {
}
