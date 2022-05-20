package com.ryan.redlight.web.tourist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Ryan
 */
@Controller
public class HomeController {
    @GetMapping(value = "/home")
    public String getPage(HttpSession session, Model model) {
        String userInfo = session.getAttribute("userInfo").toString();
        model.addAttribute("userInfo", session.getAttribute("userInfo"));
        System.out.println( "1"+userInfo);
        return "tourist/home";
    }
}
