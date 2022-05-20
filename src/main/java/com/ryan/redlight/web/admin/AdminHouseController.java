package com.ryan.redlight.web.admin;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.MsgDeprecated;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/admin/houses")
public class AdminHouseController {
    @Autowired
    HouseService houseService;

    @AdminCheck
    @PostMapping(value = "/create")
    public String create(@ModelAttribute(value = "house") House house,
                         Model model) {
        MsgDeprecated msgDeprecated = houseService.insertSelective(house);
        // TODO: 2022/5/15 msgDeprecated model.addAttribute("msgDeprecated", msgDeprecated);
        return "redirect:/admin/houses/get/list";
    }

    /**
     * 翻页与模糊搜索
     *
     * @param pageNum
     * @param condition
     * @param model
     * @return
     */
    @AdminCheck
    @RequestMapping(value = "/get/list")
    public String select(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam(required = false, value = "condition") String condition,
                         Model model) {
        PageInfo<House> housePageInfo = houseService.selectList(pageNum, condition);
        List<House> houseList = housePageInfo.getList();
        model.addAttribute("housePageInfo", housePageInfo);
        model.addAttribute("houseList", houseList);
        model.addAttribute("condition", condition);
        // 用于添加功能
        House house = new House();
        model.addAttribute("house", house);
        return "admin/house_list";
    }

    @AdminCheck
    @PostMapping(value = "/delete")
    public String delete(@RequestParam(value = "houseId") Integer houseId,
                         Model model) {
        MsgDeprecated msgDeprecated = houseService.deleteByPrimaryKey(houseId);
        // TODO: 2022/5/15 msgDeprecated model.addAttribute("msgDeprecated", msgDeprecated);
        return "redirect:/admin/houses/get/list";
    }

    @AdminCheck
    @RequestMapping(value = "/get/single")
    public String houseDetailPage(@RequestParam(value = "houseId") Integer houseId,
                                  Model model) {
        House house = houseService.selectByPrimaryKey(houseId);
        model.addAttribute("house", house);
        return "admin/house_detail";
    }

    @AdminCheck
    @PostMapping(value = "/update")
    public String update(@ModelAttribute(value = "house") House house,
                         Model model) {
        MsgDeprecated msgDeprecated = houseService.updateByPrimaryKeySelective(house);
        model.addAttribute("msg", msgDeprecated);
        model.addAttribute("house", house);
        return "admin/house_detail";
    }
}
