// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bytepoxic.core.web;

import com.bytepoxic.core.dao.PersonDAO;
import com.bytepoxic.core.dao.PhoneDAO;
import com.bytepoxic.core.model.Phone;
import com.bytepoxic.core.model.PhoneType;
import com.bytepoxic.core.web.PhoneController;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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

privileged aspect PhoneController_Roo_Controller {
    
    @Autowired
    PhoneDAO PhoneController.phoneDAO;
    
    @Autowired
    PersonDAO PhoneController.personDAO;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String PhoneController.create(@Valid Phone phone, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, phone);
            return "phones/create";
        }
        uiModel.asMap().clear();
        phoneDAO.save(phone);
        return "redirect:/phones/" + encodeUrlPathSegment(phone.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String PhoneController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Phone());
        return "phones/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String PhoneController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("phone", phoneDAO.findOne(id));
        uiModel.addAttribute("itemId", id);
        return "phones/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String PhoneController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("phones", phoneDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) phoneDAO.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("phones", phoneDAO.findAll());
        }
        return "phones/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String PhoneController.update(@Valid Phone phone, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, phone);
            return "phones/update";
        }
        uiModel.asMap().clear();
        phoneDAO.save(phone);
        return "redirect:/phones/" + encodeUrlPathSegment(phone.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String PhoneController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, phoneDAO.findOne(id));
        return "phones/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String PhoneController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Phone phone = phoneDAO.findOne(id);
        phoneDAO.delete(phone);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/phones";
    }
    
    void PhoneController.populateEditForm(Model uiModel, Phone phone) {
        uiModel.addAttribute("phone", phone);
        uiModel.addAttribute("people", personDAO.findAll());
        uiModel.addAttribute("phonetypes", Arrays.asList(PhoneType.values()));
    }
    
    String PhoneController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
