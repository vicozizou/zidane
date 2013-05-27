// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web.jsf.converter;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.service.LocationService;
import com.bytepoxic.core.web.jsf.converter.LocationConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect LocationConverter_Roo_Converter {
    
    declare parents: LocationConverter implements Converter;
    
    declare @type: LocationConverter: @FacesConverter("com.bytepoxic.core.web.jsf.converter.LocationConverter");
    
    @Autowired
    LocationService LocationConverter.locationService;
    
    public Object LocationConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return locationService.findLocation(id);
    }
    
    public String LocationConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Location ? ((Location) value).getId().toString() : "";
    }
    
}
