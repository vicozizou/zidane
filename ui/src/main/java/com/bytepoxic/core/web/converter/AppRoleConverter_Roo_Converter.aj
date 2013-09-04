// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web.converter;

import com.bytepoxic.core.model.AppRole;
import com.bytepoxic.core.service.UserService;
import com.bytepoxic.core.web.converter.AppRoleConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect AppRoleConverter_Roo_Converter {
    
    declare parents: AppRoleConverter implements Converter;
    
    declare @type: AppRoleConverter: @FacesConverter("com.bytepoxic.core.web.converter.AppRoleConverter");
    
    @Autowired
    UserService AppRoleConverter.userService;
    
    public Object AppRoleConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return userService.findAppRole(id);
    }
    
    public String AppRoleConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof AppRole ? ((AppRole) value).getId().toString() : "";
    }
    
}
