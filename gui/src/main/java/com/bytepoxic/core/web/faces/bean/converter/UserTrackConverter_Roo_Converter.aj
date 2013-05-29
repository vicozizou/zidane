// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web.faces.bean.converter;

import com.bytepoxic.core.model.UserTrack;
import com.bytepoxic.core.service.UserService;
import com.bytepoxic.core.web.faces.bean.converter.UserTrackConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect UserTrackConverter_Roo_Converter {
    
    declare parents: UserTrackConverter implements Converter;
    
    declare @type: UserTrackConverter: @FacesConverter("com.bytepoxic.core.web.faces.bean.converter.UserTrackConverter");
    
    @Autowired
    UserService UserTrackConverter.userService;
    
    public Object UserTrackConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return userService.findUserTrack(id);
    }
    
    public String UserTrackConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof UserTrack ? ((UserTrack) value).getId().toString() : "";
    }
    
}
