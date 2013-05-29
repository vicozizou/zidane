package com.bytepoxic.core.web.jsf;

import com.bytepoxic.core.model.Place;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Place.class, beanName = "placeBean")
public class PlaceBean {
}
