package com.ryan.redlight.web.admin;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.Msg;
import com.ryan.redlight.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author Ryan
 */
@Controller
public class AdminLoginController {
    @Autowired
    AdminService adminService;

    @GetMapping(value = "/admin/login")
    public String loginPage() {
        return "admin/login";

    }

    @PostMapping(value = "/admin/session")
    public String loginAdmin(@RequestParam(value = "nickname") String nickname,
                             @RequestParam(value = "password") String password,
                             HttpSession session,
                             Model model) {
        // 查找 user
        Admin getAdmin = adminService.selectByNickName(nickname);
        if (getAdmin == null
                || !getAdmin.getPassword().equals(password)) {
            // 查找失败，附加错误信息
            model.addAttribute("msg", new Msg(Msg.STATE_FAILURE, "用户名或密码错误"));
            return "admin/login";
        }
        model.addAttribute("msg", new Msg(Msg.STATE_SUCCESS, "登录成功"));
        // 查找成功，创建 session:
        //      adminInfo:   Admin
        //      clientInfo:  Client
        session.setAttribute("adminInfo", getAdmin);
        return "redirect:/admin/houses/get/list";
    }
}
