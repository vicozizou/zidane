package com.bytepoxic.core.web.jsf;

import com.bytepoxic.core.model.Email;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Email.class, beanName = "emailBean")
public class EmailBean {
}
