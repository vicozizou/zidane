package com.bytepoxic.core.web.faces.bean;

import com.bytepoxic.core.model.Person;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Person.class, beanName = "personBean")
public class PersonBean {
}
