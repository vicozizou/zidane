package com.bytepoxic.core.web.jsf;

import com.bytepoxic.example.model.Foo;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Foo.class, beanName = "fooBean")
public class FooBean {
}
