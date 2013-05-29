package com.bytepoxic.core.web.jsf;

import com.bytepoxic.core.model.UserTrack;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = UserTrack.class, beanName = "userTrackBean")
public class UserTrackBean {
}
