// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web.jsf.converter;

import com.bytepoxic.core.web.jsf.converter.FooConverter;
import com.bytepoxic.example.model.Foo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

privileged aspect FooConverter_Roo_Converter {
    
    declare parents: FooConverter implements Converter;
    
    declare @type: FooConverter: @FacesConverter("com.bytepoxic.core.web.jsf.converter.FooConverter");
    
    public Object FooConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return Foo.findFoo(id);
    }
    
    public String FooConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Foo ? ((Foo) value).getId().toString() : "";
    }
    
}
