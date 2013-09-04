// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web.converter;

import com.bytepoxic.core.dao.NationalityDAO;
import com.bytepoxic.core.model.Nationality;
import com.bytepoxic.core.web.converter.NationalityConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect NationalityConverter_Roo_Converter {
    
    declare parents: NationalityConverter implements Converter;
    
    declare @type: NationalityConverter: @FacesConverter("com.bytepoxic.core.web.converter.NationalityConverter");
    
    @Autowired
    NationalityDAO NationalityConverter.nationalityDAO;
    
    public Object NationalityConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return nationalityDAO.findOne(id);
    }
    
    public String NationalityConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Nationality ? ((Nationality) value).getId().toString() : "";
    }
    
}