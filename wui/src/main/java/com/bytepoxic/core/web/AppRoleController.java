package com.bytepoxic.core.web;

import com.bytepoxic.core.model.AppRole;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/approles")
@Controller
@RooWebScaffold(path = "approles", formBackingObject = AppRole.class)
public class AppRoleController {
}
