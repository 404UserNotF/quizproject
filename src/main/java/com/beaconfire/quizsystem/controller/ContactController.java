package com.beaconfire.quizsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {
    @RequestMapping(path="contact/toContact")
    public String toContact(){
        return "contact";
    }
}
