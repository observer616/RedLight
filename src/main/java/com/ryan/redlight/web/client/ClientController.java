package com.ryan.redlight.web.client;

import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.interceptor.ClientCheck;
import com.ryan.redlight.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Ryan
 */
@Controller
public class ClientController {
    final
    ClientService clientService;

    public ClientController(ClientService clientService) {
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
        Msg msg = clientService.insertSelective(client);
        if (msg == null) {
            model.addAttribute("msg", new Msg("注册失败", "用户名已存在"));
            return "client/register";
        }
        return "redirect:/login";
    }

    @ClientCheck
    @GetMapping(value = "/clients/get/single")
    public String getSingle(HttpSession session,
                            Model model) {
        Client client = (Client) session.getAttribute("clientInfo");
        // 页面中更新功能需要model
        model.addAttribute("client", client);
        return "client/personal_info";
    }

    @AdminCheck
    @PostMapping(value = "/clients/update")
    public String update(@RequestParam(value="clientId") Integer clientId,
                         @ModelAttribute(value = "client") Client client,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
        client.setClientId(clientId);
        Msg msg = clientService.updateByPrimaryKeySelective(client);
        redirectAttributes.addAttribute("msg", msg);
        // 更新session
        client = clientService.selectByPrimaryKey(clientId);
        session.setAttribute("clientInfo", client);
        return "redirect:/clients/get/single?clientId="+clientId;
    }
}
