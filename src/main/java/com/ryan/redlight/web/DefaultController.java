package com.ryan.redlight.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Ryan
 */
@Controller
public class DefaultController {
    @GetMapping(value = {"/*"})
    public String redirectHome() {
        // TODO: 2022/5/16 home
//        return "redirect:/home";
        return "admin/house_visual";
    }

    @GetMapping(value = {"/admin/*", "/admin"})
    public String redirectAdmin(HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin != null && isAdmin) {
            return "redirect:/admin/houses/get/list";
        } else {
            return "redirect:/admin/login";
        }
    }
}
