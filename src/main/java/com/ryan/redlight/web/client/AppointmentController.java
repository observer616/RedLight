package com.ryan.redlight.web.client;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.ViewAppointment;
import com.ryan.redlight.entity.vo.AppointmentVo;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.interceptor.ClientCheck;
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
import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/appointments")
public class AppointmentController {
    final
    ViewAppointmentService appointmentService;

    final
    HouseService houseService;

    public AppointmentController(ViewAppointmentService appointmentService, HouseService houseService) {
        this.appointmentService = appointmentService;
        this.houseService = houseService;
    }

    @ClientCheck
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
            msg = appointmentService.insertSelective(houseId,
                    client.getClientId(), viewTime);
        }
        model.addAttribute("msg", msg);
        House house = houseService.selectByPrimaryKey(houseId);
        model.addAttribute("house", house);
        model.addAttribute("viewAppointment", new ViewAppointment());
        return "client/house_detail";
    }

    @AdminCheck
    @RequestMapping(value = "/get/creatorId")
    public String appointmentList(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                  HttpSession session,
                                  Model model) {
        Client client = (Client) session.getAttribute("clientInfo");
        PageInfo<AppointmentVo> appointmentVoPageInfo = appointmentService.selectAllByCreatorId(pageNum, client.getClientId());
        List<AppointmentVo> appointmentVoList = appointmentVoPageInfo.getList();
        model.addAttribute("appointmentVoPageInfo", appointmentVoPageInfo);
        model.addAttribute("appointmentVoList", appointmentVoList);
        return "client/personal_appointments";
    }
}
