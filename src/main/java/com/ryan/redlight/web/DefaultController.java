package com.ryan.redlight.web;

import com.ryan.redlight.entity.vo.VisualHouseTypeVo;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 设置默认控制器，收集未定义的url请求
 * @author Ryan
 */
@Controller
public class DefaultController {
    final
    HouseService houseService;

    public DefaultController(HouseService houseService) {
        this.houseService = houseService;
    }


    @GetMapping(value = {"/*"})
    public String redirectHome() {
        return "redirect:/home";
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
