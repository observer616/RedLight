package com.ryan.redlight.web.admin;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.service.AdminService;
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
    final
    AdminService adminService;

    public AdminLoginController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/admin/login")
    public String loginPage() {
        return "admin/login";

    }

    @PostMapping(value = "/admin/session")
    public String loginAdmin(@RequestParam(value = "nickname") String nickname,
                             @RequestParam(value = "password") String password,
                             HttpSession session,
                             Model model) {
        // 查找 admin
        Admin getAdmin = adminService.selectByNickname(nickname);
        // 查找失败，附加错误信息
        if (getAdmin == null) {
            model.addAttribute("msg", new Msg("登陆失败", "用户不存在"));
            return "admin/login";
        } else if (!getAdmin.getPassword().equals(password)) {
            model.addAttribute("msg", new Msg("登陆失败", "密码错误"));
            return "admin/login";
        }
        // 查找成功，创建 session:
        //      adminInfo:   Admin
        //      clientInfo:  Client
        session.setAttribute("adminInfo", getAdmin);
        return "redirect:/admin/houses/get/list";
    }
}
