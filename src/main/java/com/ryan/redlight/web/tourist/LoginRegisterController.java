package com.ryan.redlight.web.tourist;

import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.Msg;
import com.ryan.redlight.entity.User;
import com.ryan.redlight.service.AdminService;
import com.ryan.redlight.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/login_register")
public class LoginRegisterController {
    @Autowired
    ClientService clientService;

    @Autowired
    AdminService adminService;

    @GetMapping(value = {"/*", ""})
    public String getPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("msg", new Msg(null));
        return "tourist/login_register";
    }

    @PostMapping(value = "/login/client")
    public String loginClient(HttpSession session, Model model,
                              @ModelAttribute(value = "user") User user) {
        // 获取参数
        String nickName = user.getNickName();
        String password = user.getPassword();
        // 查找 user
        User getUser = clientService.selectByNickName(nickName);
        if (getUser == null
                || !getUser.getPassword().equals(password)) {
            // 查找失败，附加错误信息
            model.addAttribute("msg", new Msg(Msg.STATE_FAILURE, "用户名或密码错误"));
            return "tourist/login_register";
        }
        model.addAttribute("msg", new Msg(Msg.STATE_SUCCESS, "登录成功"));
        // 查找成功，创建 session:
        //      isLogin: true/false 已登陆
        //      isAdmin: true/false
        //      info:   Client/Admin
        session.setAttribute("isLogin", true);
        session.setAttribute("isAdmin", false);
        session.setAttribute("userInfo", getUser);
        // TODO: 2022/5/20
//        System.out.println(session.getAttribute("userInfo"));

//        model.addAttribute("isLogin", (Boolean)session.getAttribute("isLogin"));
        String userInfo = session.getAttribute("userInfo").toString();
        model.addAttribute("userInfo", session.getAttribute("userInfo"));
        System.out.println( "1"+userInfo);
        return "tourist/home";
    }

    @PostMapping(value = "/login/admin")
    public Object loginAdmin(HttpSession session, Model model,
                             @ModelAttribute(value = "user") User user) {
        // 获取参数
        String nickName = user.getNickName();
        String password = user.getPassword();
        // 查找 user
        User getUser = adminService.selectByNickName(nickName);
        if (getUser == null
                || !getUser.getPassword().equals(password)) {
            // 查找失败，附加错误信息
            model.addAttribute("msg", new Msg(Msg.STATE_FAILURE, "用户名或密码错误"));
            return "tourist/login_register";
        }
        model.addAttribute("msg", new Msg(Msg.STATE_SUCCESS, "登录成功"));
        // 查找成功，创建 session:
        //      isLogin: true/false 已登陆
        //      isAdmin: true/false
        //      info:   Client/Admin
//        session.setAttribute("isLogin", true);
        session.setAttribute("isAdmin", true);
        session.setAttribute("userInfo", getUser);
        // TODO: 2022/5/20
//        System.out.println(session.getAttribute("userInfo"));

//        model.addAttribute("isLogin", (Boolean)session.getAttribute("isLogin"));
        String userInfo = session.getAttribute("userInfo").toString();
        model.addAttribute("userInfo", session.getAttribute("userInfo"));
        System.out.println( "1"+userInfo);
        return "tourist/home";
    }

    @PostMapping(value = "/register/client")
    public Object registerClient(Model model,
                                 @ModelAttribute(value = "user") User user) {
        Client client = new Client(user);
        Msg msg = clientService.insertSelective(client);
        if (msg == null || msg.getState().equals(Msg.STATE_FAILURE)) {
            model.addAttribute("msg", msg);
            return "tourist/login_register";
        }
        model.addAttribute("msg", "注册成功，请登录");
        return "tourist/login_register";
    }
}
