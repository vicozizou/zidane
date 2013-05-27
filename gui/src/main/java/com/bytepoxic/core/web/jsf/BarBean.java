package com.bytepoxic.core.web.jsf;

import com.bytepoxic.example.model.Bar;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Bar.class, beanName = "barBean")
public class BarBean {
}
