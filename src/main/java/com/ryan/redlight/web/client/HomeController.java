package com.ryan.redlight.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ryan
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/home")
    public String homePage(){
        return "client/home";
    }
}
