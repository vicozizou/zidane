// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web;

import com.bytepoxic.core.model.Location;
import com.bytepoxic.core.model.Nationality;
import com.bytepoxic.core.service.LocationService;
import com.bytepoxic.core.web.NationalityController;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect NationalityController_Roo_Controller {
    
    @Autowired
    LocationService NationalityController.locationService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String NationalityController.create(@Valid Nationality nationality, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, nationality);
            return "nationalitys/create";
        }
        uiModel.asMap().clear();
        locationService.saveNationality(nationality);
        return "redirect:/nationalitys/" + encodeUrlPathSegment(nationality.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String NationalityController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Nationality());
        List<String[]> dependencies = new ArrayList<String[]>();
        if (locationService.countAllLocations() == 0) {
            dependencies.add(new String[] { "location", "locations" });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "nationalitys/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String NationalityController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("nationality", locationService.findNationality(id));
        uiModel.addAttribute("itemId", id);
        return "nationalitys/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String NationalityController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("nationalitys", locationService.findNationalityEntries(firstResult, sizeNo));
            float nrOfPages = (float) locationService.countAllNationalitys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("nationalitys", locationService.findAllNationalitys());
        }
        return "nationalitys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String NationalityController.update(@Valid Nationality nationality, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, nationality);
            return "nationalitys/update";
        }
        uiModel.asMap().clear();
        locationService.updateNationality(nationality);
        return "redirect:/nationalitys/" + encodeUrlPathSegment(nationality.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String NationalityController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, locationService.findNationality(id));
        return "nationalitys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String NationalityController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Nationality nationality = locationService.findNationality(id);
        locationService.deleteNationality(nationality);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/nationalitys";
    }
    
    void NationalityController.populateEditForm(Model uiModel, Nationality nationality) {
        uiModel.addAttribute("nationality", nationality);
        uiModel.addAttribute("locations", locationService.findAllLocations());
    }
    
    String NationalityController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
