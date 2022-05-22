package com.ryan.redlight.web.client;

import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.entity.ViewAppointment;
import com.ryan.redlight.interceptor.LoginCheck;
import com.ryan.redlight.service.HouseService;
import com.ryan.redlight.service.ViewAppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/client/view_appointment")
public class AppointmentController {
    final
    ViewAppointmentService viewAppointmentService;

    final
    HouseService houseService;

    public AppointmentController(ViewAppointmentService viewAppointmentService, HouseService houseService) {
        this.viewAppointmentService = viewAppointmentService;
        this.houseService = houseService;
    }

    @LoginCheck
    @PostMapping(value = "/create")
    public String createViewAppointment(@RequestParam(value = "houseId") Integer houseId,
                                        @RequestParam(value = "date") String date,
                                        @RequestParam(value = "time") String time,
                                        HttpSession session,
                                        Model model) throws ParseException {
        // 时间转换
        Date viewTime = new SimpleDateFormat("yyy-MM-dd HH:mm").parse(date + " " + time);

        Client client = (Client) session.getAttribute("clientInfo");
        Msg msg;
        // 管理员身份
        if (client == null) {
            msg = new Msg("权限错误", "请确保用户身份");
        } else {
            // 创建预约
            msg = viewAppointmentService.insertSelective(houseId,
                    client.getClientId(), viewTime);
        }
        model.addAttribute("msg", msg);
        House house = houseService.selectByPrimaryKey(houseId);
        model.addAttribute("house", house);
        model.addAttribute("viewAppointment", new ViewAppointment());
        return "client/house_detail";
    }
}
