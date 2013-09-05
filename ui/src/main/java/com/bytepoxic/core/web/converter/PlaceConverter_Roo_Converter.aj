// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web.converter;

import com.bytepoxic.core.model.Place;
import com.bytepoxic.core.service.LocationService;
import com.bytepoxic.core.web.converter.PlaceConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect PlaceConverter_Roo_Converter {
    
    declare parents: PlaceConverter implements Converter;
    
    declare @type: PlaceConverter: @FacesConverter("com.bytepoxic.core.web.converter.PlaceConverter");
    
    @Autowired
    LocationService PlaceConverter.locationService;
    
    public Object PlaceConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return locationService.findPlace(id);
    }
    
    public String PlaceConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Place ? ((Place) value).getId().toString() : "";
    }
    
}
