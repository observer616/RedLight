package com.ryan.redlight.web.admin;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.ViewAppointment;
import com.ryan.redlight.entity.vo.AppointmentVo;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.service.ViewAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/admin/appointments")
public class AdminAppointmentController {
    final
    ViewAppointmentService appointmentService;

    public AdminAppointmentController(ViewAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @AdminCheck
    @RequestMapping(value = "/get/list")
    public String appointmentList(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                  Model model) {
        PageInfo<AppointmentVo> appointmentVoPageInfo = appointmentService.selectAll(pageNum);
        List<AppointmentVo> appointmentVoList = appointmentVoPageInfo.getList();
        model.addAttribute("appointmentVoPageInfo", appointmentVoPageInfo);
        model.addAttribute("appointmentVoList", appointmentVoList);
        // TODO: 2022/5/15 condition?
        model.addAttribute("appointmentVo", new AppointmentVo());
        return "admin/appointment_list";
    }

    @AdminCheck
    @PostMapping(value = "/update/reply")
    public String updateReply(@ModelAttribute(value = "appointmentVo") AppointmentVo appointmentVo,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        Admin adminInfo = (Admin) session.getAttribute("adminInfo");
        // 构造appointment对象
        appointmentVo.setIsReplied((byte) 1);
        appointmentVo.setReplyerId(adminInfo.getAdminId());
        // 设置回复时间为当前时间
        appointmentVo.setReplyTime(new Date());
        Msg msg = appointmentService.updateByPrimaryKeySelective(appointmentVo);
        redirectAttributes.addAttribute("msg", msg);
        return "redirect:/admin/appointmentVo/get/list";
    }

    @AdminCheck
    @PostMapping(value = "/update/view")
    public String updateView(@ModelAttribute(value = "appointmentVo") AppointmentVo appointmentVo,
                             RedirectAttributes redirectAttributes) {
        appointmentVo.setIsViewed((byte) 1);
        Msg msg = appointmentService.updateByPrimaryKeySelective(appointmentVo);
        redirectAttributes.addAttribute("msg", msg);
        return "redirect:/admin/appointment/get/list";
    }
}
