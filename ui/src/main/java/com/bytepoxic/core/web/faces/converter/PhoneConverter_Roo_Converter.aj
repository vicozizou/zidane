// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web.faces.converter;

import com.bytepoxic.core.dao.PhoneDAO;
import com.bytepoxic.core.model.Phone;
import com.bytepoxic.core.web.faces.converter.PhoneConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect PhoneConverter_Roo_Converter {
    
    declare parents: PhoneConverter implements Converter;
    
    declare @type: PhoneConverter: @FacesConverter("com.bytepoxic.core.web.faces.converter.PhoneConverter");
    
    @Autowired
    PhoneDAO PhoneConverter.phoneDAO;
    
    public Object PhoneConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return phoneDAO.findOne(id);
    }
    
    public String PhoneConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Phone ? ((Phone) value).getId().toString() : "";
    }
    
}