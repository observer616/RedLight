package com.ryan.redlight.web.client;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.service.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Ryan
 */
@Controller
public class HomeController {
    final
    HouseService houseService;

    public HomeController(HouseService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping(value = "/home")
    public String homePage(Model model) {
        // 用于广告轮播
        PageInfo<House> housePageInfo = houseService.selectList(1, null);
        List<House> houseList = housePageInfo.getList();
        model.addAttribute("housePageInfo", housePageInfo);
        model.addAttribute("houseList", houseList);
        return "client/home";
    }
}
