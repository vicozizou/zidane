package com.bytepoxic.core.web;

import com.bytepoxic.core.model.Nationality;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/nationalitys")
@Controller
@RooWebScaffold(path = "nationalitys", formBackingObject = Nationality.class)
public class NationalityController {
}
