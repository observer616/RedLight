package com.ryan.redlight.web.client;

import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author Ryan
 */
@Controller
public class LoginRegisterController {
    final
    ClientService clientService;

    public LoginRegisterController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "client/login";
    }

    @PostMapping(value = "/session")
    public String loginClient(@RequestParam(value = "nickname") String nickname,
                              @RequestParam(value = "password") String password,
                              HttpSession session,
                              Model model) {
        // 查找 client
        Client getClient = clientService.selectByNickname(nickname);
        // 查找失败，附加错误信息
        if (getClient == null) {
            model.addAttribute("msg", new Msg("登陆失败", "用户不存在"));
            return "client/login";
        } else if (!getClient.getPassword().equals(password)) {
            model.addAttribute("msg", new Msg("登陆失败", "密码错误"));
            return "client/login";
        }
        // 查找成功，创建 session:
        //      clientInfo:   Client
        //      clientInfo:  Client
        session.setAttribute("clientInfo", getClient);
        return "redirect:/home";
    }

    @GetMapping(value = "/register")
    public String registerPage(Model model) {
        model.addAttribute("client", new Client());
        return "client/register";
    }

    @PostMapping(value = "/register/create")
    public Object registerClient(@ModelAttribute(value = "client") Client client,
                                 Model model) {
        Integer clientId = clientService.insertSelective(client);
        if (clientId == null) {
            model.addAttribute("msg", new Msg("注册失败", "用户名已存在"));
            return "client/register";
        }
        return "redirect:/login";
    }
}
