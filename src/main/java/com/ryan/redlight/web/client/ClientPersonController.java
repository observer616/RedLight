package com.ryan.redlight.web.client;

import com.ryan.redlight.entity.Client;
import com.ryan.redlight.interceptor.LoginCheck;
import com.ryan.redlight.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * todo
 * @author Ryan
 */
@Controller
public class ClientPersonController {
    @Autowired
    ClientService clientService;


    @LoginCheck
    @GetMapping(value = "/clients/get/single")
    public String getSingle(HttpSession session,
                            Model model) {
        Client client = (Client) session.getAttribute("userInfo");
        // 页面中更新功能需要model
        model.addAttribute("userInfo", client);
        return "client/personal_info";
    }

    @LoginCheck
    @PostMapping(value = "/clients/update")
    public String update(@RequestParam("userInfo") Client client) {
//        MsgDeprecated msgDeprecated = clientService.updateByPrimaryKeySelective(client);
        return "redirect:/clients/get/single";
    }
}
