package com.bytepoxic.core.web.faces.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class LocaleBean {
    private Locale locale;
    private List<Locale> supportedLocales;

    @PostConstruct
    public void init() {
        locale = FacesUtils.getExternal().getRequestLocale();
        supportedLocales = getLocales();
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public List<Locale> getSupportedLocales() {
		return supportedLocales;
	}

	private List<Locale> getLocales() {
        List<Locale> items = new ArrayList<Locale>();
        Iterator<Locale> localeIt = FacesUtils.getApplication().getSupportedLocales();
        while (localeIt.hasNext()) {
            Locale locale = localeIt.next();
            items.add(locale);
        }
        return items;
    }
    
    public List<SelectItem> getLocaleItems() {
        List<SelectItem> items = new ArrayList<SelectItem>(supportedLocales.size());
        for(Locale locale : supportedLocales) {
        	items.add(new SelectItem(locale.toString(), locale.getDisplayName()));
        }
        return items;
    }

    public String getSelectedLocale() {
        return getLocale().toString();
    }

    public void setSelectedLocale() {
        setSelectedLocale(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("locale"));
    }
    
   public void setSelectedLocale(String localeString) {
        Iterator<Locale> supportedLocales = FacesUtils.getApplication().getSupportedLocales();
        while (supportedLocales.hasNext()) {
            Locale locale = supportedLocales.next();
            if (locale.toString().equals(localeString)) {
                this.locale = locale;
                break;
            }
        }
    }
}