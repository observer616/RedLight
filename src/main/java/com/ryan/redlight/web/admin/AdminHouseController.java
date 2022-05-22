package com.ryan.redlight.web.admin;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.service.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/admin/houses")
public class AdminHouseController {
    final
    HouseService houseService;

    public AdminHouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @AdminCheck
    @PostMapping(value = "/create")
    public String create(@ModelAttribute(value = "house") House house,
                         RedirectAttributes redirectAttributes) {
        Msg msg = houseService.insertSelective(house);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/houses/get/list";
    }

    /**
     * 翻页与模糊搜索
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
                         RedirectAttributes redirectAttributes) {
        Msg msg = houseService.deleteByPrimaryKey(houseId);
        redirectAttributes.addFlashAttribute("msg", msg);
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
        Msg msg = houseService.updateByPrimaryKeySelective(house);
        model.addAttribute("msg", msg);
        model.addAttribute("house", house);
        return "admin/house_detail";
    }
}
