package com.ryan.redlight.web.admin;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Msg;
import com.ryan.redlight.entity.User;
import com.ryan.redlight.entity.ViewAppointment;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.service.ViewAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/admin/appointment")
public class AdminAppointmentController {
    @Autowired
    ViewAppointmentService appointmentService;

    @AdminCheck
    @RequestMapping(value = "/get/list")
    public String appointmentList(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                  Model model) {
        PageInfo<ViewAppointment> appointmentPageInfo = appointmentService.selectAll(pageNum);
        List<ViewAppointment> appointmentList = appointmentPageInfo.getList();
        model.addAttribute("appointmentPageInfo", appointmentPageInfo);
        model.addAttribute("appointmentList", appointmentList);
        // TODO: 2022/5/15 condition?
        model.addAttribute("appointment", new ViewAppointment());
        return "admin/appointment_list";
    }

    @AdminCheck
    @PostMapping(value = "/update/reply")
    public String updateReply(@ModelAttribute(value = "appointment") ViewAppointment appointment,
                              HttpSession session,
                              Model model) {
        appointment.setIsViewed((byte) 1);
        User userInfo = (User) session.getAttribute("userInfo");
        appointment.setReplyerId(userInfo.getUserId());
        // 设置回复时间为当前时间
        appointment.setReplyTime(new Date());
        Msg msg = appointmentService.updateByPrimaryKeySelective(appointment);
        model.addAttribute("msg", msg);
        model.addAttribute("appointment", appointment);
        return "redirect:/admin/appointment/get/list";
    }

    @AdminCheck
    @PostMapping(value = "/update/view")
    public String updateView(@ModelAttribute(value = "appointment") ViewAppointment appointment,
                             Model model) {
        appointment.setIsViewed((byte) 1);
        Msg msg = appointmentService.updateByPrimaryKeySelective(appointment);
        model.addAttribute("msg", msg);
        model.addAttribute("appointment", appointment);
        return "redirect:/admin/appointment/get/list";
    }
}
