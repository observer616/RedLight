package com.ryan.redlight.web.client;

import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.Msg;
import com.ryan.redlight.service.HouseService;
import com.ryan.redlight.service.ViewAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ClientViewAppointmentController {
    @Autowired
    ViewAppointmentService viewAppointmentService;

    @Autowired
    HouseService houseService;

    @PostMapping(value = "/create")
    public String createViewAppointment(@RequestParam(value = "houseId") Integer houseId,
                                        @RequestParam(value = "date") String date,
                                        @RequestParam(value = "time") String time,
                                        HttpSession session,
                                        Model model) throws ParseException {
        // 时间转换
        System.out.println(date + time);
        Date viewTime = new SimpleDateFormat("yyy-MM-dd HH:mm").parse(date + " " + time);

        Client client = (Client) session.getAttribute("userInfo");
        // 创建预约
        Msg msg = viewAppointmentService.insertSelective(houseId,
                client.getUserId(), viewTime);
        return "redirect:/houses/single?houseId=" + houseId;
    }
}
