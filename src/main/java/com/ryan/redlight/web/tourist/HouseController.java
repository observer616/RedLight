package com.ryan.redlight.web.tourist;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.MsgDeprecated;
import com.ryan.redlight.entity.ViewAppointment;
import com.ryan.redlight.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/houses")
public class HouseController {
    @Autowired
    HouseService houseService;

    @GetMapping(value = "")
    public String getHouseList(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                               @RequestParam(required = false, value = "condition") String condition,
                               Model model) {
        PageInfo<House> housePageInfo = houseService.selectList(pageNum, condition);
        List<House> houseList = housePageInfo.getList();
        model.addAttribute("housePageInfo", housePageInfo);
        model.addAttribute("houseList", houseList);
        model.addAttribute("condition", condition);
        return "tourist/house_list";
    }

    @RequestMapping(value = "/single")
    public String getHouse(@RequestParam(value = "houseId") Integer houseId,
                           Model model) {
        House house = houseService.selectByPrimaryKey(houseId);
        if (house == null) {
            model.addAttribute("msg", new MsgDeprecated(MsgDeprecated.STATE_FAILURE, "未找到该房屋信息"));
            return "redirect:/houses";
        }
        model.addAttribute("house", house);
        model.addAttribute("viewAppointment", new ViewAppointment());
        return "tourist/house";
    }
}
