package com.ryan.redlight.web.admin;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.MsgDeprecated;
import com.ryan.redlight.interceptor.AdminCheck;
import com.ryan.redlight.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping(value = "/admin/clients")
public class AdminUserController {
    @Autowired
    ClientService clientService;

    // 翻页与模糊搜索
    @AdminCheck
    @PostMapping(value = "/get/list")
    public String select(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam(required = false, value = "condition") String condition,
                         Model model) {
        PageInfo<Client> clientPageInfo = clientService.selectList(pageNum, condition);
        List<Client> clientList = clientPageInfo.getList();
        model.addAttribute("clientPageInfo", clientPageInfo);
        model.addAttribute("clientList", clientList);
        model.addAttribute("client", new Client());
        model.addAttribute("client", "");
        // TODO: 2022/5/15 是否需要传递 condition model.addAttribute("condition", condition);
        return "admin/client_list";
    }

    @AdminCheck
    @PostMapping(value = "/create")
    public String create(@RequestParam(value = "client") Client client,
                         Model model) {
        MsgDeprecated msgDeprecated = clientService.insertSelective(client);
        // TODO: 2022/5/15 msgDeprecated model.addAttribute("msgDeprecated", msgDeprecated);
        return "redirect:/admin/clients/get/list";
    }

    @AdminCheck
    @PostMapping(value = "/delete")
    public String delete(@RequestParam(value = "clientId") Integer clientId,
                         Model model) {
        MsgDeprecated msgDeprecated = clientService.deleteByPrimaryKey(clientId);
        // TODO: 2022/5/15 msgDeprecated model.addAttribute("msgDeprecated", msgDeprecated);
        return "redirect:/admin/clients/get/list";
    }

    @AdminCheck
    @RequestMapping(value = "/get/single")
    public String clientDetailPage(@RequestParam(value = "client") Client client,
                                   Model model) {
        model.addAttribute("client", client);
        return "admin/client_detail";
    }

    @AdminCheck
    @PostMapping(value = "/admin/clients/update")
    public String update(@RequestParam(value = "client") Client client,
                         Model model) {
        MsgDeprecated msgDeprecated = clientService.updateByPrimaryKeySelective(client);
        model.addAttribute("msg", msgDeprecated);
        model.addAttribute("client", client);
        return "admin/client_detail";
    }
}
