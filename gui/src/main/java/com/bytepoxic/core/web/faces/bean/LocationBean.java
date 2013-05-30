package com.bytepoxic.core.web.faces.bean;

import com.bytepoxic.core.model.Location;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Location.class, beanName = "locationBean")
public class LocationBean {
}
