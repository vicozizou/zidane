package com.bytepoxic.example.web;

import com.bytepoxic.example.model.Bar;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/bars")
@Controller
@RooWebScaffold(path = "bars", formBackingObject = Bar.class)
public class BarController {
}
