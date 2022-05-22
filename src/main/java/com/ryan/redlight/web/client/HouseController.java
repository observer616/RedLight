package com.ryan.redlight.web.client;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.entity.ViewAppointment;
import com.ryan.redlight.service.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/houses")
public class HouseController {
    final
    HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping(value = "get/list")
    public String getHouseList(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                               @RequestParam(required = false, value = "condition") String condition,
                               Model model) {
        PageInfo<House> housePageInfo = houseService.selectList(pageNum, condition);
        List<House> houseList = housePageInfo.getList();
        model.addAttribute("housePageInfo", housePageInfo);
        model.addAttribute("houseList", houseList);
        model.addAttribute("condition", condition);
        return "client/house_list";
    }

    @RequestMapping(value = "/single")
    public String getHouse(@RequestParam(value = "houseId") Integer houseId,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        House house = houseService.selectByPrimaryKey(houseId);
        if (house == null) {
            redirectAttributes.addFlashAttribute("msg", new Msg("房屋不存在", "消失术！"));
            return "redirect:/houses/get/list";
        }
        model.addAttribute("house", house);
        model.addAttribute("viewAppointment", new ViewAppointment());
        return "client/house_detail";
    }
}
