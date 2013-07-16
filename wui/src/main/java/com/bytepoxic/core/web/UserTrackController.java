package com.bytepoxic.core.web;

import com.bytepoxic.core.model.UserTrack;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/usertracks")
@Controller
@RooWebScaffold(path = "usertracks", formBackingObject = UserTrack.class)
public class UserTrackController {
}
